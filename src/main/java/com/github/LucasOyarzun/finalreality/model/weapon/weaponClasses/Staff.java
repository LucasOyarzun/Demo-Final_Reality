package com.github.LucasOyarzun.finalreality.model.weapon.weaponClasses;

import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

public class Staff extends AbstractWeapon {
    private final int magicDamage;
  /**
   * Creates a weapon with a name, a base damage, weight and magicDamage.
   *
   * @param name        the staff's name
   * @param damage      the staff's damage
   * @param weight      the staff's weight
   * @param magicDamage    the staff's magicDamage
   */

  public Staff(final String name, final int damage, final int weight, final int magicDamage) {
    super(name, damage, weight);
    this.magicDamage = magicDamage;
  }

  @Override
  public boolean equals(final Object o) {
    return o instanceof Staff && super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), Staff.class);
  }
}
