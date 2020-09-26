package com.github.LucasOyarzun.finalreality.model.character.player.playertypes;

import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
/**
*A class that holds the information of the mages (Basically mana and posibility
*Of use spells.
*/
public class AbstractMage extends AbstractPlayerCharacter {
    /**
     * Creates a new mage.
     *
     * @param mana
     *     the amount of mage's mana
     */
    private int mana;

        public AbstractMage(@NotNull String name,
                                       @NotNull BlockingQueue<ICharacter> turnsQueue,
                                       final CharacterClass characterClass) {
            super(name, turnsQueue, characterClass);
        }
}
