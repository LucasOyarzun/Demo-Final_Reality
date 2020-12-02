package com.github.LucasOyarzun.finalreality.controller;

import com.github.LucasOyarzun.finalreality.model.Computer;
import com.github.LucasOyarzun.finalreality.model.Player;
import com.github.LucasOyarzun.finalreality.model.character.*;
import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A class that controls every aspect of the model.
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */

public class GameController {
    private final Player player;
    private final Computer com;
    private ICharacter actualCharacter;
    protected BlockingQueue<ICharacter> turns;
    private final IEventHandler playerCharacterDeathHandler = new playerCharacterDeathHandler(this);
    private final IEventHandler enemyDeathHandler = new enemyDeathHandler(this);
    private final IEventHandler characterEndsTurnHandler = new characterEndsTurnHandler(this);

    /**
     * Creates a new controller.
     */
    public GameController() {
        turns = new LinkedBlockingQueue<>();
        player = new Player("Player", turns);
        com = new Computer("COM", turns);
        player.addListener(enemyDeathHandler);
        com.addListener(playerCharacterDeathHandler);
    }

    /**
     * Start a game, adding characters to turns queue.
     */
    public void startGame() {
        for (int i=0;i<getPlayerCharactersList().size();i++) {
            getPlayerCharactersList().get(i).waitTurn();
        }
        for (int i=0;i<getEnemiesList().size();i++) {
            getEnemiesList().get(i).waitTurn();
        }
    }

    /**
     * Return the Computer playing.
     */
    public Computer getCom() {
        return com;
    }

    /**
     * Return the Player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Return turns queue.
     */
    public BlockingQueue<ICharacter> getTurns() {
        return turns;
    }

    /**
     * Return the Player's characters list.
     */
    public ArrayList<IPlayerCharacter> getPlayerCharactersList() {
        return player.getCharacters();
    }

    /**
     * Return the Computer's enemy list.
     */
    public ArrayList<Enemy> getEnemiesList() {
        return com.getEnemies();
    }

    /**
     * Return the Player's inventory.
     */
    public ArrayList<IWeapon> getPlayerInventory() {
        return player.getInventory();
    }

    /**
     * returns to the character that is currently deciding.
     */
    public ICharacter getActualCharacter() {
        return actualCharacter;
    }


    //Ask character's information

    /**
     * Ask character's name if character is in the PlayerCharacter or Enemy List
     * @param character
     */
    public String askCharacterName(ICharacter character) {
        if (player.getCharacters().contains(character) ||
                com.getEnemies().contains(character)) {
            return character.getName();
        } else {
            return "";
        }
    }

    /**
     * Ask character's LifePoints if character is in the PlayerCharacter or Enemy List
     * @param character
     */
    public int askCharacterLifePoints(ICharacter character) {
        if (player.getCharacters().contains(character) ||
                com.getEnemies().contains(character)) {
            return character.getLifePoints();
        } else {
            return -1;
        }
    }

    /**
     * Ask character's Damage if character is in the PlayerCharacter or Enemy List
     * @param character
     */
    public int askCharacterDamage(ICharacter character) {
        if (player.getCharacters().contains(character) ||
                com.getEnemies().contains(character)) {
            return character.getDamage();
        } else {
            return -1;
        }
    }

    /**
     * Ask character's Defense if character is in the PlayerCharacter or Enemy List
     * @param character
     */
    public int askCharacterDefense(ICharacter character) {
        if (player.getCharacters().contains(character) ||
                com.getEnemies().contains(character)) {
            return character.getDefense();
        } else {
            return -1;
        }
    }

    /**
     * Ask character's Weight if character is in the PlayerCharacter or Enemy List
     * @param character
     */
    public int askCharacterWeight(ICharacter character) {
        if (player.getCharacters().contains(character) ||
                com.getEnemies().contains(character)) {
            return character.getWeight();
        }
        else {
            return -1;
        }
    }

    /**
     * Picks the first character in queue.
     */
    public void pickCharacterFromQueue() {
        if(!turns.isEmpty()) {
            actualCharacter = turns.poll();
            characterIsWaiting(actualCharacter);
        }
    }

    //Methods of attack

    /**
     * A method to attack an objective whit actualCharacter.
     * @param objective objective attacked.
     */
    public void attack(ICharacter objective) {
        objective.controllerAttack(this);
    }

    /**
     * A method to attack an enemy with actualCharacter.
     * @param enemy Enemy attacked.
     */

    public void attackEnemy(Enemy enemy) {
        player.attack(actualCharacter, enemy);
    }

    /**
     * A method to attack an PlayerCharacter with actualCharacter.
     * @param character PlayerCharacter attacked.
     */
    public void attackPlayerCharacter(IPlayerCharacter character) {
        com.attack(actualCharacter, character);
    }

    //Methods for testing.
    /**
     * Order a Player character to attack an enemy.
     * @param character Player character that will attack.
     * @param enemy     Enemy attacked.
     */
    public void charAttackEnemy(IPlayerCharacter character, Enemy enemy) {
        player.attack(character, enemy);
    }

    /**
     * Order an Enemy to attack a Player character.
     * @param enemy     Enemy that will attack.
     * @param character Player character attacked.
     */
    public void enemyAttackChar(Enemy enemy, IPlayerCharacter character) {
        com.attack(enemy, character);
    }

    /**
     * Add a Player Character to Player's characters list.
     * @param character Player character added.
     */
    public void addPlayerCharacter(IPlayerCharacter character) {
        character.addListener(characterEndsTurnHandler);
        player.addPlayerCharacter(character);
    }

    /**
     * Add an Enemy to Computer's enemies list.
     * @param enemy Enemy added.
     */
    public void addEnemy(Enemy enemy) {
        enemy.addListener(characterEndsTurnHandler);
        com.addEnemy(enemy);
    }

    /**
     * Add a Weapon to Player's weapons list.
     * @param weapon Weapon added.
     */
    public void addWeapon(IWeapon weapon) {
        player.addWeapon(weapon);
    }

    /**
     * Remove a character from the Player's character list.
     * @param character Weapon to remove.
     */
    public void removePlayerCharacter(IPlayerCharacter character) {
        player.removePlayerCharacter(character);
    }

    /**
     * Remove an enemy from the Computer's enemy list.
     * @param enemy Enemy to remove.
     */
    public void removeEnemy(Enemy enemy) {
        com.removeEnemy(enemy);
    }

    /**
     * Remove a weapon from the Player's inventory.
     * @param weapon Weapon to remove.
     */
    public void removeWeapon(IWeapon weapon) {
        player.removeWeapon(weapon);
    }

    /**
     * Equip a weapon to a selected Player character.
     * @param weapon    Weapon added.
     * @param character Character that will equip the weapon.
     */
    public void equipWeapontoCharacter(IWeapon weapon, IPlayerCharacter character) {
        player.equipWeapon(weapon, character);

    }

    public void changeWeapon(IWeapon weapon) {
        actualCharacter.beOrderedToEquipBy(this, weapon);
    }

    /**
     * Remove a dead character from the list of characters.
     * @param deadCharacter character who died.
     */
    public void onCharacterDeath(IPlayerCharacter deadCharacter) {
        removePlayerCharacter(deadCharacter);
        System.out.println(deadCharacter.getName() + " has died.");
        //If the player lost the game
        if (player.getCharacters().isEmpty()) {
            com.win();
            com.clearList();
            System.out.println("GAME OVER You Lose.");
        }

    }

    /**
     * Remove a dead enemy from the list of characters
     * @param deadEnemy Enemy who died.
     */
    public void onEnemyDeath(Enemy deadEnemy) {
        removeEnemy(deadEnemy);
        System.out.println(deadEnemy.getName() + " has died.");
        //If the player lost the game
        if (com.getEnemies().isEmpty()) {
            player.win();
            player.clearList();
            System.out.println("GAME OVER You Win.");
        }

    }

    /**
     * Print a message when a character ends his/her turn.
     * @param message message printed.
     */
    public void characterEndsTurn(String message) {
        System.out.println(message +" and ends his/her turn.");
    }

    /**
     * Print a message when a character is ready to battle.
     * @param character character Ready.
     */
    public void characterIsWaiting(ICharacter character) {
        System.out.println(character.getName() + " is ready to battle.");
    }
}