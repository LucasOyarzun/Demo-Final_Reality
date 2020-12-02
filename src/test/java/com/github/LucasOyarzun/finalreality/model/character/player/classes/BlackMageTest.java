package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Set of tests for the {@code BlackMage} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
class BlackMageTest extends AbstractPlayerCharacterTest {

    private static final String BLACK_MAGE_NAME = "Vivi";
    private BlackMage vivi;
    /**
     * Setup method.
     * Creates a new character named Vivi with 10 speed and links it to a turn queue.
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        vivi = new BlackMage(BLACK_MAGE_NAME,100, 10,100, turns);
    }

    /**
     * Test the waitTurn method in enemies.
     */
    @Test
    void waitTurnTest() {
        vivi.equip(testStaff);
        checkWaitTurn(vivi);
    }

    /**
     * Checks that the class' constructor works properly.
     */
    @Test
    void constructorTest() {
        checkConstruction(new BlackMage(BLACK_MAGE_NAME,100,10,100, turns),
                vivi,
                new BlackMage("Different name",100,10,100, turns),
                new WhiteMage(BLACK_MAGE_NAME,100,10, 100,turns),
                goblin);

        //Not equals with another Knight with different weapon//
        BlackMage withOtherWeapon = new BlackMage(BLACK_MAGE_NAME,100,10, 100, turns);
        withOtherWeapon.equip(testKnife);
        vivi.equip(testStaff);
        assertNotEquals(withOtherWeapon, vivi);
    }

    /**
     * Test the method equip whit Black Mage
     */
    @Test
    void equipWeaponTest() {
        assertNull(vivi.getEquippedWeapon());
        vivi.equip(testSword);
        vivi.equip(testBow);
        vivi.equip(testAxe);
        assertNull(vivi.getEquippedWeapon());
        assertEquals(0, vivi.getWeight());

        vivi.equip(testStaff);
        assertEquals(testStaff, vivi.getEquippedWeapon());
        vivi.equip(testKnife);
        assertEquals(testKnife, vivi.getEquippedWeapon());
        assertEquals(10, vivi.getWeight());
    }

    /**
     * Test the attack and equip methods in Enemy
     */
    @Test
    void attackTest() {
        vivi.equip(testStaff);
        /*Attack until 0 lifePoints */
        assertTrue(goblin.isAlive());
        vivi.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(35, goblin.getLifePoints());
        vivi.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(20, goblin.getLifePoints());
        vivi.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(5, goblin.getLifePoints());
        vivi.attack(goblin);
        assertFalse(goblin.isAlive());
        assertEquals(0, goblin.getLifePoints());

        /*Attack to a dead enemy*/
        vivi.attack(goblin);
        assertFalse(goblin.isAlive());
        assertEquals(0, goblin.getLifePoints());

        /*Attack whit different player's defense */
        assertTrue(demon.isAlive());
        vivi.attack(demon);
        assertTrue(demon.isAlive());
        assertEquals(95, demon.getLifePoints());

        /*Attack whit player's defense bigger than enemy's attack */
        assertTrue(immortal.isAlive());
        vivi.attack(immortal);
        assertTrue(immortal.isAlive());
        assertEquals(100, immortal.getLifePoints());
    }
}