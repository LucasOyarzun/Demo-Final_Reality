package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Axe;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Bow;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public class Engineer extends AbstractPlayerCharacter {

    /**
     * Creates a new character.
     *
     * @param name               the character's name
     * @param lifePoints         the engineer's lifePoints
     * @param defense            the engineer's defense
     * @param turnsQueue         the queue with the characters waiting for their turn
     */
    public Engineer(@NotNull final String name, int lifePoints, int defense,
                    @NotNull final BlockingQueue<ICharacter> turnsQueue) {
        super(name, lifePoints, defense, turnsQueue);
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof Engineer && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Engineer.class);
    }

    @Override
    public void equipAxe(Axe axe) {
        this.equippedWeapon = axe;
    }

    @Override
    public void equipBow(Bow bow) {
        this.equippedWeapon = bow;
    }

    @Override
    public String getClassName() {
        return "Engineer";
    }
}
