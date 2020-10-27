package com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes;

import com.github.LucasOyarzun.finalreality.model.character.player.PlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.WeaponType;

public class Knife extends AbstractWeapon {
    /**
     * Creates a weapon with a name, a base damage and weight.
     *
     * @param name        the knife's name
     * @param damage      the knife's damage
     * @param weight      the knife's weight
     * @see WeaponType
     */
    public Knife(String name, int damage, int weight) {
        super(name, damage, weight, WeaponType.KNIFE);
    }

    public void beEquipedBy(PlayerCharacter character) {
        character.equipKnife(this);
    }
}
