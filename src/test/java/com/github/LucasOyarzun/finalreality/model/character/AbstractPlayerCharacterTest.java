package com.github.LucasOyarzun.finalreality.model.character;

import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacter;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.github.LucasOyarzun.finalreality.model.character.player.platerTypes.*;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Set of tests for the {@code GameCharacter} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 * @see AbstractPlayerCharacter
 */
class AbstractPlayerCharacterTest extends AbstractCharacterTest {

  private static final String BLACK_MAGE_NAME = "Vivi";
  private static final String KNIGHT_NAME = "Adelbert";
  private static final String WHITE_MAGE_NAME = "Eiko";
  private static final String ENGINEER_NAME = "Cid";
  private static final String THIEF_NAME = "Zidane";
  private Map<CharacterClass, String> characterNames;
  private AbstractWeapon testAbstractWeapon;
  protected List<AbstractPlayerCharacter> testCharacters;

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

  /**
   * Setup method.
   * Creates 5 new characters and links them to a turn queue.
   * Creates 6 new weapons
   */
  @BeforeEach
  void setUp() {
    testCharacters = new ArrayList<>();

    testAxe = new Axe(AXE_NAME, DAMAGE, SPEED);
    testStaff = new Staff(STAFF_NAME, DAMAGE, SPEED, 100);
    testSword = new Sword(SWORD_NAME, DAMAGE, SPEED);
    testBow = new Bow(BOW_NAME, DAMAGE, SPEED);
    testKnife = new Knife(KNIFE_NAME, DAMAGE, SPEED);

    super.basicSetUp();
    characterNames = new EnumMap<>(CharacterClass.class);
    characterNames.put(CharacterClass.BLACK_MAGE, BLACK_MAGE_NAME);
    characterNames.put(CharacterClass.KNIGHT, KNIGHT_NAME);
    characterNames.put(CharacterClass.WHITE_MAGE, WHITE_MAGE_NAME);
    characterNames.put(CharacterClass.ENGINEER, ENGINEER_NAME);
    characterNames.put(CharacterClass.THIEF, THIEF_NAME);

    testCharacters.add(new BlackMage(BLACK_MAGE_NAME, turns, 100, 10, 50));
    testCharacters.add(new Knight(KNIGHT_NAME, turns, 100, 10));
    testCharacters.add(new WhiteMage(WHITE_MAGE_NAME, turns, 100, 10, 50));
    testCharacters.add(new Engineer(ENGINEER_NAME, turns, 100, 10));
    testCharacters.add(new Thief(THIEF_NAME, turns, 100, 10));
    }

  /**
   * Test the waitTurn method
   */
  @Test
  void waitTurnTest() {
    Assertions.assertTrue(turns.isEmpty());
    testCharacters.get(0).equip(testStaff);
    testCharacters.get(0).waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertEquals(testCharacters.get(0), turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  /**
   * Checks that the class' constructor and equals method works properly.
   */
  @Test
  void constructorTest() {
    var enemy = new Enemy("Enemy", turns, 100, 10, 10, 10);
    for (var character :
        testCharacters) {
      var characterClass = character.getCharacterClass();
      var characterName = characterNames.get(characterClass);
      checkConstruction(new AbstractPlayerCharacter(characterName, turns, characterClass, 100, 10),
          character,
          new AbstractPlayerCharacter("Test", turns, characterClass, 100, 10),
          new AbstractPlayerCharacter(characterName, turns,
              characterClass == CharacterClass.THIEF ? CharacterClass.BLACK_MAGE
                  : CharacterClass.THIEF, 100, 10));
      assertNotEquals(character, enemy);
    }

  }

  /**
   * Test the method equip whit Black Mage
   */
  @Test
  void equipWeaponBlackMageTest() {
    var Bmage = testCharacters.get(0);
    assertNull(Bmage.getEquippedWeapon());
    Bmage.equip(testAxe);
    assertNull(Bmage.getEquippedWeapon());
    Bmage.equip(testStaff);
    assertEquals(testStaff, Bmage.getEquippedWeapon());
    Bmage.equip(testKnife);
    assertEquals(testKnife, Bmage.getEquippedWeapon());
  }

  /**
   * Test the method equip whit Knight
   */
  @Test
  void equipWeaponKnightTest() {
    var kina = testCharacters.get(1);
    assertNull(kina.getEquippedWeapon());
    kina.equip(testStaff);
    assertNull(kina.getEquippedWeapon());
    kina.equip(testSword);
    assertEquals(testSword, kina.getEquippedWeapon());
    kina.equip(testAxe);
    assertEquals(testAxe, kina.getEquippedWeapon());
    kina.equip(testKnife);
    assertEquals(testKnife, kina.getEquippedWeapon());
  }

  /**
   * Test the method equip whit White Mage
   */
  @Test
  void equipWeaponWhiteMageTest() {
    var Wmage = testCharacters.get(2);
    assertNull(Wmage.getEquippedWeapon());
    Wmage.equip(testAxe);
    assertNull(Wmage.getEquippedWeapon());
    Wmage.equip(testStaff);
    assertEquals(testStaff, Wmage.getEquippedWeapon());
  }

  /**
   * Test the method equip whit Engineer
   */
  @Test
  void equipWeaponEngineerTest() {
    var engi = testCharacters.get(3);
    assertNull(engi.getEquippedWeapon());
    engi.equip(testStaff);
    assertNull(engi.getEquippedWeapon());
    engi.equip(testAxe);
    assertEquals(testAxe, engi.getEquippedWeapon());
    engi.equip(testBow);
    assertEquals(testBow, engi.getEquippedWeapon());

    testStaff.beEquipedBy(engi);
    assertEquals(testBow, engi.getEquippedWeapon());
    testAxe.beEquipedBy(engi);
    assertEquals(testAxe, engi.getEquippedWeapon());
  }

  /**
   * Test the method equip whit Thief
   */
  @Test
  void equipWeaponThiefTest() {
    var ladron = testCharacters.get(4);
    assertNull(ladron.getEquippedWeapon());
    ladron.equip(testAxe);
    assertNull(ladron.getEquippedWeapon());
    ladron.equip(testStaff);
    assertEquals(testStaff, ladron.getEquippedWeapon());
    ladron.equip(testBow);
    assertEquals(testBow, ladron.getEquippedWeapon());
    ladron.equip(testSword);
    assertEquals(testSword, ladron.getEquippedWeapon());
    testStaff.beEquipedBy(ladron);
    assertEquals(testStaff, ladron.getEquippedWeapon());
  }

  /**
   * Test the attack and equip methods in PlayerCharacter and Enemy
   */
  @Test
  void attackTest() {
    var thief = new Thief("Jose", turns, 5, 2);
    var thief2 = new Thief("Jose", turns, 5, 2);
    var enemy1 =new Enemy("Demon", turns, 100, 15, 10, 10);
    var enemy2 = new Enemy("Wolf", turns, 100, 10, 3, 10 );

    assertTrue(enemy1.isAlive());
    enemy1.attack(thief);
    assertEquals(0, thief.getLifePoints());
    assertFalse(thief.isAlive());

    enemy2.attack(thief2);
    assertEquals(4, thief2.getLifePoints());
    enemy2.attack(thief2);
    assertEquals(3, thief2.getLifePoints());
    assertTrue(thief2.isAlive());
    enemy1.attack(thief2);
    assertEquals(0, thief2.getLifePoints());
    assertFalse(thief2.isAlive());

    /**Attack when its already dead*/
    enemy1.attack(thief2);
    assertEquals(0, thief2.getLifePoints());
    assertFalse(thief2.isAlive());

    var sword= new Sword("Sword", 15, 20);
    var knight = new Knight("Levi", turns, 100, 20);
    knight.equip(sword);


    knight.attack(enemy2);
    assertEquals(95, enemy2.getLifePoints());
    assertTrue(enemy2.isAlive());

    var enemy3 = new Enemy("Bear", turns, 10, 6, 3, 10 );
    assertTrue(enemy3.isAlive());

    knight.attack(enemy3);
    assertEquals(1, enemy3.getLifePoints());
    assertTrue(enemy3.isAlive());

    knight.attack(enemy3);
    assertEquals(0, enemy3.getLifePoints());
    assertFalse(enemy3.isAlive());

    /**Attack when its already dead*/

    knight.attack(enemy3);
    assertEquals(0, enemy3.getLifePoints());
    assertFalse(enemy3.isAlive());

  }
}
