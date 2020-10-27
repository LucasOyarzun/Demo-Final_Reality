package com.github.LucasOyarzun.finalreality.model.character.player.platerTypes;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import com.github.LucasOyarzun.finalreality.model.character.player.PlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.weaponTypes.*;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

public class Thief extends PlayerCharacter {
    /**
     * Creates a new Engineer.
     *
     * @param name           the Thief's name
     * @param turnsQueue     the queue with the characters waiting for their turn
     * @param lifeP          the Thief's lifePoints
     * @param def            the Thief's defense
     *
     */
    public Thief(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue,
                int lifeP, int def) {
        super(name, turnsQueue, CharacterClass.THIEF, lifeP, def);
    }

    @Override
    public void equipStaff(Staff staff) {
        this.equippedAbstractWeapon = staff;
    }

    @Override
    public void equipSword(Sword sword) {
        this.equippedAbstractWeapon = sword;
    }

    @Override
    public void equipBow(Bow bow) {
        this.equippedAbstractWeapon = bow;
    }
}
