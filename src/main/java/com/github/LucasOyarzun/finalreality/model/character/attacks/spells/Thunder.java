package com.github.LucasOyarzun.finalreality.model.character.attacks.spells;

import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;
import com.github.LucasOyarzun.finalreality.model.character.Status;

/**
 *A class that holds the information of the Thunder spell.
 */

public class Thunder extends AbstractBlackSpell {

    public Thunder() {
        super( "Thunder", 15);
        this.effect = Status.PARALYZED;
    }

    public void doEffect(AbstractCharacter objective) {
        objective.loseLife(this.magicDamage);
        objective.beAffected(this.effect, magicDamage); // 30% de probabilidad
    }
}
