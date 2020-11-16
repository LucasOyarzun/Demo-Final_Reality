package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.character.Enemy;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WhiteMageTest extends AbstractPlayerCharacterTest {

    private static final String WHITE_MAGE_NAME = "Eiko";
    private WhiteMage eiko;

    /**
     * Setup method.
     * Creates a new character named Vivi with 10 speed and links it to a turn queue.
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        eiko = new WhiteMage(WHITE_MAGE_NAME,100, 10,100, turns);

    }

    @Test
    void waitTurnTest() {
        eiko.equip(testStaff);
        checkWaitTurn(eiko);
    }

    @Test
    void constructorTest() {
        checkConstruction(new WhiteMage(WHITE_MAGE_NAME,100,10,100, turns),
                eiko,
                new WhiteMage("Different name",100,10,100, turns),
                new Knight(WHITE_MAGE_NAME,100,10,turns),
                goblin);
    }

    /**
     * Test the method equip whit Thief
     */
    @Test
    void equipWeaponTest() {
        assertNull(eiko.getEquippedWeapon());
        eiko.equip(testAxe);
        eiko.equip(testKnife);
        eiko.equip(testBow);
        eiko.equip(testSword);
        assertNull(eiko.getEquippedWeapon());

        eiko.equip(testStaff);
        assertEquals(testStaff, eiko.getEquippedWeapon());
    }

    /**
     * Test the attack and equip methods in Enemy
     */
    @Test
    void attackTest() {
        eiko.equip(testStaff);
        /*Attack until 0 lifePoints */
        assertTrue(goblin.isAlive());
        eiko.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(35, goblin.getLifePoints());
        eiko.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(20, goblin.getLifePoints());
        eiko.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(5, goblin.getLifePoints());
        eiko.attack(goblin);
        assertFalse(goblin.isAlive());
        assertEquals(0, goblin.getLifePoints());

        /*Attack whit different player's defense */
        assertTrue(demon.isAlive());
        eiko.attack(demon);
        assertTrue(demon.isAlive());
        assertEquals(95, demon.getLifePoints());

        /*Attack whit player's defense bigger than enemy's attack */
        assertTrue(immortal.isAlive());
        eiko.attack(immortal);
        assertTrue(immortal.isAlive());
        assertEquals(100, immortal.getLifePoints());
    }
}