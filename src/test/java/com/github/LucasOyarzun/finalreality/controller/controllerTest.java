package com.github.LucasOyarzun.finalreality.controller;

import com.github.LucasOyarzun.finalreality.model.Computer;
import com.github.LucasOyarzun.finalreality.model.Player;
import com.github.LucasOyarzun.finalreality.model.character.Enemy;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.classes.*;
import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Set of tests for the {@code GameController} class.
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */

public class controllerTest {

    private final GameController controller = new GameController();
    private BlockingQueue<ICharacter> turns;
    private Player player;
    private Computer com;

    private Knight adelbert;
    private WhiteMage eiko;
    private Thief zidane;
    private Engineer cid;

    private Enemy devil1;
    private Enemy devil2;
    private Enemy ent;
    private Enemy assassin;

    private Axe testAxe;
    private Staff testStaff;
    private Sword testSword;
    private Bow testBow;
    private Knife testKnife;

    private static final String AXE_NAME = "Test Axe";
    private static final String STAFF_NAME = "Test Staff";
    private static final String SWORD_NAME = "Test Sword";
    private static final String BOW_NAME = "Test Bow";
    private static final String KNIFE_NAME = "Test Knife";
    private static final int DAMAGE = 50;

    ArrayList<ICharacter> characterList;
    ArrayList<ICharacter> enemyList;
    ArrayList<IWeapon> inventory;

    /**
     * Creates a new controller, players, characters and weapons.
     */
    @BeforeEach
    void setUp() {
        turns = controller.getTurns();
        player = controller.getPlayer();
        com = controller.getCom();
        characterList = controller.getPlayerCharactersList();
        enemyList = controller.getEnemiesList();
        inventory = controller.getPlayerInventory();

        adelbert = new Knight("Adelbert", 200, 30, turns);
        eiko = new WhiteMage("Eiko", 100, 10, 80, turns);
        zidane = new Thief("Zidane", 100, 15, turns);
        cid = new Engineer("Cid", 130, 20, turns);

        devil1 = new Enemy("Devil1", 60, 25, 32, 40, turns);
        devil2 = new Enemy("Devil2", 60, 25, 42, 40, turns);
        ent = new Enemy("Ent", 200, 40, 60, 52, turns);
        assassin = new Enemy("Assassin", 80, 200, 32, 80, turns);

        testAxe = new Axe(AXE_NAME, DAMAGE, 10);
        testStaff = new Staff(STAFF_NAME, DAMAGE, 20, 100);
        testSword = new Sword(SWORD_NAME, DAMAGE, 30);
        testBow = new Bow(BOW_NAME, DAMAGE, 40);
        testKnife = new Knife(KNIFE_NAME, DAMAGE, 50);
    }

    /**
     * Checks that the classes' constructor works properly.
     */
    @Test
    void constructorTest() {
        assertEquals(player, new Player("Player",controller, turns));
        assertEquals(player, player);
        assertNotEquals(player, new Player("Player2",controller, turns));
        assertNotEquals(player, new Computer("Player",controller, turns));

        assertEquals(com, com);
        assertEquals(com, new Computer("COM",controller, turns));
        assertNotEquals(com, new Computer("COM2",controller, turns));
        assertNotEquals(com, new Player("COM",controller, turns));
    }

    /**
     * Checks that de askInformation methods works properly.
     */
    @Test
    void characterInfoTest() {
        assertEquals("", controller.askCharacterName(adelbert));
        assertEquals(-1, controller.askCharacterLifePoints(adelbert));
        assertEquals(-1, controller.askCharacterDamage(adelbert));
        assertEquals(-1, controller.askCharacterDefense(adelbert));
        assertEquals(-1, controller.askCharacterWeight(adelbert));
        controller.addPlayerCharacter(adelbert);
        assertEquals("Adelbert", controller.askCharacterName(adelbert));
        assertEquals(200, controller.askCharacterLifePoints(adelbert));
        assertEquals(30, controller.askCharacterDefense(adelbert));
        controller.addWeapon(testSword);
        controller.equipWeapontoCharacter(testSword, adelbert);
        assertEquals(50, controller.askCharacterDamage(adelbert));
        assertEquals(30, controller.askCharacterWeight(adelbert));

        assertEquals("", controller.askCharacterName(devil1));
        assertEquals(-1, controller.askCharacterLifePoints(devil1));
        assertEquals(-1, controller.askCharacterDamage(devil1));
        assertEquals(-1, controller.askCharacterDefense(devil1));
        assertEquals(-1, controller.askCharacterWeight(devil1));
        controller.addEnemy(devil1);
        assertEquals("Devil1", controller.askCharacterName(devil1));
        assertEquals(60, controller.askCharacterLifePoints(devil1));
        assertEquals(25, controller.askCharacterDefense(devil1));
        assertEquals(40, controller.askCharacterDamage(devil1));
        assertEquals(32, controller.askCharacterWeight(devil1));

    }
    /**
     * Checks that the add/remove Enemy method works properly.
     */
    @Test
    void addEnemyTest() {
        assertTrue(enemyList.isEmpty());
        controller.addEnemy(devil1);
        assertTrue(enemyList.contains(devil1));
        controller.addEnemy(devil2);
        assertTrue(enemyList.contains(devil2));
        controller.addEnemy(ent);
        controller.addEnemy(assassin);
        assertEquals(4, enemyList.size());
        controller.removeEnemy(devil1);
        assertFalse(enemyList.contains(devil1));
        controller.removeEnemy(devil2);
        controller.removeEnemy(ent);
        controller.removeEnemy(assassin);
        assertTrue(enemyList.isEmpty());
    }

    /**
     * Checks that the add/remove IPlayerCharacter methods works properly.
     */
    @Test
    void addPlayerCharacterTest() {
        assertTrue(characterList.isEmpty());
        controller.addPlayerCharacter(eiko);
        assertTrue(characterList.contains(eiko));
        controller.addPlayerCharacter(adelbert);
        assertTrue(characterList.contains(adelbert));
        controller.addPlayerCharacter(cid);
        controller.addPlayerCharacter(zidane);
        assertEquals(4, characterList.size());
    }

    /**
     * Checks that the add/remove Weapons methods works properly.
     */
    @Test
    void addWeaponTest() {
        //Add weapons to inventory
        assertTrue(inventory.isEmpty());
        controller.addWeapon(testAxe);
        assertTrue(inventory.contains(testAxe));
        controller.addWeapon(testBow);
        controller.addWeapon(testKnife);
        controller.addWeapon(testStaff);
        controller.addWeapon(testSword);
        assertEquals(5, inventory.size());
        controller.removeWeapon(testSword);
        assertFalse(inventory.contains(testSword));
        controller.removeWeapon(testAxe);
        controller.removeWeapon(testStaff);
        controller.removeWeapon(testBow);
        controller.removeWeapon(testKnife);
        assertTrue(inventory.isEmpty());
    }

    /**
     * Checks that the equipWeapons methods work properly.
     */
    @Test
    void equipWeaponTest() {
        controller.addPlayerCharacter(eiko);
        controller.addPlayerCharacter(adelbert);

        assertNull(eiko.getEquippedWeapon());
        assertNull(adelbert.getEquippedWeapon());

        controller.equipWeapontoCharacter(testSword, adelbert);
        assertNull(adelbert.getEquippedWeapon());

        controller.addWeapon(testAxe);
        controller.equipWeapontoCharacter(testAxe, cid);
        assertNull(cid.getEquippedWeapon());

        controller.addWeapon(testStaff);
        controller.equipWeapontoCharacter(testAxe, adelbert);
        assertEquals(testAxe, adelbert.getEquippedWeapon());
        controller.equipWeapontoCharacter(testAxe, eiko);
        assertNull(eiko.getEquippedWeapon());
        controller.equipWeapontoCharacter(testStaff, eiko);
        assertEquals(testStaff, eiko.getEquippedWeapon());
    }
    /**
     * Check that the changeWeapon method works properly.
     */
    @Test
    void changeWeaponTest() throws InterruptedException {
        controller.addPlayerCharacter(adelbert);
        controller.addPlayerCharacter(cid);
        controller.addWeapon(testAxe);
        controller.addWeapon(testBow);
        controller.addWeapon(testSword);
        controller.addWeapon(testStaff);
        controller.equipWeapontoCharacter(testAxe, adelbert);
        controller.equipWeapontoCharacter(testBow, cid);
        assertEquals(testAxe, adelbert.getEquippedWeapon());
        assertEquals(testBow, cid.getEquippedWeapon());
        assertEquals(2, player.getInventory().size());

        controller.startGame();

        assertEquals(adelbert, controller.getActualCharacter());
        controller.changeWeapon(testSword);
        assertEquals(testSword, adelbert.getEquippedWeapon());
        assertTrue(player.getInventory().contains(testAxe));
        controller.attack(devil1);

        controller.pickCharacterFromQueue(); //Adelbert
        Thread.sleep(1500);
        controller.pickCharacterFromQueue(); //Adelbert
        Thread.sleep(3000);
        controller.pickCharacterFromQueue(); //Adelbert
        controller.pickCharacterFromQueue(); //Cid
        assertEquals(cid, controller.getActualCharacter());
        controller.changeWeapon(testAxe);
        assertEquals(testAxe, cid.getEquippedWeapon());
    }

    /**
     * Checks that the queue methods works properly.
     */
    /** Estaba pensado para otro tipo de fases, no las que se usaron al final*/
    @Test
    void queueTest() throws InterruptedException {
        System.out.println("////Queue Test");
        //Adding Characters, Enemies and Weapons
        controller.addPlayerCharacter(adelbert);
        controller.addPlayerCharacter(eiko);
        controller.addWeapon(testAxe);
        controller.addWeapon(testStaff);
        controller.equipWeapontoCharacter(testAxe, adelbert);
        controller.equipWeapontoCharacter(testStaff, eiko);
        controller.addEnemy(devil1);
        controller.addEnemy(devil2);

        controller.startGame();

        assertEquals(adelbert, controller.getActualCharacter());
        assertEquals(player, controller.getActualPlayer());
        controller.attack(devil1);

        Thread.sleep(1500);
        controller.pickCharacterFromQueue();
        assertEquals(adelbert, controller.getActualCharacter());
        controller.attack(devil1);

        Thread.sleep(1500);
        controller.pickCharacterFromQueue();
        assertEquals(eiko, controller.getActualCharacter());
        controller.attack(devil2);

        Thread.sleep(1500);
        controller.pickCharacterFromQueue();
        assertEquals(adelbert, controller.getActualCharacter());
        controller.attack(adelbert);

        Thread.sleep(1500);
        controller.pickCharacterFromQueue();
        assertEquals(devil1, controller.getActualCharacter());
        assertEquals(com, controller.getActualPlayer());
        controller.attack(adelbert);
        System.out.println("//////////////////////////");
    }


    /**
     * Checks that the attack method works properly.
     */
    @Test
    void attackTest() {
        System.out.println("////Attack Test");
        //Adding Characters, Enemies and Weapons
        controller.addPlayerCharacter(adelbert);
        controller.addPlayerCharacter(eiko);
        controller.addWeapon(testAxe);
        controller.addWeapon(testStaff);
        controller.equipWeapontoCharacter(testStaff, eiko);
        controller.equipWeapontoCharacter(testAxe, adelbert);
        controller.addEnemy(devil1);
        controller.addEnemy(devil2);

        //Character attacks Enemy
        assertEquals(60, devil1.getLifePoints());
        assertEquals(60, devil2.getLifePoints());
        controller.charAttackEnemy(eiko, devil1);
        assertEquals(35, devil1.getLifePoints());
        controller.charAttackEnemy(eiko, devil1);

        //Enemy die and is removed from list
        assertEquals(2, enemyList.size());
        controller.charAttackEnemy(eiko, devil1);
        assertEquals(1, enemyList.size());
        controller.charAttackEnemy(eiko, devil1);
        assertEquals(1, enemyList.size());

        //Enemy attack Player Character
        controller.enemyAttackChar(devil2, eiko);
        assertEquals(70, eiko.getLifePoints());
        controller.enemyAttackChar(devil2, eiko);
        controller.enemyAttackChar(devil2, eiko);
        assertEquals(10, eiko.getLifePoints());

        //Player Character die and is removed from list
        assertEquals(2, characterList.size());
        controller.enemyAttackChar(devil2, eiko);
        assertEquals(1, characterList.size());
        controller.enemyAttackChar(devil2, eiko);
        assertEquals(1, characterList.size());
        System.out.println("//////////////////////////");

    }

    /**
     * Checks that player wins when Computer is out of enemies.
     */
    @Test
    void winTest() {
        System.out.println("////Win Test");
        controller.addPlayerCharacter(eiko);
        controller.addWeapon(testStaff);
        controller.equipWeapontoCharacter(testStaff, eiko);
        controller.addEnemy(devil1);
        controller.charAttackEnemy(eiko, devil1);
        controller.charAttackEnemy(eiko, devil1);
        assertEquals(10, devil1.getLifePoints());

        controller.charAttackEnemy(eiko, devil1);

        assertEquals(0, enemyList.size());
        assertEquals(0, characterList.size());

        assertTrue(controller.getPlayer().askWin());
        assertFalse(controller.getCom().askWin());
        System.out.println("//////////////////////////");
    }

    /**
     * Checks that Player loses when Player is out of characters.
     */
    @Test
    void loseTest() {
        System.out.println("////Lose Test");
        controller.addPlayerCharacter(eiko);
        controller.addWeapon(testStaff);
        controller.equipWeapontoCharacter(testStaff, eiko);
        controller.addEnemy(devil1);
        controller.enemyAttackChar(devil1, eiko);
        controller.enemyAttackChar(devil1, eiko);
        controller.enemyAttackChar(devil1, eiko);
        assertEquals(10, eiko.getLifePoints());

        controller.enemyAttackChar(devil1, eiko);
        assertEquals(0, characterList.size());
        assertEquals(0, enemyList.size());
        assertTrue(controller.getCom().askWin());
        assertFalse(controller.getPlayer().askWin());
        System.out.println("//////////////////////////");
    }

}
