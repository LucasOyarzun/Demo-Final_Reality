package com.github.LucasOyarzun.finalreality.model.character.player.spells;

import org.jetbrains.annotations.NotNull;

public class AbstractWhiteSpell extends AbstractSpell {

    public AbstractWhiteSpell (@NotNull String name, int cost) {
        super(name, cost, SpellsTypes.WHITE_SPELL);
    }
}
