package com.github.LucasOyarzun.finalreality.model.character.player;

import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public class PlayerCharacter extends AbstractCharacter {

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   * @param characterClass
   *     the class of this character
   * @param lifeP
   *     the lifePoints of this character
   * @param def
   *     the defense of this character
   */
  public PlayerCharacter(@NotNull String name,
      @NotNull BlockingQueue<ICharacter> turnsQueue,
      final CharacterClass characterClass,
      int lifeP, int def) {
    super(turnsQueue, name, characterClass, lifeP, def);
  }

  /**
   *
   * Equip a Weapon to the character
   * @param abstractWeapon
   *      the weapon that we will equip to the character
   */
  public void equip(AbstractWeapon abstractWeapon) {
      this.equippedAbstractWeapon = abstractWeapon;
  }

  /**
   *
   * @return character's equipped weapon
   *
   */
  public AbstractWeapon getEquippedWeapon() {
    return equippedAbstractWeapon;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getLifePoints(), getDefense(),getCharacterClass());
  }

  /**Compares an object and return if it's equals to this character*/
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PlayerCharacter)) {
      return false;
    }
    final PlayerCharacter that = (PlayerCharacter) o;
    return this.hashCode() == o.hashCode();
  }
}
