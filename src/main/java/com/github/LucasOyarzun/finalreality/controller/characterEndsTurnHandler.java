package com.github.LucasOyarzun.finalreality.controller;

import java.beans.PropertyChangeEvent;

/**
 * A class that holds the behavior of characterEndsListener
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */

public class characterEndsTurnHandler implements IEventHandler {
    private final GameController controller;

    /**
     * Create the handler.
     * @param controller Observer.
     */
    public characterEndsTurnHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.characterEndsTurn((String) evt.getNewValue());
    }
}