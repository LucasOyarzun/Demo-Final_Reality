package com.github.LucasOyarzun.finalreality.model.character.player.spells;

import org.jetbrains.annotations.NotNull;

public class AbstractBlackSpell extends AbstractSpell {

    public AbstractBlackSpell (@NotNull String name, int cost) {
        super(name, cost, SpellsTypes.BLACK_SPELL);
    }
}
