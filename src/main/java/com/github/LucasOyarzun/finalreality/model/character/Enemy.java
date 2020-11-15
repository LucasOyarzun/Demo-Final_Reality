package com.github.LucasOyarzun.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 */
public class Enemy extends AbstractCharacter {

  private final int weight;
  private final int attack;
  private final int defense;

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to
   * play.
   * @param name           the character's name
   * @param weight         the character's weight
   * @param lifePoints          the character's lifePoints
   * @param defense        the character's defense
   * @param turnsQueue     the queue with the characters waiting for their turn
   */
  public Enemy(@NotNull final String name, final int weight, final int lifePoints,
               final int defense, final int attack,
               @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, lifePoints, defense, turnsQueue);
    this.weight = weight;
    this.attack = attack;
    this.defense = defense;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor
            .schedule(this::addToQueue, this.getWeight() / 10, TimeUnit.SECONDS);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getWeight());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return getWeight() == enemy.getWeight();
  }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Return the enemy's damage
   */
  public int getDamage() {
    return attack;
  }
}


