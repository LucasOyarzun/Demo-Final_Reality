package com.github.LucasOyarzun.finalreality.model.character;

import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Abstract class containing the common tests for all the types of characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 * @see ICharacter
 */
public abstract class AbstractCharacterTest {
  protected BlockingQueue<ICharacter> turns;
  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */



  protected void checkConstruction(final ICharacter expectedCharacter,
      final ICharacter testEqualCharacter,
      final ICharacter sameClassDifferentCharacter,
      final ICharacter differentClassCharacter) {
    assertEquals(expectedCharacter, testEqualCharacter);
    assertNotEquals(sameClassDifferentCharacter, testEqualCharacter);
    assertNotEquals(testEqualCharacter, differentClassCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());
  }

  protected void basicSetUp() {
    turns = new LinkedBlockingQueue<>();
  }
}
