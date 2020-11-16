package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Knife;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Staff;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ignacio Slater Muñoz.
 */
public class BlackMage extends AbstractPlayerCharacter implements IPlayerCharacter {
    private int mana;
    /**
     * Creates a new character.
     *
     * @param name               the character's name
     * @param lifePoints         the black mage's lifePoints
     * @param defense            the black mage's defense
     * @param mana               the black mage's mana
     * @param turnsQueue         the queue with the characters waiting for their turn
     */
    public BlackMage(@NotNull final String name, int lifePoints, int defense, int mana,
                     @NotNull final BlockingQueue<ICharacter> turnsQueue) {
        super(name, lifePoints, defense, turnsQueue);
        this.mana = mana;
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof BlackMage && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), BlackMage.class);
    }

    @Override
    public void equipStaff(Staff staff) {
        this.equippedWeapon = staff;
    }

    @Override
    public void equipKnife(Knife knife) {
        this.equippedWeapon = knife;
    }
}