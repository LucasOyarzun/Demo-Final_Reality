package com.github.LucasOyarzun.finalreality.model.character.attacks.spells;


import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;

/**
 *A class that holds the information of the Heal spell.
 */

public class Heal extends AbstractWhiteSpell {

    public Heal() {
        super( "Heal", 15);
    }

    public void doEffect(AbstractCharacter objective) {
        int amount = (int)(objective.getMaxLife() * 0.3);
        objective.beHealed(amount);
    }
}
