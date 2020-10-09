package com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes;

import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.WeaponType;

public class Staff extends AbstractWeapon {
    /**
     * Creates a weapon with a name, a base damage, weight and magicDamage.
     *
     * @param name
     * @param damage
     * @param weight
     * @param magicDam
     * @see WeaponType
     */
    int magicDamage;
    public Staff(String name, int damage, int weight, int magicDam) {
        super(name, damage, weight, WeaponType.STAFF);
        this.magicDamage = magicDam;
    }
}
