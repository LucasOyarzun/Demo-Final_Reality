package com.github.LucasOyarzun.finalreality.model.weapon.posibleweapons;

import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.WeaponType;

public class Staff extends AbstractWeapon {

    public Staff(final String name, final int damage, final int weight) {
        super(name, damage, weight, WeaponType.STAFF);
    }
}
