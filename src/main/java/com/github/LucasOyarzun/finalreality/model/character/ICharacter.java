package com.github.LucasOyarzun.finalreality.model.character;

import com.github.LucasOyarzun.finalreality.controller.GameController;
import com.github.LucasOyarzun.finalreality.controller.IEventHandler;
import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

  /**
   * Adds this character to the turns queue.
   */
  void addToQueue();

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * Return this character's defense.
   */
  int getDefense();

  /**
   * Return this character's Life Points.
   */
  int getLifePoints();

  /**
   * Return this character's weight.
   */
  int getWeight();

  /**
   * Return this character's AttackPoints
   */
  int getDamage();

  /**
   * Decrease this character's life Points by a int value.
   */
  void loseLife(int cantidad);

  /**
   * Return true is this character's Life Points are over 0, else, return false.
   */
  boolean isAlive();

  /**
   * A method to attack another character in game.
   * @param character character attacked.
   */
  void attack(ICharacter character);
  /**
   * A method to decide what to do when character attacks an enemy.
   * @param enemy Computer's character.
   */
  void attackEnemy(Enemy enemy);

  /**
   * A method to decide what to do when character attacks an Player Character.
   * @param character Player's character.
   */
  void attackPlayerCharacter(IPlayerCharacter character);

  /**
   * A method to decide what to do when this character is attacked.
   * @param character character who attacked.
   */
  void beAttackedBy(ICharacter character);

  /**
   * Add a Listener to this character.
   * @param handler listener.
   */
  void addListener(IEventHandler handler);

  /**
   * Attack ordered by a controller.
   * @param controller Controller who ordered the attack.
   */
  void controllerAttack(GameController controller);

  /**
   * If it's a PlayerCharacter, it will equip a weapon ordered by a controller.
   * @param gameController Controller who ordered.
   */
  void beOrderedToEquipBy(GameController gameController, IWeapon weapon);

  /**
   * Return true if this ICharacter is an Enemy;
   */
  boolean isEnemy();
}
