package com.github.LucasOyarzun.finalreality.controller;

import com.github.LucasOyarzun.finalreality.model.character.Enemy;
import java.beans.PropertyChangeEvent;

/**
 * A class that holds enemyDeathListener
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */

public class enemyDeathHandler implements IEventHandler {
    private final GameController controller;

    /**
     * Create the handler.
     * @param controller Observer.
     */
    public enemyDeathHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onEnemyDeath((Enemy) evt.getNewValue());
    }
}