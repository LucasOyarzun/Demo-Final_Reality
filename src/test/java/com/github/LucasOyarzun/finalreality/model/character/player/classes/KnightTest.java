package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void waitTurnTest() {
        adelbert.equip(testAxe);
        checkWaitTurn(adelbert);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Knight(KNIGHT_NAME,100,10, turns),
                adelbert,
                new Knight("Different name",100,10, turns),
                new Engineer(KNIGHT_NAME,100,10,turns),
                goblin);
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

        adelbert.equip(testSword);
        assertEquals(testSword, adelbert.getEquippedWeapon());
        adelbert.equip(testAxe);
        assertEquals(testAxe, adelbert.getEquippedWeapon());
        adelbert.equip(testKnife);
        assertEquals(testKnife, adelbert.getEquippedWeapon());
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