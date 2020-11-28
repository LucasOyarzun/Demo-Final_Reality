package com.github.LucasOyarzun.finalreality.controller;

import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;

import java.beans.PropertyChangeEvent;

/**
 * A class that holds playerCharacterDeathListener
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */

public class playerCharacterDeathHandler implements IEventHandler {
    private final GameController controller;

    /**
     * Create the handler.
     * @param controller Observer.
     */
    public playerCharacterDeathHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onCharacterDeath((IPlayerCharacter) evt.getNewValue());
    }
}
