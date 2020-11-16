package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Bow;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Staff;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Sword;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * @author Ignacio Slater Muñoz.
 */
public class Thief extends AbstractPlayerCharacter implements IPlayerCharacter {

    /**
     * Creates a new character.
     *
     * @param name               the character's name
     * @param lifePoints         the knight's lifePoints
     * @param defense            the knight's defense
     * @param turnsQueue         the queue with the characters waiting for their turn
     */
    public Thief(@NotNull final String name, int lifePoints, int defense ,
                 @NotNull final BlockingQueue<ICharacter> turnsQueue) {
        super(name, lifePoints, defense, turnsQueue);
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof Thief && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Thief.class);
    }

    @Override
    public void equipStaff(Staff staff) {
        this.equippedWeapon = staff;
    }

    @Override
    public void equipSword(Sword sword) {
        this.equippedWeapon = sword;
    }

    @Override
    public void equipBow(Bow bow) {
        this.equippedWeapon = bow;
    }
}