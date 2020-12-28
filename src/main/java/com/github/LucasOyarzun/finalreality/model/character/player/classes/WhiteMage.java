package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Staff;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */

public class WhiteMage extends AbstractPlayerCharacter {
    private int mana;
    /**
     * Creates a new character.
     *
     * @param name               the character's name
     * @param lifePoints         the white mage's lifePoints
     * @param defense            the white mage's defense
     * @param mana               the white mage's mana
     * @param turnsQueue         the queue with the characters waiting for their turn
     */
    public WhiteMage(@NotNull final String name, int lifePoints, int defense, int mana,
                     @NotNull final BlockingQueue<ICharacter> turnsQueue) {
        super(name, lifePoints, defense, turnsQueue);
        this.mana = mana;
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof WhiteMage && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), WhiteMage.class);
    }

    @Override
    public void equipStaff(Staff staff) {
        this.equippedWeapon = staff;
    }

    @Override
    public String getClassName() {
        return "White Mage";
    }
}