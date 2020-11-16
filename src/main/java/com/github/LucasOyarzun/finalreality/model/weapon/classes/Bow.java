package com.github.LucasOyarzun.finalreality.model.weapon.classes;

import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

/**
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public class Bow extends AbstractWeapon {

    /**
     * Creates a weapon with a name, a base damage and weight.
     *
     * @param name        the bow's name
     * @param damage      the bow's damage
     * @param weight      the bow's weight
     */
    public Bow(final String name, final int damage, final int weight) {
        super(name, damage, weight);
    }
    @Override
    public boolean equals(final Object o) {
        return o instanceof Bow && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Bow.class);
    }

    @Override
    public void beEquipedBy(IPlayerCharacter character) {
        character.equipBow(this);
    }
}