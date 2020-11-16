package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void waitTurnTest() {
        zidane.equip(testSword);
        checkWaitTurn(zidane);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Thief(THIEF_NAME,100,10, turns),
                zidane,
                new Thief("Different name",100,10, turns),
                new Knight(THIEF_NAME,100,10,turns),
                goblin);
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