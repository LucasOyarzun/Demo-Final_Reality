package com.github.LucasOyarzun.finalreality.model.weapon.classes;

import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

/**
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public class Staff extends AbstractWeapon {
    private final int magicDamage;
    /**
     * Creates a weapon with a name, a base damage and weight.
     *
     * @param name        the staff's name
     * @param damage      the staff's damage
     * @param weight      the staff's weight
     * @param magicDamage the staff's magic Damage
     */
    public Staff(final String name, final int damage, final int weight, final int magicDamage) {
        super(name, damage, weight);
        this.magicDamage = magicDamage;
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof Staff && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Staff.class);
    }

    @Override
    public void beEquipedBy(IPlayerCharacter character) {
        character.equipStaff(this);
    }
}