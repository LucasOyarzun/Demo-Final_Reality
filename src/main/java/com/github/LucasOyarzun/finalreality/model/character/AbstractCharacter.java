package com.github.LucasOyarzun.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public abstract class AbstractCharacter implements ICharacter {

    protected final BlockingQueue<ICharacter> turnsQueue;
    protected final String name;
    protected int defense;
    protected int lifePoints;
    protected ScheduledExecutorService scheduledExecutor;

    protected AbstractCharacter( @NotNull String name, int lifeP, int def,
                                 @NotNull BlockingQueue<ICharacter> turnsQueue) {
        this.turnsQueue = turnsQueue;
        this.name = name;
        this.lifePoints = lifeP;
        this.defense = def;
    }

    /**
     * Adds this character to the turns queue.
     */
    protected void addToQueue() {
        turnsQueue.add(this);
        scheduledExecutor.shutdown();
    }

    @Override
    public int hashCode() {
        return Objects.hash(AbstractCharacter.class, getName());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractCharacter)) {
            return false;
        }
        final AbstractCharacter that = (AbstractCharacter) o;
        return getName().equals(that.getName());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public int getLifePoints() {
        return lifePoints;
    }

    @Override
    public void loseLife(int value) {
        if ((this.lifePoints - value > 0)) {
            this.lifePoints -= value;
        } else {
            this.lifePoints = 0;
        }
    }

    @Override
    public boolean isAlive() {
        return this.lifePoints > 0;
    }
}
