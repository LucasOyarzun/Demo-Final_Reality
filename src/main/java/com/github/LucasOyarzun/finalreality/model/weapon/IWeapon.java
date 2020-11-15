package com.github.LucasOyarzun.finalreality.model.weapon;

/**
 * This represents a weapon from the game.
 * A weapon can be used by a PlayerCharacter of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez
 */
public interface IWeapon {

    /**
     * Returns this weapon's name.
     */
    String getName();

    /**
     * Returns this weapon's damage.
     */
    int getDamage();

    /**
     * Returns this weapon's weight.
     */
    int getWeight();
}