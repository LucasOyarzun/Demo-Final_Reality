package com.github.LucasOyarzun.finalreality.model.weapon;

import com.github.LucasOyarzun.finalreality.model.character.player.platerTypes.Thief;
import com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWeaponTest {

  private static final String AXE_NAME = "Test Axe";
  private static final String STAFF_NAME = "Test Staff";
  private static final String SWORD_NAME = "Test Sword";
  private static final String BOW_NAME = "Test Bow";
  private static final String KNIFE_NAME = "Test Knife";
  private static final int DAMAGE = 15;
  private static final int SPEED = 10;

  private Axe testAxe;
  private Staff testStaff;
  private Sword testSword;
  private Bow testBow;
  private Knife testKnife;

  @BeforeEach
  void setUp() {
    testAxe = new Axe(AXE_NAME, DAMAGE, SPEED);
    testStaff = new Staff(STAFF_NAME, DAMAGE, SPEED, 100);
    testSword = new Sword(SWORD_NAME, DAMAGE, SPEED);
    testBow = new Bow(BOW_NAME, DAMAGE, SPEED);
    testKnife = new Knife(KNIFE_NAME, DAMAGE, SPEED);
  }

  /**
   * Checks that the class' constructor works properly.
   */
  @Test
  void constructorTest() {

    var expectedAxe = new AbstractWeapon(AXE_NAME, DAMAGE, SPEED, WeaponType.AXE);
    var expectedStaff = new AbstractWeapon(STAFF_NAME, DAMAGE, SPEED, WeaponType.STAFF);
    var expectedSword = new AbstractWeapon(SWORD_NAME, DAMAGE, SPEED, WeaponType.SWORD);
    var expectedBow = new AbstractWeapon(BOW_NAME, DAMAGE, SPEED, WeaponType.BOW);
    var expectedKnife = new AbstractWeapon(KNIFE_NAME, DAMAGE, SPEED, WeaponType.KNIFE);
    assertEquals(expectedAxe, testAxe);
    assertEquals(expectedAxe.hashCode(), testAxe.hashCode());
    assertEquals(expectedStaff, testStaff);
    assertEquals(expectedStaff.hashCode(), testStaff.hashCode());
    assertEquals(expectedSword, testSword);
    assertEquals(expectedSword.hashCode(), testSword.hashCode());
    assertEquals(expectedBow, testBow);
    assertEquals(expectedBow.hashCode(), testBow.hashCode());
    assertEquals(expectedKnife, testKnife);
    assertEquals(expectedKnife.hashCode(), testKnife.hashCode());
  }

  /**
   * Checks that the class' equals method works properly.
   */
  @Test
  void equalsTest() {
    var expectedAxe = new AbstractWeapon(AXE_NAME, DAMAGE, SPEED, WeaponType.AXE);
    var expectedAxe2 = new AbstractWeapon("holaa", 20, 15, WeaponType.AXE);
    var expectedAxe3 = new AbstractWeapon("Test Axe", 15, 15, WeaponType.KNIFE);
    var expectedAxe4 = new AbstractWeapon("Test Axe", 15, 10, WeaponType.KNIFE);
    assertNotEquals(testBow, testAxe);
    assertEquals(expectedAxe, testAxe);
    assertEquals(testAxe, testAxe);
    assertNotEquals(testAxe, "1");
    assertNotEquals(testAxe, expectedAxe2);
    assertNotEquals(testAxe, expectedAxe3);
    assertNotEquals(testAxe, expectedAxe4);

  }
}