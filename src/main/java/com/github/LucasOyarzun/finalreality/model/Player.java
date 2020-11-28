package com.github.LucasOyarzun.finalreality.model;

import com.github.LucasOyarzun.finalreality.controller.IEventHandler;
import com.github.LucasOyarzun.finalreality.model.character.Enemy;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds behavior of Player in the battle.
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */

public class Player {

    private final PropertyChangeSupport enemyDeathEvent = new PropertyChangeSupport(this);
    private final String name;
    private final ArrayList<IPlayerCharacter> characters = new ArrayList<>();
    private final ArrayList<IWeapon> inventory = new ArrayList<>();
    protected BlockingQueue<ICharacter> turns;
    private boolean win;

    /**
     * Creates a Player in the game.
     * @param name name of the Player.
     * @param turns Queue of turns.
     */
    public Player (String name, BlockingQueue<ICharacter> turns) {
        this.name= name;
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
     * Player wins the game.
     */
    public void win() {
        win = true;
    }

    /**
     * Add a character to the ArrayList of characters.
     * @param character the character added.
     */
    public void addPlayerCharacter(IPlayerCharacter character) {
        characters.add(character);
    }

    /**
     * Remove a character from the ArrayList of characters.
     * @param character the character removed.
     */
    public void removePlayerCharacter(IPlayerCharacter character) {
        characters.remove(character);
    }


    /**
     * Add a weapon to the ArrayList of weapons.
     * @param weapon the weapon added.
     */
    public void addWeapon(IWeapon weapon) {
        inventory.add(weapon);
    }

    /**
     * Remove a weapon from the ArrayList of weapons.
         * @param weapon the weapon to remove.
     */
    public void removeWeapon(IWeapon weapon) {
        inventory.remove(weapon);
    }

    /**
     * Equip a weapon from the inventory to a character in character's list.
     * @param weapon     weapon to equip.
     * @param character  character that will equip the weapon.
     */
    public void equipWeapon(IWeapon weapon, IPlayerCharacter character) {
        if (characters.contains(character) && inventory.contains(weapon)) {
            character.equip(weapon);
        }
    }

    /**
     * Return this Player's inventory.
     */
    public ArrayList<IWeapon> getInventory() {
        return inventory;
    }

    /**
     * Return this Player's list of characters.
     */
    public ArrayList<IPlayerCharacter> getCharacters() {
        return this.characters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        Player player = (Player) o;
        return hashCode() == player.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * empty the Player's character list.
     */
    public void clearList() {
        characters.clear();
    }

    /**
     * Order a character to attack an enemy.
     * @param character character ordered.
     * @param enemy     enemy attacked.
     */
    public void attack(ICharacter character, Enemy enemy) {
        character.attack(enemy);
        if (!enemy.isAlive()) {
            enemyDeathEvent.firePropertyChange(
                    enemy.getName() + "has died",
                    null,
                    enemy
            );
        }
    }

    /**
     * Add 2 handlers to this Player.
     * @param handler  Handler of enemy's deaths.
     */
    public void addListener(IEventHandler handler) {
        enemyDeathEvent.addPropertyChangeListener(handler);
    }
}
