package com.github.LucasOyarzun.finalreality.model.character.attacks.spells;
import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;
import com.github.LucasOyarzun.finalreality.model.character.Status;
import org.jetbrains.annotations.NotNull;

/**
 *A class that holds the information of the spells.
 */
public class AbstractSpell implements ISpell {

    /**
     * Creates a new character.
     *
     * @param cost
     *     the spell's cost
     * @param name
     *     the spell's name
     * @param type
     *     the spell's type ( white or black spell)
     */

    private final int cost;
    private final String name;
    protected SpellsTypes type;
    protected Status effect;
    protected int magicDamage;

    protected AbstractSpell(@NotNull String name, int cost, SpellsTypes spellType) {
        this.cost = cost;
        this.name = name;
        this.type = null;
        this.effect = Status.NORMAL;
        this.magicDamage = 0;
    }
    public int getDamage() {
        return this.magicDamage;
    }

    public void setDamage(int amount) {
        this.magicDamage = amount;
    }
    public int getCost() {
        return this.cost;
    }

    public Status getEffect() {
        return this.effect;
    }
    public SpellsTypes getType() {
        return this.type;
    }

    public void doEffect(AbstractCharacter objective) {
    }
}
