package de.jayy.interactions.commands;

import de.jayy.data.DataStorage;
import de.jayy.interactions.interfaces.CommandExecutor;
import de.jayy.utils.MessageUtil;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ReverseCommand implements CommandExecutor {

    private final DataStorage storage;

    public ReverseCommand(DataStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run(SlashCommandInteractionEvent event) {
        OptionMapping eventOption = event.getOption("md5");

        if(eventOption == null) {
            event.reply("Please Enter MD5 you want to reverse").setEphemeral(true).complete().deleteOriginal().queueAfter(10, TimeUnit.SECONDS);
            return;
        }

        String md5 = Objects.requireNonNull(eventOption).getAsString();
        String clearFromMD5 = this.storage.getClearFromMD5(md5);

        MessageEmbed response;

        if(clearFromMD5 == null) {
            response = MessageUtil.embedMessageReverse(false, md5, "Not Found");
        } else {
            response = MessageUtil.embedMessageReverse(true, md5, clearFromMD5);
        }

        event.replyEmbeds(response).queue();
    }
}