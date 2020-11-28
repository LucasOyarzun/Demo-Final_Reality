package com.github.LucasOyarzun.finalreality.model.character.player;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.*;

/**
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public interface IPlayerCharacter extends ICharacter {
    /**
     * Return this character's equipped weapon.
     */
    IWeapon getEquippedWeapon();

    /**
     * Equips a weapon to the character.
     */
    void equip(IWeapon weapon);

    /**
     * A method to try to equip an Axe.
     * @param axe
     */
    void equipAxe(Axe axe);

    /**
     * A method to try to equip a Bow.
     * @param bow
     */
    void equipBow(Bow bow);

    /**
     * A method to try to equip a Knife.
     * @param knife
     */
    void equipKnife(Knife knife);

    /**
     * A method to try to equip a Staff.
     * @param staff
     */
    void equipStaff(Staff staff);

    /**
     * A method to try to equip a Sword.
     * @param sword
     */
    void equipSword(Sword sword);
}
