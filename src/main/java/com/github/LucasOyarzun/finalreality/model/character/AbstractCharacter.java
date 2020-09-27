package com.github.LucasOyarzun.finalreality.model.character;

import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;

import java.util.ArrayList;
import java.util.List;
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
  protected int maxLifePoints;
  protected int lifePoints;
  protected int defensePoints;
  private Status status;
  private int statusValue;
  protected int weight;
  private ScheduledExecutorService scheduledExecutor;
  protected List<AbstractWeapon> inventario;

  protected AbstractCharacter(@NotNull String name,
      @NotNull BlockingQueue<ICharacter> turnsQueue,
      CharacterClass characterClass) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.characterClass = characterClass;
    this.status = Status.NORMAL;
    this.weight = this.equippedWeapon.getWeight();
    this.statusValue = 0;
    inventario = new ArrayList<AbstractWeapon>();
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
  public Status getStatus() {
    return this.status;
  }

  @Override
  public int getMaxLife() {
    return this.maxLifePoints;
  }
  @Override
  public int getLife() {
    return lifePoints;
  }

  @Override
  public int getDefense() {
    return defensePoints;
  }

  @Override
  public CharacterClass getCharacterClass() {
    return characterClass;
  }

  @Override
  public void attack(AbstractCharacter objective, int amount) {
      //amount en Enemy y PlayerCharacter
      int blocked = objective.getDefense();
      int damage = amount - blocked;
      objective.loseLife(damage);
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

  public void beAffected(Status status, int magicDamage) {
    this.status = status;
    this.statusValue = magicDamage;
  }

  @Override
  public void die() {
    this.status = Status.DEAD;
  }

  public void addEquip(AbstractWeapon weapon) {
    if (this instanceof AbstractPlayerCharacter) {
      inventario.add(weapon);
    }
  }
  @Override
  public void equip(AbstractWeapon weapon) {
    if ((this instanceof AbstractPlayerCharacter) &&
            (this.inventario.contains(weapon))) {
      this.equippedWeapon = weapon;
    }
  }

  @Override
  public AbstractWeapon getEquippedWeapon() {
    return equippedWeapon;
  }

}