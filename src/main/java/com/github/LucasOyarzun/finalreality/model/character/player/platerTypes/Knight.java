package com.github.LucasOyarzun.finalreality.model.character.player.platerTypes;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import com.github.LucasOyarzun.finalreality.model.character.player.PlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes.Axe;
import com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes.Knife;
import com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes.Sword;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

public class Knight extends PlayerCharacter {
    /**
     * Creates a new Engineer.
     *
     * @param name           the knight's name
     * @param turnsQueue     the queue with the characters waiting for their turn
     * @param lifeP          the knight's lifePoints
     * @param def            the knight's defense
     *
     */
    public Knight(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue,
                  int lifeP, int def) {
        super(name, turnsQueue, CharacterClass.KNIGHT, lifeP, def);
    }

    /** Equip a weapon just in case that it's an Axe, a Knife or a Sword*/
    @Override
    public void equip(AbstractWeapon weapon) {
        if (weapon instanceof Knife || weapon instanceof Sword || weapon instanceof Axe) {
            super.equip(weapon);
        }
    }
}
