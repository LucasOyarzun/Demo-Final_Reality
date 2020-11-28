package com.github.LucasOyarzun.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests for all the types of weapons.
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 * @see IWeapon
 */
public abstract class AbstractWeaponTest {
  /**
   * Checks that the class' constructor works properly.
   */
  protected void checkConstruction(final IWeapon expectedWeapon,
                                   final IWeapon testEqualWeapon,
                                   final IWeapon sameClassDifferentWeapon,
                                   final IWeapon differentClassWeapon) {
    assertEquals(expectedWeapon, testEqualWeapon);
    assertNotEquals(sameClassDifferentWeapon, testEqualWeapon);
    assertNotEquals(testEqualWeapon, differentClassWeapon);
    assertEquals(expectedWeapon.hashCode(), testEqualWeapon.hashCode());
  }
}