package com.github.LucasOyarzun.finalreality.model.character.attacks.spells;

import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;
import com.github.LucasOyarzun.finalreality.model.character.Status;

/**
 *A class that holds the information of the Paralysis spell.
 */

public class Paralysis extends AbstractWhiteSpell {

    public Paralysis() {
        super("Paralysis", 25);
        this.effect = Status.PARALYZED;
    }
    public void doEffect(AbstractCharacter objective) {
        objective.beAffected(this.effect, magicDamage);
    }

}
