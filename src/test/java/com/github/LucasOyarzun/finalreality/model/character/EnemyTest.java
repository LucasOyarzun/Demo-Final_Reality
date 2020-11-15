package com.github.LucasOyarzun.finalreality.model.character;

import com.github.LucasOyarzun.finalreality.model.character.player.classes.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";
  private Enemy enemy;

  @BeforeEach
  void setUp() {
    basicSetUp();
    enemy = new Enemy(ENEMY_NAME, 10, turns);
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, 10, turns),
        enemy,
        new Enemy(ENEMY_NAME, 11, turns),
        new Knight(ENEMY_NAME, turns));
  }

  @Test
  void waitTurnTest() {
    checkWaitTurn(enemy);
  }
}