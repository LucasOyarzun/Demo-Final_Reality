package com.github.LucasOyarzun.finalreality.model.character;

import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import com.github.LucasOyarzun.finalreality.model.character.player.PlayerCharacter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class EnemyTest extends AbstractCharacterTest {
  protected List<Enemy> testEnemies;
  private static final String ENEMY_NAME = "Goblin";

  @BeforeEach
  void setUp() {
    basicSetUp();
    testEnemies = new ArrayList<>();
    testEnemies.add(new Enemy(ENEMY_NAME, turns, 100, 10, 10, 10));
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, turns, 100, 10, 10, 10),
        testEnemies.get(0),
            new Enemy(ENEMY_NAME, turns, 100, 10, 10, 11),
        new PlayerCharacter(ENEMY_NAME, turns, CharacterClass.THIEF, 100, 10));
  }

  @Test
  void waitTurnTest() {
    Assertions.assertTrue(turns.isEmpty());
    testEnemies.get(0).waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertEquals(testEnemies.get(0), turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}