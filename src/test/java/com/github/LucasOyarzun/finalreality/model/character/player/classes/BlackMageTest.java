package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.character.Enemy;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void waitTurnTest() {
        vivi.equip(testStaff);
        checkWaitTurn(vivi);
    }

    @Test
    void constructorTest() {
        checkConstruction(new BlackMage(BLACK_MAGE_NAME,100,10,100, turns),
                vivi,
                new BlackMage("Different name",100,10,100, turns),
                new Knight(BLACK_MAGE_NAME,100,10,turns),
                goblin);
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

        vivi.equip(testStaff);
        assertEquals(testStaff, vivi.getEquippedWeapon());
        vivi.equip(testKnife);
        assertEquals(testKnife, vivi.getEquippedWeapon());
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