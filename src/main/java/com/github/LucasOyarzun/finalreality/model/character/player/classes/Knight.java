package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Axe;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Knife;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Sword;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public class Knight extends AbstractPlayerCharacter {

    /**
     * Creates a new character.
     *
     * @param name               the character's name
     * @param lifePoints         the knight's lifePoints
     * @param defense            the knight's defense
     * @param turnsQueue         the queue with the characters waiting for their turn
     */
    public Knight(@NotNull final String name, int lifePoints, int defense,
                  @NotNull final BlockingQueue<ICharacter> turnsQueue) {
        super(name, lifePoints, defense, turnsQueue);
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof Knight && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Knight.class);
    }

    @Override
    public void equipKnife(Knife knife) {
        this.equippedWeapon = knife;
    }

    @Override
    public void equipSword(Sword sword) {
        this.equippedWeapon = sword;
    }

    @Override
    public void equipAxe(Axe axe) {
        this.equippedWeapon = axe;
    }
}
