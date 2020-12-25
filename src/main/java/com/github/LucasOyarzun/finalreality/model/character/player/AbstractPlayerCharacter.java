package com.github.LucasOyarzun.finalreality.model.character.player;

import com.github.LucasOyarzun.finalreality.controller.GameController;
import com.github.LucasOyarzun.finalreality.controller.IEventHandler;
import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;
import com.github.LucasOyarzun.finalreality.model.character.Enemy;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;

import java.beans.PropertyChangeSupport;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.LucasOyarzun.finalreality.model.weapon.classes.*;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements
        IPlayerCharacter {

  private final PropertyChangeSupport characterEndsTurn = new PropertyChangeSupport(this);

  protected IWeapon equippedWeapon = null;

  /**
   * Creates a new character.
   *
   * @param name       the character's name
   * @param lifePoints the lifePoints of this character
   * @param defense    the defense of this character
   * @param turnsQueue the queue with the characters waiting for their turn
   */
  public AbstractPlayerCharacter(@NotNull String name, final int lifePoints, final int defense,
                                 @NotNull BlockingQueue<ICharacter> turnsQueue) {
    super(name, lifePoints, defense, turnsQueue);
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor
              .schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
    characterEndsTurn.firePropertyChange(this.getName() +" ends turn ",
            null,
            this.getName() + " ends turn");
  }

  @Override
  public int hashCode() {
    return Objects
            .hash(super.hashCode(), AbstractPlayerCharacter.class, getEquippedWeapon());
  }

  @Override
  public boolean equals(final Object o) {

    final AbstractPlayerCharacter that = (AbstractPlayerCharacter) o;
    return Objects.equals(getEquippedWeapon(), that.getEquippedWeapon())
            && super.equals(o);
  }

  @Override
  public IWeapon getEquippedWeapon() {
    return equippedWeapon;
  }

  @Override
  public int getDamage() {
    return equippedWeapon.getDamage();
  }

  public int getWeight() {
    if (equippedWeapon != null) {
      return equippedWeapon.getWeight();
    } else{
      return 0;
    }
  }
  @Override
  public void equip(IWeapon weapon) {
    weapon.beEquipedBy(this);
  }

  @Override
  public void equipAxe(Axe axe) {
  }

  @Override
  public void equipBow(Bow bow) {
  }

  @Override
  public void equipKnife(Knife knife) {
  }

  @Override
  public void equipStaff(Staff staff) {
  }

  @Override
  public void equipSword(Sword sword) {
  }

  @Override
  public void attackEnemy(Enemy enemy) {
    if (enemy.isAlive()) {
      int damage = this.getDamage() - enemy.getDefense();
      enemy.loseLife(this.getDamage() - enemy.getDefense());
    }
  }

  @Override
  public void attackPlayerCharacter(IPlayerCharacter character) {}

  @Override
  public void beAttackedBy(ICharacter character) {
    character.attackPlayerCharacter(this);
  }

  @Override
  public void controllerAttack(GameController controller){
    controller.attackPlayerCharacter(this);
  }

  /**
   * Add a Listener to this class.
   * @param handler listener.
   */
  public void addListener(IEventHandler handler) {
    characterEndsTurn.addPropertyChangeListener(handler);
  }

  @Override
  public void beOrderedToEquipBy(GameController gameController, IWeapon weapon) {
    gameController.equipWeapontoCharacter(weapon, this);
  }

  @Override
  public boolean isEnemy() {
    return false;
  }

}

