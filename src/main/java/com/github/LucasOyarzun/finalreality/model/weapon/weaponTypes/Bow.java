package com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes;

import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.WeaponType;

public class Bow extends AbstractWeapon {
    /**
     * Creates a weapon with a name, a base damage and weight.
     *
     * @param name        the bow's name
     * @param damage      the bow's damage
     * @param weight      the bow's weight
     * @see WeaponType
     */
    public Bow(String name, int damage, int weight) {
        super(name, damage, weight, WeaponType.BOW);
    }
}
