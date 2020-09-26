package com.github.LucasOyarzun.finalreality.model.character.attacks.spells;

import org.jetbrains.annotations.NotNull;

/**
 *A class that holds the information of the White spells.
 */
public class AbstractWhiteSpell extends AbstractSpell {

    public AbstractWhiteSpell (@NotNull String name, int cost) {
        super(name, cost, SpellsTypes.WHITE_SPELL);
    }
}
