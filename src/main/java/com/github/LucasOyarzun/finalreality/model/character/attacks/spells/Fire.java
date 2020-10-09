package com.github.LucasOyarzun.finalreality.model.character.attacks.spells;

import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;
import com.github.LucasOyarzun.finalreality.model.character.Status;

public class Fire extends AbstractWhiteSpell {

    public Fire() {
        super( "Heal", 15);
        this.effect = Status.BURNED;
    }
    public void doEffect(AbstractCharacter objective) {
        objective.loseLife(this.magicDamage);
        objective.beAffected(this.effect, magicDamage); // 20% de probabilidad
    }
}
