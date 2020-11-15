package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacter;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ignacio Slater Muñoz.
 */
public class BlackMage extends AbstractPlayerCharacter {

    /**
     * Creates a new character.
     *
     * @param name               the character's name
     * @param lifePoints         the knight's lifePoints
     * @param defense            the knight's defense
     * @param turnsQueue         the queue with the characters waiting for their turn
     */
    public BlackMage(@NotNull final String name, @NotNull final BlockingQueue<ICharacter> turnsQueue,
                     int lifePoints, int defense) {
        super(name, lifePoints, defense, turnsQueue);
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof BlackMage && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), BlackMage.class);
    }
}