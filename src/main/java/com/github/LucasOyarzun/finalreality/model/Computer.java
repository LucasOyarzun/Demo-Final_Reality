package com.github.LucasOyarzun.finalreality.model;

import com.github.LucasOyarzun.finalreality.controller.GameController;
import com.github.LucasOyarzun.finalreality.controller.IEventHandler;
import com.github.LucasOyarzun.finalreality.model.character.Enemy;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds behavior of Computer in the battle.
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */

public class Computer implements IPlayer{

    private final PropertyChangeSupport playerCharacterDeathEvent = new PropertyChangeSupport(this);
    private final ArrayList<ICharacter> enemies = new ArrayList<>();
    protected BlockingQueue<ICharacter> turns;
    protected String name;
    private boolean win;
    private GameController controller;

    /**
     *Creates a new "Computer Player".
     * @param name a name for computer player.
     * @param turns the queue with the characters waiting for their turn.
     */
    public Computer (String name, GameController controller, BlockingQueue<ICharacter> turns) {
        this.name = name;
        this.turns = turns;
        this.controller  =controller;
        win = false;

    }

    @Override
    public boolean askWin() {
        return win;
    }

    @Override
    public void win() {
        win = true;
    }

    /**
     * Add an enemy to the ArrayList of enemies.
     * @param enemy the enemy added.
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * Remove an enemy from the ArrayList of enemies.
     * @param enemy the enemy removed.
     */
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    @Override
    public ArrayList<ICharacter> getCharacters() {
        return enemies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Computer)) {
            return false;
        }
        Computer com = (Computer) o;
        return hashCode() == com.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public void clearList() {
        enemies.clear();
    }

    @Override
    public void attack(ICharacter enemy, ICharacter playerCharacter) {
        enemy.attack(playerCharacter);
        if (!playerCharacter.isAlive()) {
            playerCharacterDeathEvent.firePropertyChange(
                    playerCharacter.getName() + "has died",
                    null,
                    playerCharacter
                    );
        }
        enemy.waitTurn();
    }

    @Override
    public void addListener(IEventHandler handler) {
        playerCharacterDeathEvent.addPropertyChangeListener(handler);
    }
}
