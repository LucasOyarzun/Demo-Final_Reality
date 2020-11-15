package com.github.LucasOyarzun.finalreality.model.weapon.weaponClasses;
;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

public class Bow extends AbstractWeapon {
    /**
     * Creates a weapon with a name, a base damage and weight.
     *
     * @param name        the bow's name
     * @param damage      the bow's damage
     * @param weight      the bow's weight
     */
    public Bow(String name, int damage, int weight) {
        super(name, damage, weight);
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof Knife && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Knife.class);
    }
}