package com.github.LucasOyarzun.finalreality.controller;

import com.github.LucasOyarzun.finalreality.model.character.player.InvalidEquipException;
import com.github.LucasOyarzun.finalreality.phases.*;
import com.github.LucasOyarzun.finalreality.model.Computer;
import com.github.LucasOyarzun.finalreality.model.IPlayer;
import com.github.LucasOyarzun.finalreality.model.Player;
import com.github.LucasOyarzun.finalreality.model.character.*;
import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    private String lastAttack;
    private Phase actualPhase;
    private ICharacter actualCharacter;
    protected BlockingQueue<ICharacter> turns;
    private final IEventHandler playerCharacterDeathHandler = new playerCharacterDeathHandler(this);
    private final IEventHandler enemyDeathHandler = new enemyDeathHandler(this);
    private final IEventHandler characterEndsTurnHandler = new characterEndsTurnHandler(this);

    /**
     * Creates a new controller.
     */
    public GameController() {
        setPhase(new MainPhase(this));
        turns = new LinkedBlockingQueue<>();
        player = new Player("Player", this, turns);
        com = new Computer("COM", this, turns);
        lastAttack = "";
        player.addListener(enemyDeathHandler);
        com.addListener(playerCharacterDeathHandler);
    }

    /**
     * Start a game, adding characters to turns queue.
     */
    public void startGame() {
        for (int i = 0; i < getPlayerCharactersList().size() - 1; i++) {
            getPlayerCharactersList().get(i + 1).waitTurn(); // Doesn't add the first character.
        }
        for (int i = 0; i < getEnemiesList().size(); i++) {
            getEnemiesList().get(i).waitTurn();
        }
        actualCharacter = getPlayerCharactersList().get(0); // Everytime the game starts with a PlayerCharacter's turn.
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
    public ArrayList<ICharacter> getPlayerCharactersList() {
        return player.getCharacters();
    }

    /**
     * Return the Computer's enemy list.
     */
    public ArrayList<ICharacter> getEnemiesList() {
        return com.getCharacters();
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

    public IPlayer getActualPlayer() {
        if (getPlayerCharactersList().contains(actualCharacter)) {
            return player;
        } else {
            return com;
        }
    }


    //Ask character's information

    /**
     * Ask character's name if character is in the PlayerCharacter or Enemy List
     *
     * @param character
     */
    public String askCharacterName(ICharacter character) {
        if (player.getCharacters().contains(character) ||
                com.getCharacters().contains(character)) {
            return character.getName();
        } else {
            return "";
        }
    }

    /**
     * Ask character's LifePoints if character is in the PlayerCharacter or Enemy List
     *
     * @param character
     */
    public int askCharacterLifePoints(ICharacter character) {
        if (player.getCharacters().contains(character) ||
                com.getCharacters().contains(character)) {
            return character.getLifePoints();
        } else {
            return -1;
        }
    }

    /**
     * Ask character's Damage if character is in the PlayerCharacter or Enemy List
     *
     * @param character
     */
    public int askCharacterDamage(ICharacter character) {
        if (player.getCharacters().contains(character) ||
                com.getCharacters().contains(character)) {
            return character.getDamage();
        } else {
            return -1;
        }
    }

    /**
     * Ask character's Defense if character is in the PlayerCharacter or Enemy List
     *
     * @param character
     */
    public int askCharacterDefense(ICharacter character) {
        if (player.getCharacters().contains(character) ||
                com.getCharacters().contains(character)) {
            return character.getDefense();
        } else {
            return -1;
        }
    }

    /**
     * Ask character's Weight if character is in the PlayerCharacter or Enemy List
     *
     * @param character
     */
    public int askCharacterWeight(ICharacter character) {
        if (player.getCharacters().contains(character) ||
                com.getCharacters().contains(character)) {
            return character.getWeight();
        } else {
            return -1;
        }
    }

    /**
     * Picks the first character in queue.
     */
    public void pickCharacterFromQueue() {
        if (!turns.isEmpty()) {
            actualCharacter = turns.poll();
            characterIsWaiting(actualCharacter);
        }
    }

    //Methods of attack

    /**
     * A method to attack an objective whit actualCharacter.
     *
     * @param objective objective attacked.
     */
    public void attack(ICharacter objective) {
        objective.controllerAttack(this);
    }

    /**
     * A method to attack an enemy with actualCharacter.
     *
     * @param enemy Enemy attacked.
     */

    public void attackEnemy(Enemy enemy) {
        player.attack(actualCharacter, enemy);
    }

    /**
     * A method to attack an PlayerCharacter with actualCharacter.
     *
     * @param character PlayerCharacter attacked.
     */
    public void attackPlayerCharacter(IPlayerCharacter character) {
        com.attack(actualCharacter, character);
    }

    //Methods for testing.

    /**
     * Order a Player character to attack an enemy.
     *
     * @param character Player character that will attack.
     * @param enemy     Enemy attacked.
     */
    public void charAttackEnemy(IPlayerCharacter character, Enemy enemy) {
        player.attack(character, enemy);
    }

    /**
     * Order an Enemy to attack a Player character.
     *
     * @param enemy     Enemy that will attack.
     * @param character Player character attacked.
     */
    public void enemyAttackChar(Enemy enemy, IPlayerCharacter character) {
        com.attack(enemy, character);
    }

    /**
     * Add a Player Character to Player's characters list.
     *
     * @param character Player character added.
     */
    public void addPlayerCharacter(IPlayerCharacter character) {
        character.addListener(characterEndsTurnHandler);
        player.addPlayerCharacter(character);
    }

    /**
     * Add an Enemy to Computer's enemies list.
     *
     * @param enemy Enemy added.
     */
    public void addEnemy(Enemy enemy) {
        enemy.addListener(characterEndsTurnHandler);
        com.addEnemy(enemy);
    }

    /**
     * Add a Weapon to Player's weapons list.
     *
     * @param weapon Weapon added.
     */
    public void addWeapon(IWeapon weapon) {
        player.addWeapon(weapon);
    }

    /**
     * Remove a character from the Player's character list.
     *
     * @param character Weapon to remove.
     */
    public void removePlayerCharacter(IPlayerCharacter character) {
        player.removePlayerCharacter(character);
    }

    /**
     * Remove an enemy from the Computer's enemy list.
     *
     * @param enemy Enemy to remove.
     */
    public void removeEnemy(Enemy enemy) {
        com.removeEnemy(enemy);
    }

    /**
     * Remove a weapon from the Player's inventory.
     *
     * @param weapon Weapon to remove.
     */
    public void removeWeapon(IWeapon weapon) {
        player.removeWeapon(weapon);
    }

    /**
     * Equip a weapon to a selected Player character.
     *
     * @param weapon    Weapon added.
     * @param character Character that will equip the weapon.
     */
    public void equipWeapontoCharacter(IWeapon weapon, IPlayerCharacter character) throws InvalidEquipException {
        player.equipWeapon(weapon, character);
    }

    /**
     * Change the actualcharacter's weapon.
     */
    public void changeWeapon(IWeapon weapon) throws InvalidEquipException {
        actualCharacter.beOrderedToEquipBy(this, weapon);
    }

    /**
     * Remove a dead character from the list of characters.
     *
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
     *
     * @param deadEnemy Enemy who died.
     */
    public void onEnemyDeath(Enemy deadEnemy) {
        removeEnemy(deadEnemy);
        System.out.println(deadEnemy.getName() + " has died.");
        //If the player lost the game
        if (com.getCharacters().isEmpty()) {
            player.win();
            player.clearList();
            System.out.println("GAME OVER You Win.");
        }
    }

    /**
     * Print a message when a character ends his/her turn.
     *
     * @param message message printed.
     */
    public void characterEndsTurn(String message) {
        actualCharacter = null;
    }

    /**
     * Print a message when a character is ready to battle.
     *
     * @param character character Ready.
     */
    public void characterIsWaiting(ICharacter character) {
        System.out.println(character.getName() + " is ready to battle.");
    }

    /**
     * Set the actualPhase
     *
     * @param phase Game's phase selected
     */
    public void setPhase(Phase phase) {
        this.actualPhase = phase;
        phase.setController(this);
    }

    /**
     * Return the actual Phase
     */
    public Phase getActualPhase() {
        return actualPhase;
    }


    /**
     * Try to change the actualCharacter's weapon
     *
     * @param weapon
     */
    public void trytoChangeWeapon(IWeapon weapon) throws InvalidEquipException, InvalidDecisionException {
        actualPhase.changeWeapon(weapon);
    }

    /**
     * Try to attack a Character with the actualCharacter
     *
     * @param character character attacked
     */
    public void tryToAttack(ICharacter character) throws InvalidDecisionException {
        actualPhase.attack(character);
        tryToPick();
    }

    /**
     * Try to pick a Character from turn's queue.
     */
    public void tryToPick() {
        try {
            actualPhase.pickCharacterFromQueue();
            if (actualCharacter.getLifePoints()<=0) {
                setPhase(new EndPhase(this));
                Thread.sleep(500);
                tryToPick();
            }
        } catch (InvalidDecisionException | InvalidTransitionException | InterruptedException ignored) {
            System.out.println("Nobody is ready yet");
        }

    }

    /**
     * Try to change the actual Phase to SelectingAttackTargetPhase
     */
    public void tryToStartAttack() throws InvalidTransitionException {
        actualPhase.toSelectingAttackTargetPhase();
    }

    /**
     * Return enemies list's size.
     */
    public int getEnemiesListSize() {
        return getEnemiesList().size();
    }

    /**
     * Return an enemy from List.
     * @param i position of the enemy in the enemies List.
     */
    public ICharacter getEnemy(int i) {
        return com.getCharacters().get(i);
    }

    /**
     * Return enemy's name if it's an available enemy.
     * @param i position of the enemy in the enemies List.
     */
    public String getEnemyName(int i) {
        return com.getCharacters().get(i).getName();
    }

    /**
     * Return enemy's HP if it's an available enemy.
     * @param i position of the enemy in the enemies List.
     */
    public String getEnemyHP(int i) {
        return Integer.toString(com.getCharacters().get(i).getLifePoints());
    }

    /**
     * Return enemy's Attack Points if it's an available enemy.
     * @param i position of the enemy in the enemies List.
     */
    public String getEnemyAttack(int i) {
        return Integer.toString(com.getCharacters().get(i).getDamage());
    }

    /**
     * Return enemy's Defense Points if it's an available enemy.
     * @param i position of the enemy in the enemies List.
     */
    public String getEnemyDefense(int i) {
        return Integer.toString(com.getCharacters().get(i).getDefense());
    }

    /**
     * Return playerCharacter list's size.
     */
    public int getPlayerListSize() {
        return getPlayerCharactersList().size();
    }

    /**
     * Return character's name if it's an available character.
     * @param i position of the character in character List.
     */
    public String getPlayerCharacterName(int i) {
        return player.getCharacters().get(i).getName();
    }

    /**
     * Return character's HP if it's an available character.
     * @param i position of the character in character List.
     */
    public String getPlayerCharacterHP(int i) {
        return Integer.toString(player.getCharacters().get(i).getLifePoints());
    }

    /**
     * Return character's Attack Points if it's an available character.
     * @param i position of the character in character List.
     */
    public String getPlayerCharacterAttack(int i) {
        return Integer.toString(player.getCharacters().get(i).getDamage());
    }

    /**
     * Return character's Defense Points if it's an available character.
     * @param i position of the character in character List.
     */
    public String getPlayerCharacterDefense(int i) {
        return Integer.toString(player.getCharacters().get(i).getDefense());
    }

    /**
     * Return character's weapon if it's an available character.
     * @param i position of the character in character List.
     */
    public String getPlayerCharacterWeaponName(int i) {
        return player.getCharacters().get(i).getEquippedWeapon().getName();
    }

    /**
     * Return character's class if it's an available character.
     * @param i position of the character in character List.
     */
    public String getPlayerCharacterClass(int i) {
        return player.getCharacters().get(i).getClassName();
    }

    /**
     * Return actualCharacter's name.
     */
    public String getActualCharacterName() {
        if (getActualCharacter() == null) {
            return "";
        } else {
            return getActualCharacter().getName();
        }
    }

    /**
     * Return player inventory's size.
     */
    public int getInventorySize() {
        return getPlayerInventory().size();
    }

    /**
     * Return weapon's name if it's an available weapon.
     * @param i position of the weapon in the character List.
     */
    public String getWeaponName(int i) {
        return getPlayerInventory().get(i).getName();
    }

    /**
     * Return weapon's Attack Points if it's an available weapon.
     * @param i position of the weapon in the character List.
     */
    public String getWeaponAttack(int i) {
        return Integer.toString(getPlayerInventory().get(i).getDamage());
    }

    /**
     * Return weapon's Weight if it's an available weapon.
     * @param i position of the weapon in the character List.
     */
    public String getWeaponWeight(int i) {
        return Integer.toString(getPlayerInventory().get(i).getWeight());
    }

    /**
     * Return actualPhase's name.
     */
    public String getActualPhaseName() {
        return getActualPhase().getName();
    }

    /**
     * Calculates the damage dealt by a Character to another
     * @param attacker Character who attacks.
     * @param attacked Character who is attacked.
     * @return An String with the damage.
     */
    public String getDamageDealt(ICharacter attacker, ICharacter attacked) {
        String lastLifePoints = Integer.toString(attacked.getLifePoints());
        if ((attacker.getDamage() - attacked.getDefense()) >= attacked.getLifePoints()) {
            return lastLifePoints + " HP. " + attacked.getName() + " died.";
        } else {
            return Integer.toString(attacker.getDamage() - attacked.getDefense()) + " HP. ";
        }
    }


    /**
     * Makes the enemyMove when it's an enemy's turn.
     * @param text Text of the lastAction before this attack.
     * @return A String thath announces the last attack.
     */
    public String makeEnemyMove(String text) {
        Random randomNumber = new Random();
        List<ICharacter> characterList = new ArrayList<>();
        List<ICharacter> possibleCharacterList = player.getCharacters();
        for (ICharacter characterTemp: possibleCharacterList) {
            if (characterTemp.getLifePoints() >0) {
                characterList.add(characterTemp);
            }
        }
        ICharacter character = characterList.get(randomNumber.nextInt(characterList.size()));
        ICharacter enemy = actualCharacter;
        if(enemy.getLifePoints() > 0) {
            try {
                tryToStartAttack();
                int lastLifePoints = character.getLifePoints();
                tryToAttack(character);
                if(character.getLifePoints() <= 0) {
                    return enemy.getName() + " attacked " + character.getName() + " and did " +
                            lastLifePoints + " HP.  " + character.getName() + " died.";
                }
                return enemy.getName() + " attacked " + character.getName() + " and did " +
                        Integer.toString(enemy.getDamage() - character.getDefense()) + " HP.";
            } catch (InvalidTransitionException e) {
                System.out.println("Invalid transition Enemy attack");
            } catch (InvalidDecisionException e) {
                System.out.println("Invalid Decision Enemy attack");
            }
        } else {
            setPhase(new EndPhase(this));
        }

        return text;
    }
}
