package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.LucasOyarzun.finalreality.model.character.player.InvalidEquipException;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Set of tests for the {@code WhiteMage} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
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

    /**
     * Test the waitTurn method in enemies.
     */
    @Test
    void waitTurnTest() throws InvalidEquipException {
        eiko.equip(testStaff);
        checkWaitTurn(eiko);
    }

    /**
     * Checks that the class' constructor works properly.
     */
    @Test
    void constructorTest() throws InvalidEquipException {
        checkConstruction(new WhiteMage(WHITE_MAGE_NAME,100,10,100, turns),
                eiko,
                new WhiteMage("Different name",100,10,100, turns),
                new BlackMage(WHITE_MAGE_NAME,100,10, 100,turns),
                goblin);

        //Not equals with another WhiteMage with different weapon//
        WhiteMage withOtherWeapon = new WhiteMage(WHITE_MAGE_NAME,100,10,100, turns);
        withOtherWeapon.equip(testStaff);
        eiko.equip(new Staff("Other staff", 100, 100, 100));
        assertNotEquals(withOtherWeapon, eiko);
    }

    /**
     * Test the method equip whit Thief
     */
    @Test
    void equipWeaponTest() throws InvalidEquipException {
        assertNull(eiko.getEquippedWeapon());
        assertThrows(com.github.LucasOyarzun.finalreality.model.character.player.InvalidEquipException.class,
                () -> {eiko.equip(testBow);});
        assertThrows(com.github.LucasOyarzun.finalreality.model.character.player.InvalidEquipException.class,
                () -> {eiko.equip(testAxe);});
        assertThrows(com.github.LucasOyarzun.finalreality.model.character.player.InvalidEquipException.class,
                () -> {eiko.equip(testKnife);});
        assertThrows(com.github.LucasOyarzun.finalreality.model.character.player.InvalidEquipException.class,
                () -> {eiko.equip(testSword);});
        assertNull(eiko.getEquippedWeapon());
        assertEquals(0, eiko.getWeight());

        eiko.equip(testStaff);
        assertEquals(testStaff, eiko.getEquippedWeapon());
        assertEquals(10, eiko.getWeight());
    }

    /**
     * Test the attack and equip methods of WhiteMage
     */
    @Test
    void attackTest() throws InvalidEquipException {
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
        /*Attack to a dead enemy*/
        eiko.attack(goblin);
        assertFalse(goblin.isAlive());
        assertEquals(0, goblin.getLifePoints());

        /*Attack with different player's defense */
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