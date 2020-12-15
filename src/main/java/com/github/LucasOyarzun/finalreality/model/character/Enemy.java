package com.github.LucasOyarzun.finalreality.model.character;

import java.beans.PropertyChangeSupport;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.LucasOyarzun.finalreality.controller.GameController;
import com.github.LucasOyarzun.finalreality.controller.IEventHandler;
import com.github.LucasOyarzun.finalreality.model.character.player.IPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Lucas Oyarzun Mendez.
 */
public class Enemy extends AbstractCharacter {

  private final PropertyChangeSupport characterEndsTurn = new PropertyChangeSupport(this);

  private final int weight;
  private final int attack;

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to
   * play.
   *
   * @param name       the character's name.
   * @param lifePoints the character's lifePoints.
   * @param defense    the character's defense.
   * @param weight     the character's weight.
   * @param turnsQueue the queue with the characters waiting for their turn.
   */
  public Enemy(@NotNull final String name, final int lifePoints, final int defense,
               final int weight, final int attack,
               @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, lifePoints, defense, turnsQueue);
    this.weight = weight;
    this.attack = attack;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor
            .schedule(this::addToQueue, this.getWeight() / 10, TimeUnit.SECONDS);
    characterEndsTurn.firePropertyChange(this.getName() +" ends turn ",
            null,
            this.getName() + " ends turn");
  }

  @Override
  public int hashCode() {
    return Objects.hash(getWeight(), getDamage(), getDefense(),
            getLifePoints(), getName());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return hashCode() == enemy.hashCode();
  }

  @Override
  public int getWeight() {
    return weight;
  }

  @Override
  public int getDamage() {
    return attack;
  }


  @Override
  public void attackEnemy(Enemy enemy) {
  }

  @Override
  public void attackPlayerCharacter(IPlayerCharacter character) {
    if (character.isAlive()) {
      int damage = this.getDamage() - character.getDefense();
      character.loseLife(this.getDamage() - character.getDefense());

    }
  }

  @Override
  public void beAttackedBy(ICharacter character) {
    character.attackEnemy(this);
  }

  @Override
  public void controllerAttack(GameController controller){
    controller.attackEnemy(this);
  }

  @Override
  public void beOrderedToEquipBy(GameController gameController, IWeapon weapon) {
    //do nothing
  }

  @Override
  public void addListener(IEventHandler handler) {
    characterEndsTurn.addPropertyChangeListener(handler);
  }

  @Override
  public boolean isEnemy() {
    return true;
  }
}
