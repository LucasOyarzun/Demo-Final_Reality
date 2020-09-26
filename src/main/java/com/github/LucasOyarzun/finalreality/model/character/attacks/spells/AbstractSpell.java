package com.github.LucasOyarzun.finalreality.model.character.attacks.spells;
import com.github.LucasOyarzun.finalreality.model.character.attacks.AttackEffects;
import com.github.LucasOyarzun.finalreality.model.character.attacks.AttackTypes;
import org.jetbrains.annotations.NotNull;

/**
 *A class that holds the information of the spells.
 */
public class AbstractSpell {

    /**
     * Creates a new character.
     *
     * @param cost
     *     the spell's cost
     * @param name
     *     the spell's name
     * @param type
     *     the spell's type ( white or black spell)
     * @param secondEffect
     *     the spell's second effect
     */
    private final int cost;
    private final String name;
    protected SpellsTypes type;
    protected AttackEffects effect;
    protected AttackTypes attackType;
    protected SpellsTypes spellType;

    protected AbstractSpell(@NotNull String name, int cost, SpellsTypes spellType) {
        this.cost = cost;
        this.name = name;
        this.type = spellType;
        this.effect = AttackEffects.JUST_DAMAGE;
        this.attackType = AttackTypes.MAGIC;
        this.spellType = null;
    }
}
