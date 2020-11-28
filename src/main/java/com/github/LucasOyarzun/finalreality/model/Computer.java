package com.github.LucasOyarzun.finalreality.model;

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

public class Computer {

    private final PropertyChangeSupport playerCharacterDeathEvent = new PropertyChangeSupport(this);
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    protected BlockingQueue<ICharacter> turns;
    protected String name;
    private boolean win;

    /**
     *Creates a new "Computer Player".
     * @param name a name for computer player.
     * @param turns the queue with the characters waiting for their turn.
     */
    public Computer (String name, BlockingQueue<ICharacter> turns) {
        this.name = name;
        this.turns = turns;
        win = false;

    }

    /**
     * Return true if this character won the game.
     */
    public boolean askWin() {
        return win;
    }

    /**
     * Computer wins the game.
     */
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

    /**
     * Return this Computer's list of enemies.
     */
    public ArrayList<Enemy> getEnemies() {
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

    /**
     * empty the Player's character list.
     */
    public void clearList() {
        enemies.clear();
    }

    /**
     * Order an enemy to attack a character.
     * @param enemy     enemy ordered.
     * @param character character attacked.
     */
    public void attack(ICharacter enemy, IPlayerCharacter character) {
        enemy.attack(character);
        if (!character.isAlive()) {
            playerCharacterDeathEvent.firePropertyChange(
                    character.getName() + "has died",
                    null,
                    character
                    );
        }
    }

    /**
     * Add 2 handlers to this Player.
     * @param handler  Handler of playerCharacter's deaths.
     */
    public void addListener(IEventHandler handler) {
        playerCharacterDeathEvent.addPropertyChangeListener(handler);
    }
}
