package de.jayy.interactions.classes;

import de.jayy.interactions.interfaces.ButtonExecutor;

public class Button {
    private ButtonExecutor executor;

    public ButtonExecutor getExecutor() {
        return this.executor;
    }

    public void setExecutor(ButtonExecutor executor) {
        this.executor = executor;
    }
}