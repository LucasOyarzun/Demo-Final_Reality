package com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes;

import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.WeaponType;

public class Axe extends AbstractWeapon {
    /**
     * Creates an axe with a name, a base damage and weight.
     *
     * @param name        the axe's name
     * @param damage      the axe's damage
     * @param weight      the axe's weight
     * @see WeaponType
     */
    public Axe(String name, int damage, int weight) {
        super(name, damage, weight, WeaponType.AXE);
    }
}
