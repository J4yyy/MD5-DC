package de.jayy.interactions;

import de.jayy.Main;
import de.jayy.interactions.classes.Button;
import de.jayy.interactions.classes.Command;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InteractionManager {

    private static final ConcurrentHashMap<String, Command> commands = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Button> buttons = new ConcurrentHashMap<>();

    public static Command getCommand(String name) {
        if(!commands.containsKey(name)) {
            commands.put(name, new Command());
        }
        return commands.get(name);
    }

    public static Button getButton(String name) {
        if(!buttons.containsKey(name)) {
            buttons.put(name, new Button());
        }
        return buttons.get(name);
    }

    public static void push() {
        CommandListUpdateAction update = Main.getJda().updateCommands();

        for(Map.Entry<String, Command> command : commands.entrySet()) {
            update.addCommands(command.getValue().getData()).queue();
        }
        update.queue();
    }
}
