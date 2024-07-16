package de.jayy.interactions.interfaces;

import net.dv8tion.jda.api.interactions.components.buttons.ButtonInteraction;

public interface ButtonExecutor {
    void run(ButtonInteraction event);
}