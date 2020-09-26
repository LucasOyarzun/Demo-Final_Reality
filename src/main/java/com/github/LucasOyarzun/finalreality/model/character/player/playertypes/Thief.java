package com.github.LucasOyarzun.finalreality.model.character.player.playertypes;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.posibleweapons.Bow;
import com.github.LucasOyarzun.finalreality.model.weapon.posibleweapons.Staff;
import com.github.LucasOyarzun.finalreality.model.weapon.posibleweapons.Sword;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

public class Thief extends AbstractPlayerCharacter {

    public Thief(@NotNull String name,
                     @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, turnsQueue, CharacterClass.THIEF);
    }

    @Override
    public void equip(AbstractWeapon weapon) {
        if (weapon instanceof Sword || weapon instanceof Staff || weapon instanceof Bow) {
            this.equippedWeapon = weapon;
        }
    }
}
