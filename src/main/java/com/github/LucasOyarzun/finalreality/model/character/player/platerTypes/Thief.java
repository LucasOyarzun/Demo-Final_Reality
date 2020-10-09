package com.github.LucasOyarzun.finalreality.model.character.player.platerTypes;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import com.github.LucasOyarzun.finalreality.model.character.player.PlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes.Bow;
import com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes.Staff;
import com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes.Sword;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

public class Thief extends PlayerCharacter {
    /**
     * Creates a new Thief.
     *
     * @param name           the character's name
     * @param turnsQueue     the queue with the characters waiting for their turn
     */
    public Thief(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue,
                int lifeP, int def) {
        super(name, turnsQueue, CharacterClass.THIEF, lifeP, def);
    }

    @Override
    public void equip(AbstractWeapon weapon) {
        if (weapon instanceof Sword || weapon instanceof Staff || weapon instanceof Bow) {
            super.equip(weapon);
        }
    }
}