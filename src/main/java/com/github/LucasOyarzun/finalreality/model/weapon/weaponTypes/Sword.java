package com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes;

import com.github.LucasOyarzun.finalreality.model.character.player.PlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.WeaponType;

public class Sword extends AbstractWeapon {
    /**
     * Creates a weapon with a name, a base damage and weight.
     *
     * @param name        the sword's name
     * @param damage      the sword's damage
     * @param weight      the sword's weight
     * @see WeaponType
     */
    public Sword(String name, int damage, int weight) {
        super(name, damage, weight, WeaponType.SWORD);
    }

    /**
     * Equip this Sword to a character
     * @param character
     */
    public void beEquipedBy(PlayerCharacter character) {
        character.equipSword(this);
    }
}
