package com.github.LucasOyarzun.finalreality.model.character.attacks.spells;

import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;
import com.github.LucasOyarzun.finalreality.model.character.Status;

/**
 *A class that holds the information of the Poison spell.
 */

public class Poison extends AbstractWhiteSpell {

    public Poison() {
        super( "Poison", 40);
        this.effect = Status.POISONED;
    }
    public void doEffect(AbstractCharacter objective) {
        objective.beAffected(this.effect, magicDamage);
    }
}
