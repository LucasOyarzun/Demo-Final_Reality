package com.github.LucasOyarzun.finalreality.model.character.player.playertypes;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.posibleweapons.Axe;
import com.github.LucasOyarzun.finalreality.model.weapon.posibleweapons.Sword;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 *A class that holds the information and modules of the knights
 */

public class Knight extends AbstractPlayerCharacter {

    public Knight(@NotNull String name,
                     @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, turnsQueue, CharacterClass.KNIGHT);
    }

    @Override
    public void equip(AbstractWeapon weapon) {
        if (weapon instanceof Sword || weapon instanceof Axe) {
            this.equippedWeapon = weapon;
        }
    }
}