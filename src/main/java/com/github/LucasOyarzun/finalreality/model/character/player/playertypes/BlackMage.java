package com.github.LucasOyarzun.finalreality.model.character.player.playertypes;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.posibleweapons.Knife;
import com.github.LucasOyarzun.finalreality.model.weapon.posibleweapons.Staff;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 *A class that holds the information and modules of the Black Mages
 */
public class BlackMage extends AbstractMage {

    public BlackMage(@NotNull String name,
                     @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, turnsQueue, CharacterClass.BLACK_MAGE);
    }

    @Override
    public void equip(AbstractWeapon weapon) {
        if (weapon instanceof Knife || weapon instanceof Staff) {
            this.equippedWeapon = weapon;
        }
    }
}