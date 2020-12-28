package com.github.LucasOyarzun.finalreality.model.weapon.classes;

import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.InvalidEquipException;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

/**
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public class Axe extends AbstractWeapon {

    /**
     * Creates an axe with a name, a base damage and weight.
     *
     * @param name        the axe's name
     * @param damage      the axe's damage
     * @param weight      the axe's weight
     */
    public Axe(final String name, final int damage, final int weight) {
        super(name, damage, weight);
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof Axe && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Axe.class);
    }

    @Override
    public void beEquipedBy(IPlayerCharacter character) throws InvalidEquipException {
        character.equipAxe(this);
    }
}