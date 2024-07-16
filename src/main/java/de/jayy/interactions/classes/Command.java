package de.jayy.interactions.classes;

import de.jayy.interactions.interfaces.CommandExecutor;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class Command {
    private CommandExecutor executor;
    private CommandData data;

    public CommandExecutor getExecutor() {
        return this.executor;
    }

    public void setExecutor(CommandExecutor executor) {
        this.executor = executor;
    }

    public CommandData getData() {
        return this.data;
    }

    public void setData(CommandData data) {
        this.data = data;
    }
}