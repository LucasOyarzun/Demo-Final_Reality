package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.weapon.weaponClasses.Staff;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackMageTest extends AbstractPlayerCharacterTest {

  private static final String BLACK_MAGE_NAME = "Vivi";
  private BlackMage vivi;
  private Staff staff = new Staff("staff", 1, 10);
  /**
   * Setup method.
   * Creates a new character named Vivi with 10 speed and links it to a turn queue.
   */
  @BeforeEach
  void setUp() {
    super.basicSetUp();
    vivi = new BlackMage(BLACK_MAGE_NAME, turns);
  }

  @Test
  void waitTurnTest() {
    vivi.equip(staff);
    checkWaitTurn(vivi);
  }

  @Test
  void constructorTest() {
    checkConstruction(new BlackMage(BLACK_MAGE_NAME, turns),
        vivi,
        new BlackMage("Different name", turns),
        new Knight(BLACK_MAGE_NAME, turns));
  }

  @Test
  void equippedWeaponTest() {
    checkEquippedWeapon(vivi, staff);
  }
}