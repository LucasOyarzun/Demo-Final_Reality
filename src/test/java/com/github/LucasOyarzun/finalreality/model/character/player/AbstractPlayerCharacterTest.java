package com.github.LucasOyarzun.finalreality.model.character.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;
import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacterTest;

/**
 * Set of tests for the {@code GameCharacter} class.
 *
 * @author Ignacio Slater Muñoz.
 */
public abstract class AbstractPlayerCharacterTest extends AbstractCharacterTest {
  protected void checkEquippedWeapon(IPlayerCharacter character, IWeapon weapon) {
    assertNull(character.getEquippedWeapon());
    character.equip(weapon);
    assertEquals(weapon, character.getEquippedWeapon());
  }
}
