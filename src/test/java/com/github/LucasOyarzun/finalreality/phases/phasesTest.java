package com.github.LucasOyarzun.finalreality.phases;

import com.github.LucasOyarzun.finalreality.controller.GameController;
import com.github.LucasOyarzun.finalreality.model.Computer;
import com.github.LucasOyarzun.finalreality.model.Player;
import com.github.LucasOyarzun.finalreality.model.character.Enemy;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.InvalidEquipException;
import com.github.LucasOyarzun.finalreality.model.character.player.classes.Engineer;
import com.github.LucasOyarzun.finalreality.model.character.player.classes.Knight;
import com.github.LucasOyarzun.finalreality.model.character.player.classes.Thief;
import com.github.LucasOyarzun.finalreality.model.character.player.classes.WhiteMage;
import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

public class phasesTest {

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

        devil1 = new Enemy("Devil1", 60, 25, 8, 50, turns);
        devil2 = new Enemy("Devil2", 60, 25, 42, 40, turns);
        ent = new Enemy("Ent", 200, 40, 60, 52, turns);
        assassin = new Enemy("Assassin", 80, 200, 32, 80, turns);

        testAxe = new Axe(AXE_NAME, DAMAGE, 25);
        testStaff = new Staff(STAFF_NAME, DAMAGE, 20, 100);
        testSword = new Sword(SWORD_NAME, DAMAGE, 35);
        testBow = new Bow(BOW_NAME, DAMAGE, 40);
        testKnife = new Knife(KNIFE_NAME, DAMAGE, 50);
    }

    /**
     * Checks that the classes' constructor works properly.
     */

    @Test
    public void constructorTest() {
        EndPhase endPhase = new EndPhase(controller);
        MainPhase mainPhase = new MainPhase(controller);
        SelectingAttackTargetPhase selectPhase = new SelectingAttackTargetPhase(controller);

        assertEquals(endPhase, endPhase);
        assertEquals(endPhase, new EndPhase(controller));
        assertNotEquals(endPhase, new EndPhase(new GameController()));
        assertNotEquals(endPhase, new MainPhase(controller));

        assertEquals(mainPhase, mainPhase);
        assertEquals(mainPhase, new MainPhase(controller));
        assertNotEquals(mainPhase, new MainPhase(new GameController()));
        assertNotEquals(mainPhase, new EndPhase(controller));

        assertEquals(selectPhase, selectPhase);
        assertEquals(selectPhase, new SelectingAttackTargetPhase(controller));
        assertNotEquals(selectPhase, new SelectingAttackTargetPhase(new GameController()));
        assertNotEquals(selectPhase, mainPhase);
    }

    /**
     * Checks that the changePhase's methods works properly
     */
    @Test
    public void changePhaseTest() throws InvalidTransitionException, InterruptedException, InvalidEquipException, InvalidDecisionException {
        controller.addPlayerCharacter(adelbert);
        controller.addWeapon(testAxe);
        controller.addWeapon(testSword);
        controller.equipWeapontoCharacter(testAxe, adelbert);
        controller.addEnemy(devil1);


        // Start Game
        controller.startGame();
        assertEquals(adelbert, controller.getActualCharacter());

        // Main Phase

        assertEquals(new MainPhase(controller), controller.getActualPhase());
        assertNotEquals(new EndPhase(controller), controller.getActualPhase());
        assertNotEquals(new SelectingAttackTargetPhase(controller), controller.getActualPhase());

        // Can't attack
        assertThrows(com.github.LucasOyarzun.finalreality.phases.InvalidDecisionException.class,
                () -> {controller.tryToAttack(devil1);});
        assertEquals(60, devil1.getLifePoints());

        // Can't pick character from queue
        controller.tryToPick();
        assertEquals(adelbert, controller.getActualCharacter());

        // Can change weapon
        controller.trytoChangeWeapon(testSword);
        assertEquals(testSword, adelbert.getEquippedWeapon());

        // Can change to SelectingAttackTargetPhase
        controller.tryToStartAttack();

        // SelectingAttackTargetPhase

        assertEquals(new SelectingAttackTargetPhase(controller), controller.getActualPhase());
        assertNotEquals(new EndPhase(controller), controller.getActualPhase());
        assertNotEquals(new MainPhase(controller), controller.getActualPhase());
        // Can' change weapon
        assertThrows(com.github.LucasOyarzun.finalreality.phases.InvalidDecisionException.class,
                () -> {controller.trytoChangeWeapon(testAxe);});
        assertEquals(testSword, adelbert.getEquippedWeapon());

        // Can't pick character from queue
        controller.tryToPick();
        assertEquals(adelbert, controller.getActualCharacter());

        // Can´t change to SelectingAttackTargetPhase
        assertThrows(com.github.LucasOyarzun.finalreality.phases.InvalidTransitionException.class,
                () -> {controller.tryToStartAttack();;});

        assertEquals(new SelectingAttackTargetPhase(controller), controller.getActualPhase());

        // Can attack
        controller.tryToAttack(devil1);
        assertEquals(35, devil1.getLifePoints());
        // End Phase
        assertEquals(new MainPhase(controller), controller.getActualPhase());
        assertNotEquals(new EndPhase(controller), controller.getActualPhase());
        assertNotEquals(new SelectingAttackTargetPhase(controller), controller.getActualPhase());
        assertEquals(devil1, controller.getActualCharacter());

        //Can't attack
        assertThrows(com.github.LucasOyarzun.finalreality.phases.InvalidDecisionException.class,
                () -> {controller.tryToAttack(devil1);});
        assertEquals(35, devil1.getLifePoints());

        // Can' change weapon
        controller.trytoChangeWeapon(testAxe);
        assertEquals(testSword, adelbert.getEquippedWeapon());

        // Must do an Enemy Move
        controller.makeEnemyMove("This is an enemy move.");
        // Can pick character from queue ( Pick an enemy)
        Thread.sleep(1500);
        controller.tryToPick();

        // Starts an Enemy Main Phase
        assertNotEquals(new EndPhase(controller), controller.getActualPhase());
        assertEquals(new MainPhase(controller), controller.getActualPhase());

        controller.makeEnemyMove("This is an Enemy move.");
        // Can pick character from queue (Pick an enemy)
        Thread.sleep(1000);
        controller.tryToPick();

        // Keep on End Phase until pick a PlayerCharacter


        // Can pick character from queue (Pick an enemy)
        Thread.sleep(2000);
        controller.tryToPick();

        // Can pick character from queue (Pick a playerCharacter)
        controller.tryToPick();
        assertEquals(devil1, controller.getActualCharacter());

        //If picks a playerCharacter, change to MainPhase
        assertEquals(new MainPhase(controller), controller.getActualPhase());

        assertThrows(com.github.LucasOyarzun.finalreality.phases.InvalidTransitionException.class,
                () -> {controller.getActualPhase().toMainPhase();});

        controller.setPhase(new EndPhase(controller));
        assertThrows(com.github.LucasOyarzun.finalreality.phases.InvalidTransitionException.class,
                () -> {controller.getActualPhase().toEndPhase();});
    }

    /**
     * Checks that the game works with phases as expected
     */
    @Test
    public void fightTest() throws InvalidTransitionException, InterruptedException, InvalidEquipException, InvalidDecisionException {
        controller.addPlayerCharacter(adelbert);
        controller.addWeapon(testAxe);
        controller.addWeapon(testSword);
        controller.equipWeapontoCharacter(testAxe, adelbert);
        controller.addEnemy(devil1);

        // Start Game
        controller.startGame();

        assertEquals(60, devil1.getLifePoints());
        assertEquals(200, adelbert.getLifePoints());
        // Adelbert attacks devil1
        assertEquals(adelbert, controller.getActualCharacter());
        controller.tryToStartAttack();
        controller.tryToAttack(devil1);

        assertEquals(new MainPhase(controller), controller.getActualPhase());
        assertEquals(35, devil1.getLifePoints());

        // Second Turn (Enemy)
        Thread.sleep(2000);
        controller.tryToPick();
        controller.makeEnemyMove("This is an enemy Move");
        assertEquals(180, adelbert.getLifePoints());

        // Third Turn (Player)
        controller.tryToPick();
        assertEquals(adelbert, controller.getActualCharacter());
        assertEquals(new MainPhase(controller), controller.getActualPhase());

        // Adelbert attacks devil1
        assertEquals(adelbert, controller.getActualCharacter());
        controller.tryToStartAttack();
        controller.tryToAttack(devil1);

        assertEquals(new MainPhase(controller), controller.getActualPhase());
        assertEquals(10, devil1.getLifePoints());

        // Fourth Turn (Enemy)
        Thread.sleep(2000);
        controller.tryToPick();
        controller.makeEnemyMove("This is an enemy Move");

        // Adelbert lost life
        assertEquals(160, adelbert.getLifePoints());

        // Fifth Turn (Player)
        controller.tryToPick();
        assertEquals(adelbert, controller.getActualCharacter());
        assertEquals(new MainPhase(controller), controller.getActualPhase());

        // Adelbert attacks devil1
        assertEquals(adelbert, controller.getActualCharacter());
        controller.tryToStartAttack();
        controller.tryToAttack(devil1);

        // Player wins, doesn't change phase and print GAME OVER.
        assertEquals(new SelectingAttackTargetPhase(controller), controller.getActualPhase());
        assertEquals(0, devil1.getLifePoints());
        assertTrue(controller.getPlayer().askWin());
        assertFalse(controller.getCom().askWin());
    }

    @Test
    /**
     * Checks that the GetName() method works properly.
     */
    public void getNameTest() {
        EndPhase endPhase = new EndPhase(controller);
        MainPhase mainPhase = new MainPhase(controller);
        SelectingAttackTargetPhase selectPhase = new SelectingAttackTargetPhase(controller);
        assertEquals("End Phase", endPhase.getName());
        assertEquals("Main Phase", mainPhase.getName());
        assertEquals("Selecting attack target", selectPhase.getName());

    }
}
