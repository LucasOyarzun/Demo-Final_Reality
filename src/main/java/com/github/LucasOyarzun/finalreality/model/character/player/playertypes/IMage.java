package com.github.LucasOyarzun.finalreality.model.character.player.playertypes;

import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;
import com.github.LucasOyarzun.finalreality.model.character.attacks.spells.AbstractSpell;

public interface IMage {

    int getMana();

    void loseMana(int cost);

    int getMagicDamage();

    void castSpell(AbstractSpell spell, AbstractCharacter objective);
}
