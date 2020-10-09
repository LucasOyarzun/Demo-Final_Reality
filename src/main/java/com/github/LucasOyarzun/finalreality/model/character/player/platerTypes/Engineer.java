package com.github.LucasOyarzun.finalreality.model.character.player.platerTypes;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import com.github.LucasOyarzun.finalreality.model.character.player.PlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes.Axe;
import com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes.Bow;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

public class Engineer extends PlayerCharacter {
    /**
     * Creates a new Engineer.
     *
     * @param name           the character's name
     * @param turnsQueue     the queue with the characters waiting for their turn
     */
    public Engineer(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue,
                    int lifeP, int def) {
        super(name, turnsQueue, CharacterClass.ENGINEER, lifeP, def);
    }

    @Override
    public void equip(AbstractWeapon weapon) {
        if (weapon instanceof Axe || weapon instanceof Bow) {
            super.equip(weapon);
        }
    }


}