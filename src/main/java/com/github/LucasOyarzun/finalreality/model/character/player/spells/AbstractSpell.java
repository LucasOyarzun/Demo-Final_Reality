package com.github.LucasOyarzun.finalreality.model.character.player.spells;

import org.jetbrains.annotations.NotNull;

public class AbstractSpell {
    private int cost;
    private String name;
    private SpellsTypes type;

    protected AbstractSpell(@NotNull String name, int cost, SpellsTypes type) {
        this.cost = cost;
        this.name = name;
        this.type = type;
    }
}
