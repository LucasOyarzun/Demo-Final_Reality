package com.github.LucasOyarzun.finalreality.model.weapon;

import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;

import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public abstract class AbstractWeapon implements IWeapon {

  private final String name;
  private final int damage;
  private final int weight;

  /**
   * Creates a weapon with a name, a base damage, speed and it's type.
   *
   * @param name   the weapon's name
   * @param damage the weapon's damage
   * @param weight the weapon's weight
   */
  public AbstractWeapon(final String name, final int damage, final int weight) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public int getWeight() {
    return weight;
  }

  @Override
  public int hashCode() {
    return Objects.hash(AbstractCharacter.class, getName(),
                        getDamage(), getWeight());
  }
  /**
   * Compares an object and return if it's equals to this weapon
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    final AbstractWeapon that = (AbstractWeapon) o;
    return this.hashCode() == that.hashCode();
  }
}
