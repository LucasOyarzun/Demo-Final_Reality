package com.github.LucasOyarzun.finalreality.model.character;

import com.github.LucasOyarzun.finalreality.model.character.attacks.Attack;
import com.github.LucasOyarzun.finalreality.model.character.attacks.AttackEffects;
import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez
 */
public abstract class AbstractCharacter implements ICharacter {

  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  private final CharacterClass characterClass;
  protected AbstractWeapon equippedWeapon = null;
  private int maxLifePoints;
  protected int lifePoints;
  protected int defensePoints;
  private Status status;
  private ScheduledExecutorService scheduledExecutor;

  protected AbstractCharacter(@NotNull String name,
      @NotNull BlockingQueue<ICharacter> turnsQueue,
      CharacterClass characterClass) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.characterClass = characterClass;
    this.status = Status.NULL;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    if (this instanceof AbstractPlayerCharacter) {
      scheduledExecutor
          .schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
    } else {
      var enemy = (Enemy) this;
      scheduledExecutor
          .schedule(this::addToQueue, enemy.getWeight() / 10, TimeUnit.SECONDS);
    }
  }

  /**
   * Adds this character to the turns queue.
   */
  private void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getLife() {
    return lifePoints;
  }

  @Override
  public int getDefense() {
    return defensePoints;
  }

  public void attack(AbstractCharacter attacked, int amount, Attack attack) {
      AttackEffects effect = attack.getEffect();
      int blocked = attacked.getDefense();
      int damage = amount - blocked;
      attacked.loseLife(damage);                 /**READJUST*/
  }

  @Override
  public void loseLife(int amount){
    this.lifePoints = this.lifePoints - amount;
    if (this.lifePoints <= 0) {
      this.die();
    }
  }

  @Override
  public void beHealed(int amount) {
    this.lifePoints = this.lifePoints + amount;
  }

  @Override
  public void equip(AbstractWeapon weapon) {
    if (this instanceof AbstractPlayerCharacter) {
      this.equippedWeapon = weapon;
    }
  }

  @Override
  public void die() {
    this.status = Status.DEAD;
  }

  @Override
  public AbstractWeapon getEquippedWeapon() {
    return equippedWeapon;
  }

  @Override
  public CharacterClass getCharacterClass() {
    return characterClass;
  }
}
