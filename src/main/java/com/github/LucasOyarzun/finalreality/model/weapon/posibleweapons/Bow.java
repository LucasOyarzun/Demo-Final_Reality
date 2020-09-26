package com.github.LucasOyarzun.finalreality.model.weapon.posibleweapons;

import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.WeaponType;

public class Bow extends AbstractWeapon {

    public Bow(final String name, final int damage, final int weight) {
        super(name, damage, weight, WeaponType.BOW);
    }
}
