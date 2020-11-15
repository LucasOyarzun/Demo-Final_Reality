package com.github.LucasOyarzun.finalreality.model.character.player;

import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements
    IPlayerCharacter {

  protected IWeapon equippedWeapon = null;

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name
   * @param lifePoints
   *     the lifePoints of this character
   * @param defense
   *     the defense of this character
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   */
  public AbstractPlayerCharacter(@NotNull String name, final int lifePoints, final int defense,
      @NotNull BlockingQueue<ICharacter> turnsQueue) {
    super(name, lifePoints, defense, turnsQueue);
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor
        .schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(super.hashCode(), AbstractPlayerCharacter.class, getEquippedWeapon());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractPlayerCharacter)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    final AbstractPlayerCharacter that = (AbstractPlayerCharacter) o;
    return Objects.equals(getEquippedWeapon(), that.getEquippedWeapon());
  }

  @Override
  public void equip(IWeapon weapon) {
    // Esto se tiene que cambiar para la entrega parcial 3
    this.equippedWeapon = weapon;
  }

  /**
   * Return character's equipped weapon
   */
  @Override
  public IWeapon getEquippedWeapon() {
    return equippedWeapon;
  }

  /**
   * Return the player's damage
   */
  public int getDamage() {
    return equippedWeapon.getDamage();
  }
}
