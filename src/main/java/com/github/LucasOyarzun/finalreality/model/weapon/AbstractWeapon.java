package com.github.LucasOyarzun.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public class AbstractWeapon {

  private final String name;
  private final int damage;
  private final int weight;
  private final WeaponType type;
  private final int magicDamage;

  /**
   * Creates a weapon with a name, a base damage, speed and it's type.
   * @see WeaponType
   */
  public AbstractWeapon(final String name, final int damage, final int weight,
                        final WeaponType type) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
    this.type = type;
    magicDamage = 0;
  }

  public String getName() {
    return name;
  }

  public int getDamage() {
    return damage;
  }

  public int getWeight() {
    return weight;
  }

  public WeaponType getType() {
    return type;
  }

  public int getMagicDamage() {
    return this.magicDamage;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractWeapon)) {
      return false;
    }
    final AbstractWeapon weapon = (AbstractWeapon) o;
    return getDamage() == weapon.getDamage() &&
        getWeight() == weapon.getWeight() &&
        getName().equals(weapon.getName()) &&
        getType() == weapon.getType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getDamage(), getWeight(), getType());
  }
}
