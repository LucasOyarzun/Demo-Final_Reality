package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Set of tests for the {@code Knight} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
class KnightTest extends AbstractPlayerCharacterTest {

    private static final String KNIGHT_NAME = "Adelbert";
    private Knight adelbert;

    /**
     * Setup method.
     * Creates a new character named Vivi with 10 speed and links it to a turn queue.
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        adelbert = new Knight(KNIGHT_NAME,100, 10, turns);
    }

    /**
     * Test the waitTurn method in enemies.
     */
    @Test
    void waitTurnTest() {
        adelbert.equip(testAxe);
        checkWaitTurn(adelbert);
    }

    /**
     * Checks that the class' constructor works properly.
     */
    @Test
    void constructorTest() {
        checkConstruction(new Knight(KNIGHT_NAME,100,10, turns),
                adelbert,
                new Knight("Different name",100,10, turns),
                new Thief(KNIGHT_NAME,100,10,turns),
                goblin);

        //Not equals with another Knight with different weapon//
        Knight withOtherWeapon = new Knight(KNIGHT_NAME,100,10, turns);
        withOtherWeapon.equip(testSword);
        adelbert.equip(testAxe);
        assertNotEquals(withOtherWeapon, adelbert);
    }

    /**
     * Test the method equip whit Knight
     */
    @Test
    void equipWeaponTest() {
        assertNull(adelbert.getEquippedWeapon());
        adelbert.equip(testStaff);
        adelbert.equip(testBow);
        assertNull(adelbert.getEquippedWeapon());
        assertEquals(0, adelbert.getWeight());

        adelbert.equip(testSword);
        assertEquals(testSword, adelbert.getEquippedWeapon());
        adelbert.equip(testAxe);
        assertEquals(testAxe, adelbert.getEquippedWeapon());
        adelbert.equip(testKnife);
        assertEquals(testKnife, adelbert.getEquippedWeapon());
        assertEquals(10, adelbert.getWeight());
    }

    /**
     * Test the attack and equip methods in Enemy
     */
    @Test
    void attackTest() {
        adelbert.equip(testAxe);
        /*Attack until 0 lifePoints */
        assertTrue(goblin.isAlive());
        adelbert.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(35, goblin.getLifePoints());
        adelbert.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(20, goblin.getLifePoints());
        adelbert.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(5, goblin.getLifePoints());
        adelbert.attack(goblin);
        assertFalse(goblin.isAlive());
        assertEquals(0, goblin.getLifePoints());

        /*Attack to a dead enemy*/
        adelbert.attack(goblin);
        assertFalse(goblin.isAlive());
        assertEquals(0, goblin.getLifePoints());

        /*Attack whit different player's defense */
        assertTrue(demon.isAlive());
        adelbert.attack(demon);
        assertTrue(demon.isAlive());
        assertEquals(95, demon.getLifePoints());

        /*Attack whit player's defense bigger than enemy's attack */
        assertTrue(immortal.isAlive());
        adelbert.attack(immortal);
        assertTrue(immortal.isAlive());
        assertEquals(100, immortal.getLifePoints());
    }
}