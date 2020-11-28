package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Set of tests for the {@code Thief} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
class ThiefTest extends AbstractPlayerCharacterTest {

    private static final String THIEF_NAME = "Zidane";
    private Thief zidane;
    /**
     * Setup method.
     * Creates a new character named Vivi with 10 speed and links it to a turn queue.
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        zidane = new Thief(THIEF_NAME,100, 10, turns);
    }

    /**
     * Test the waitTurn method in enemies.
     */
    @Test
    void waitTurnTest() {
        zidane.equip(testSword);
        checkWaitTurn(zidane);
    }

    /**
     * Checks that the class' constructor works properly.
     */
    @Test
    void constructorTest() {
        checkConstruction(new Thief(THIEF_NAME,100,10, turns),
                zidane,
                new Thief("Different name",100,10, turns),
                new Engineer(THIEF_NAME,100,10,turns),
                goblin);

        //Not equals with another Thief with different weapon//
        Thief withOtherWeapon = new Thief(THIEF_NAME,100,10, turns);
        withOtherWeapon.equip(testSword);
        zidane.equip(testBow);
        assertNotEquals(withOtherWeapon, zidane);
    }

    /**
     * Test the method equip whit Thief
     */
    @Test
    void equipWeaponTest() {
        assertNull(zidane.getEquippedWeapon());
        zidane.equip(testAxe);
        zidane.equip(testKnife);
        assertNull(zidane.getEquippedWeapon());

        zidane.equip(testSword);
        assertEquals(testSword, zidane.getEquippedWeapon());
        zidane.equip(testBow);
        assertEquals(testBow, zidane.getEquippedWeapon());
        zidane.equip(testStaff);
        assertEquals(testStaff, zidane.getEquippedWeapon());
    }

    /**
     * Test the attack and equip methods in Enemy
     */
    @Test
    void attackTest() {
        zidane.equip(testSword);
        /*Attack until 0 lifePoints */
        assertTrue(goblin.isAlive());
        zidane.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(35, goblin.getLifePoints());
        zidane.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(20, goblin.getLifePoints());
        zidane.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(5, goblin.getLifePoints());
        zidane.attack(goblin);
        assertFalse(goblin.isAlive());
        assertEquals(0, goblin.getLifePoints());

        /*Attack to a dead enemy*/
        zidane.attack(goblin);
        assertFalse(goblin.isAlive());
        assertEquals(0, goblin.getLifePoints());

        /*Attack whit different player's defense */
        assertTrue(demon.isAlive());
        zidane.attack(demon);
        assertTrue(demon.isAlive());
        assertEquals(95, demon.getLifePoints());

        /*Attack whit player's defense bigger than enemy's attack */
        assertTrue(immortal.isAlive());
        zidane.attack(immortal);
        assertTrue(immortal.isAlive());
        assertEquals(100, immortal.getLifePoints());
    }
}