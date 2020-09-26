package com.github.LucasOyarzun.finalreality.model.character.attacks.spells;

import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;
import com.github.LucasOyarzun.finalreality.model.character.attacks.AttackEffects;

/**
 *A class that holds the information of the Fire spell.
 */

public class Fire extends AbstractBlackSpell {

    public Fire() {
        super( "Fire", 15);
        this.type = SpellsTypes.BLACK_SPELL;
    }

    public void causeEffect(AbstractCharacter attacked, AttackEffects effect) {
        this.effect = effect;
    }

}
