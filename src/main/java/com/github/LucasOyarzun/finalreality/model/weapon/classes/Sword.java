package com.github.LucasOyarzun.finalreality.model.weapon.classes;

import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

/**
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public class Sword extends AbstractWeapon {

    /**
     * Creates a weapon with a name, a base damage and weight.
     *
     * @param name        the sword's name
     * @param damage      the sword's damage
     * @param weight      the sword's weight
     */
    public Sword(final String name, final int damage, final int weight) {
        super(name, damage, weight);
    }
    @Override
    public boolean equals(final Object o) {
        return o instanceof Sword && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Sword.class);
    }

    @Override
    public void beEquipedBy(IPlayerCharacter character) {
        character.equipSword(this);
    }
}