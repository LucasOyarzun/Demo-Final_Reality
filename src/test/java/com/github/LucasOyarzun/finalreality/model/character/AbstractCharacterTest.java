package com.github.LucasOyarzun.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.jupiter.api.Assertions;

/**
 * Abstract class containing the common tests for all the types of characters.
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 * @see ICharacter
 */
public abstract class AbstractCharacterTest {

  protected BlockingQueue<ICharacter> turns;

  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */
  protected void checkWaitTurn(ICharacter character) {
    Assertions.assertTrue(turns.isEmpty());
    character.waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertEquals(character, turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks that the class' constructor works properly.
   */
  protected void checkConstruction(final ICharacter expectedCharacter,
                                   final ICharacter testEqualCharacter,
                                   final ICharacter sameClassDifferentCharacter,
                                   final ICharacter differentClassCharacter,
                                   final ICharacter opponent) {
    assertEquals(expectedCharacter, testEqualCharacter);
    assertNotEquals(sameClassDifferentCharacter, testEqualCharacter);
    assertNotEquals(differentClassCharacter, testEqualCharacter);
    assertNotEquals(opponent, testEqualCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());
  }

  /**
   * Create a LinkedBlockingQueue of turns that will be used by characters.
   */
  protected void basicSetUp() {
    turns = new LinkedBlockingQueue<>();
  }
}