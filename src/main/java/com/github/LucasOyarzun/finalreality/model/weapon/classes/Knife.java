package com.github.LucasOyarzun.finalreality.model.weapon.classes;

import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

/**
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public class Knife extends AbstractWeapon {
    /**
     * Creates a weapon with a name, a base damage and weight.
     *
     * @param name        the knife's name
     * @param damage      the knife's damage
     * @param weight      the knife's weight
     */
    public Knife(final String name, final int damage, final int weight) {
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

    @Override
    public void beEquipedBy(IPlayerCharacter character) {
        character.equipKnife(this);
    }
}