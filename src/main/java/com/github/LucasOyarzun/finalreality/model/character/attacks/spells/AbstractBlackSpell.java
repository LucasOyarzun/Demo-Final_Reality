package com.github.LucasOyarzun.finalreality.model.character.attacks.spells;

import org.jetbrains.annotations.NotNull;

/**
 *A class that holds the information of the Black spells.
 */

public class AbstractBlackSpell extends AbstractSpell {

    public AbstractBlackSpell (@NotNull String name, int cost) {
        super(name, cost, SpellsTypes.BLACK_SPELL);
    }
    
}
