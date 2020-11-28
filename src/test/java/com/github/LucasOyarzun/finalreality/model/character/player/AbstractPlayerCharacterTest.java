package com.github.LucasOyarzun.finalreality.model.character.player;

import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacterTest;
import com.github.LucasOyarzun.finalreality.model.character.Enemy;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.*;

/**
 * Set of tests for the {@code GameCharacter} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public abstract class AbstractPlayerCharacterTest extends AbstractCharacterTest {
  protected Axe testAxe;
  protected Staff testStaff;
  protected Sword testSword;
  protected Bow testBow;
  protected Knife testKnife;

  protected Enemy goblin;
  protected Enemy immortal;
  protected Enemy demon;

  private static final String AXE_NAME = "Test Axe";
  private static final String STAFF_NAME = "Test Staff";
  private static final String SWORD_NAME = "Test Sword";
  private static final String BOW_NAME = "Test Bow";
  private static final String KNIFE_NAME = "Test Knife";
  private static final int DAMAGE = 15;
  private static final int SPEED = 10;

  /**
   * Creates new weapons and Enemies.
   */
  protected void basicSetUp() {
    super.basicSetUp();
    testAxe = new Axe(AXE_NAME, DAMAGE, SPEED);
    testStaff = new Staff(STAFF_NAME, DAMAGE, SPEED, 100);
    testSword = new Sword(SWORD_NAME, DAMAGE, SPEED);
    testBow = new Bow(BOW_NAME, DAMAGE, SPEED);
    testKnife = new Knife(KNIFE_NAME, DAMAGE, SPEED);

    goblin = new Enemy("goblin",50,0,10,5, turns);
    demon = new Enemy("demon", 100, 10, 50, 40, turns);
    immortal = new Enemy("immortal", 100, 50, 20, 10, turns);
  }
}