package com.github.LucasOyarzun.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import com.github.LucasOyarzun.finalreality.controller.GameController;
import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
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

    @Override
    public void addToQueue() {
        turnsQueue.add(this);
        scheduledExecutor.shutdown();

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
        if (value >=0) {
            if ((this.lifePoints - value > 0)) {
                this.lifePoints -= value;
            } else {
                this.lifePoints = 0;
            }
        }
    }

    @Override
    public void attack(ICharacter character) {
        character.beAttackedBy(this);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
    }

    @Override
    public void attackPlayerCharacter(IPlayerCharacter character) {
    }

    @Override
    public void beAttackedBy(ICharacter character) {
    }

    @Override
    public void controllerAttack(GameController controller){
    }

    @Override
    public boolean isAlive() {
        return this.lifePoints > 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(AbstractCharacter.class, getName(),
                getLifePoints(), getDefense());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        final AbstractCharacter that = (AbstractCharacter) o;
        return this.hashCode() == that.hashCode();
    }
}
