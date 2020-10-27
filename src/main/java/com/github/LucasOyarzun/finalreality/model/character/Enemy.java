package com.github.LucasOyarzun.finalreality.model.character;

import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import com.github.LucasOyarzun.finalreality.model.character.player.PlayerCharacter;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Lucas Oyarzun Mendez
 */
public class Enemy extends AbstractCharacter {

  private final int weight;
  private final int attack;

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to
   * play.
   * @param name           the character's name
   * @param turnsQueue     the queue with the characters waiting for their turn
   * @param lifeP          the character's lifePoints
   * @param def            the character's defense
   * @param weight         the character's weight
   */
  public Enemy(@NotNull final String name, @NotNull final BlockingQueue<ICharacter> turnsQueue,
               int lifeP,int def, int attack, final int weight) {
    super(turnsQueue, name, CharacterClass.ENEMY, lifeP, def);
    this.weight = weight;
    this.attack = attack;
  }

  /**
   * Returns the enemy's weight.
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

  /**
   * A method to attack a PlayerCharacter
   * @param player the player that the enemy will attack
   */
  public void attack(PlayerCharacter player) {
    if (player.isAlive()) {
      player.loseLife(this.getDamage() - player.getDefense());
    }
  }
  /**Compares an object and return if it's equals to this enemy*/
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

  @Override
  public int hashCode() {
    return Objects.hash(getWeight());
  }
}
