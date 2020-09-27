package com.github.LucasOyarzun.finalreality.model.character.attacks.spells;

import com.github.LucasOyarzun.finalreality.model.character.AbstractCharacter;
import com.github.LucasOyarzun.finalreality.model.character.Status;

public interface ISpell {

    int getDamage();

    void setDamage(int amount);

    int getCost();

    Status getEffect();

    void doEffect(AbstractCharacter objective);
}
