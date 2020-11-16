package com.github.LucasOyarzun.finalreality.model.character;

import com.github.LucasOyarzun.finalreality.model.character.player.classes.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";
  private Enemy goblin;
  private Knight player;
  private Thief player2;
  private Engineer player3;

  @BeforeEach
  void setUp() {
    basicSetUp();
    goblin = new Enemy(ENEMY_NAME,100,10, 10, 40, turns);
    player = new Knight("Player", 100, 10, turns);
    player2 = new Thief("Player2", 100, 15, turns);
    player3 = new Engineer("Player3", 100, 50, turns);
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, 100, 10,
                          10, 10, turns), goblin,
            new Enemy(ENEMY_NAME, 100, 10, 11, 10, turns),
            new Knight(ENEMY_NAME, 100, 10, turns),
            player);
  }

  @Test
  void waitTurnTest() {
    assertTrue(turns.isEmpty());
    goblin.waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      assertEquals(0, turns.size());
      Thread.sleep(200);
      assertEquals(1, turns.size());
      assertEquals(goblin, turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Test the attack and equip methods in Enemy
   */
  @Test
  void attackTest() {
    /*Attack until 0 lifePoints */
    assertTrue(player.isAlive());
    goblin.attack(player);
    assertTrue(player.isAlive());
    assertEquals(70, player.getLifePoints());
    goblin.attack(player);
    assertTrue(player.isAlive());
    assertEquals(40, player.getLifePoints());
    goblin.attack(player);
    assertTrue(player.isAlive());
    assertEquals(10, player.getLifePoints());
    goblin.attack(player);
    assertFalse(player.isAlive());
    assertEquals(0, player.getLifePoints());

    /*Attack whit different player's defense */
    assertTrue(player2.isAlive());
    goblin.attack(player2);
    assertTrue(player2.isAlive());
    assertEquals(75, player2.getLifePoints());

    /*Attack whit player's defense bigger than enemy's attack */
    assertTrue(player3.isAlive());
    goblin.attack(player3);
    assertTrue(player3.isAlive());
    assertEquals(100, player3.getLifePoints());

  }

}