package com.github.LucasOyarzun.finalreality.model.character.attacks;

public class Attack {
    private final int damage;
    private final AttackTypes type;
    private final AttackEffects attackEffect;

    public Attack (int damage, AttackTypes type, AttackEffects attackEffect) {
        this.damage = damage;
        this.type = type;
        this.attackEffect = attackEffect;
    }

    public Attack (int damage) {
        this.damage = damage;
        this.type = AttackTypes.NORMAL;
        this.attackEffect = AttackEffects.JUST_DAMAGE;
    }

    public AttackEffects getEffect() {
        return attackEffect;
    }

    public int getDamage() {
        return damage;
    }
    public AttackTypes getType() {
        return type;
    }
}
