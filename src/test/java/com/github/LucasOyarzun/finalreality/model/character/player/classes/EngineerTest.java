package com.github.LucasOyarzun.finalreality.model.character.player.classes;

import com.github.LucasOyarzun.finalreality.model.character.Enemy;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.Axe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EngineerTest extends AbstractPlayerCharacterTest {

    private static final String ENGINEER_NAME = "Cid";
    private Engineer cid;
    /**
     * Setup method.
     * Creates a new character named Vivi with 10 speed and links it to a turn queue.
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        cid = new Engineer(ENGINEER_NAME,100, 10, turns);
    }

    @Test
    void waitTurnTest() {
        cid.equip(testAxe);
        checkWaitTurn(cid);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Engineer(ENGINEER_NAME,100,10, turns),
                cid,
                new Engineer("Different name",100,10, turns),
                new Knight(ENGINEER_NAME,100,10,turns),
                goblin);
    }

    /**
     * Test the method equip whit Engineer
     */
    @Test
    void equipWeaponTest() {
        assertNull(cid.getEquippedWeapon());
        cid.equip(testStaff);
        cid.equip(testKnife);
        cid.equip(testSword);

        assertNull(cid.getEquippedWeapon());
        cid.equip(testAxe);
        assertEquals(testAxe, cid.getEquippedWeapon());
        cid.equip(testBow);
        assertEquals(testBow, cid.getEquippedWeapon());
    }

    /**
     * Test the attack and equip methods in Enemy
     */
    @Test
    void attackTest() {
        cid.equip(testAxe);
        /*Attack until 0 lifePoints */
        assertTrue(goblin.isAlive());
        cid.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(35, goblin.getLifePoints());
        cid.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(20, goblin.getLifePoints());
        cid.attack(goblin);
        assertTrue(goblin.isAlive());
        assertEquals(5, goblin.getLifePoints());
        cid.attack(goblin);
        assertFalse(goblin.isAlive());
        assertEquals(0, goblin.getLifePoints());

        /*Attack whit different player's defense */
        assertTrue(demon.isAlive());
        cid.attack(demon);
        assertTrue(demon.isAlive());
        assertEquals(95, demon.getLifePoints());

        /*Attack whit player's defense bigger than enemy's attack */
        assertTrue(immortal.isAlive());
        cid.attack(immortal);
        assertTrue(immortal.isAlive());
        assertEquals(100, immortal.getLifePoints());
    }

}
