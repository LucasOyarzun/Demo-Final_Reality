package com.github.LucasOyarzun.finalreality.model;

import com.github.LucasOyarzun.finalreality.controller.GameController;
import com.github.LucasOyarzun.finalreality.controller.IEventHandler;
import com.github.LucasOyarzun.finalreality.model.character.Enemy;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.InvalidEquipException;
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

public class Player implements IPlayer{

    private final PropertyChangeSupport enemyDeathEvent = new PropertyChangeSupport(this);
    private final String name;
    private final ArrayList<ICharacter> characters = new ArrayList<>();
    private final ArrayList<IWeapon> inventory = new ArrayList<>();
    protected BlockingQueue<ICharacter> turns;
    private boolean win;
    private final GameController controller;

    /**
     * Creates a Player in the game.
     * @param name name of the Player.
     * @param turns Queue of turns.
     */
    public Player (String name, GameController controller, BlockingQueue<ICharacter> turns) {
        this.name= name;
        this.turns = turns;
        this.controller = controller;
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
    public void equipWeapon(IWeapon weapon, IPlayerCharacter character) throws InvalidEquipException {
        if (characters.contains(character) && inventory.contains(weapon) ) {
            if (character.getEquippedWeapon() != null) { //If the character has a weapon, add it again to the inventory
                IWeapon lastWeapon = character.getEquippedWeapon();
                character.equip(weapon);
                addWeapon(lastWeapon);
                removeWeapon(weapon);
            } else {
                character.equip(weapon);
                removeWeapon(weapon);
            }
        }
    }

    /**
     * Return this Player's inventory.
     */
    public ArrayList<IWeapon> getInventory() {
        return inventory;
    }

    @Override
    public ArrayList<ICharacter> getCharacters() {
        return characters;
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

    @Override
    public void attack(ICharacter playerCharacter, ICharacter enemy) {
        playerCharacter.attack(enemy);
        if (!enemy.isAlive()) {
            enemyDeathEvent.firePropertyChange(
                    enemy.getName() + "has died",
                    null,
                    enemy
            );
        }
        playerCharacter.waitTurn();
    }

    @Override
    public void addListener(IEventHandler handler) {
        enemyDeathEvent.addPropertyChangeListener(handler);
    }

}
