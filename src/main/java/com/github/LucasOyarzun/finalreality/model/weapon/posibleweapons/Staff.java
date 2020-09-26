package com.github.LucasOyarzun.finalreality.model.weapon.posibleweapons;

import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.WeaponType;

public class Staff extends AbstractWeapon {
    int magicDamage;

    public Staff(final String name, final int damage, final int weight, final int magicDam) {
        super(name, damage, weight, WeaponType.STAFF);
        this.magicDamage = magicDam;
    }
}
