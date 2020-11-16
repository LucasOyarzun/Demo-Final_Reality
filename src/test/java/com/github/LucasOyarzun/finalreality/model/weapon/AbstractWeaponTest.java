package com.github.LucasOyarzun.finalreality.model.weapon;


import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractWeaponTest {

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