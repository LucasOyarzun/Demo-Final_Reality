package com.github.LucasOyarzun.finalreality.phases;

import com.github.LucasOyarzun.finalreality.controller.GameController;
import com.github.LucasOyarzun.finalreality.model.character.player.InvalidEquipException;
import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;

public class MainPhase extends Phase {

    /**
     * A class to reference the Phase when the player can make decisions.
     * @param controller
     */
    public MainPhase(GameController controller) {
        super(controller);
        this.canChangeWeapon = true;
    }

    @Override
    public void toSelectingAttackTargetPhase() {
        changePhase(new SelectingAttackTargetPhase(controller));
    }
    @Override
    public void changeWeapon(IWeapon weapon) throws InvalidEquipException {
        controller.changeWeapon(weapon);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MainPhase)) {
            return false;
        }
        MainPhase phase = (MainPhase) o;
        return controller == phase.controller;
    }

    @Override
    public String getName() {
        return "Main Phase";
    }
}
