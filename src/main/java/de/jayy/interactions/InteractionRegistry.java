package de.jayy.interactions;

import de.jayy.data.DataStorage;
import de.jayy.interactions.commands.HashCommand;
import de.jayy.interactions.commands.ReverseCommand;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class InteractionRegistry extends InteractionManager {
    public static void register(DataStorage storage) {
        // Slash Commands
        getCommand("reverse").setExecutor(new ReverseCommand(storage));
        getCommand("reverse").setData(Commands.slash("reverse", "Reverse a MD5 hash").addOption(OptionType.STRING, "md5", "The MD5 you want to reverse", true).setGuildOnly(true));

        getCommand("hash").setExecutor(new HashCommand(storage));
        getCommand("hash").setData(Commands.slash("hash", "Hash a Text").addOption(OptionType.STRING, "text", "Text that should be hashed", true).setGuildOnly(true));
        // Buttons

        push();
    }
}