package com.github.LucasOyarzun.finalreality.model.character.player.playertypes;

import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.Status;
import com.github.LucasOyarzun.finalreality.model.character.attacks.spells.AbstractSpell;
import com.github.LucasOyarzun.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
/**
*A class that holds the information of the mages (Basically mana and posibility
*Of use spells.
*/
public class AbstractMage extends AbstractPlayerCharacter implements IMage, ICharacter {
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
    @Override
    public int getMana() {
        return mana;
    }
    public void loseMana(int cost) {
        this.mana -= cost;
    }

    public int getMagicDamage() {
        return equippedWeapon.getMagicDamage();
    }

    public void castSpell(AbstractSpell spell, AbstractCharacter objective) {
        spell.setDamage(this.getMagicDamage());
        spell.doEffect(objective);
        this.loseMana(spell.getCost());
    }

}
