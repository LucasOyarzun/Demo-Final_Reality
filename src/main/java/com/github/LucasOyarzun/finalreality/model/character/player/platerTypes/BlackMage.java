package com.github.LucasOyarzun.finalreality.model.character.player.platerTypes;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import com.github.LucasOyarzun.finalreality.model.character.player.PlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes.Knife;
import com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes.Staff;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

public class BlackMage extends PlayerCharacter implements IMage{
    private int mana;
    /**
     * Creates a new Black Mage.
     *
     * @param name           the BlackMage's name
     * @param turnsQueue     the queue with the characters waiting for their turn
     * @param lifeP          the BlackMage's lifePoints
     * @param def            the BlackMage's defense
     * @param mana           the BlackMage's mana
     */
    public BlackMage(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue,
                     int lifeP, int def, int mana) {
        super(name, turnsQueue, CharacterClass.BLACK_MAGE, lifeP, def);
        this.mana = mana;
    }


    @Override
    public void equipStaff(Staff staff) {
        this.equippedAbstractWeapon = staff;
    }

    @Override
    public void equipKnife(Knife knife) {
        this.equippedAbstractWeapon = knife;
    }
}
