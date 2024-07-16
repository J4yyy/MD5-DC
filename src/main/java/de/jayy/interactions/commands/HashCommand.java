package de.jayy.interactions.commands;

import de.jayy.data.DataStorage;
import de.jayy.interactions.interfaces.CommandExecutor;
import de.jayy.utils.MessageUtil;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

public class HashCommand implements CommandExecutor {

    private final DataStorage storage;

    public HashCommand(DataStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run(SlashCommandInteractionEvent event) {
        OptionMapping eventOption = event.getOption("text");
        if(eventOption == null) {
            event.reply("Provide Text that should be hashed").complete().deleteOriginal().queueAfter(10, TimeUnit.SECONDS);
            return;
        }

        String text = eventOption.getAsString();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(text.getBytes());

            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            String hash = sb.toString();

            this.storage.storeMD5(text, hash);

            event.replyEmbeds(MessageUtil.embedMessageHashed(hash, text)).queue();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}