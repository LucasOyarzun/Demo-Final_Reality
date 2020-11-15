package com.github.LucasOyarzun.finalreality.model.weapon.weaponClasses;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

public class Knife extends AbstractWeapon {
    /**
     * Creates a weapon with a name, a base damage and weight.
     *
     * @param name   the knife's name
     * @param damage the knife's damage
     * @param weight the knife's weight
     */
    public Knife(String name, int damage, int weight) {
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