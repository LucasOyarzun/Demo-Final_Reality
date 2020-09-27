package com.github.LucasOyarzun.finalreality.model.character.player.playertypes;

import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.attacks.spells.AbstractSpell;
import com.github.LucasOyarzun.finalreality.model.character.attacks.spells.SpellsTypes;
import com.github.LucasOyarzun.finalreality.model.character.player.CharacterClass;
import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeapon;
import com.github.LucasOyarzun.finalreality.model.weapon.posibleweapons.Staff;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 *A class that holds the information and modules of the White mages.
 */

public class WhiteMage extends AbstractMage {
    public WhiteMage(@NotNull String name,
                     @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, turnsQueue, CharacterClass.WHITE_MAGE);
    }

    @Override
    public void equip(AbstractWeapon weapon) {
        if (weapon instanceof Staff) {
            this.equippedWeapon = weapon;
        }
    }

    @Override
    public void castSpell(AbstractSpell spell, AbstractCharacter objective) {
        if (spell.getType() == SpellsTypes.WHITE_SPELL) {
            spell.setDamage(this.getMagicDamage());
            spell.doEffect(objective);
            this.loseMana(spell.getCost());
        }
    }
}
