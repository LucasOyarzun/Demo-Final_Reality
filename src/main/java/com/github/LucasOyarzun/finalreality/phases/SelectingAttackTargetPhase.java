package com.github.LucasOyarzun.finalreality.phases;

import com.github.LucasOyarzun.finalreality.controller.GameController;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;


/**
 * A class that reference to the Phase when the Player is selecting the Enemy to attack.
 * */
public class SelectingAttackTargetPhase extends Phase {

    public SelectingAttackTargetPhase(GameController controller) {
        super(controller);
        this.canAttack = true;
    }

    @Override
    public void toEndPhase() {
        changePhase(new EndPhase(controller));
    }

    @Override
    public void attack(ICharacter selectedCharacter) throws InvalidDecisionException {
        super.attack(selectedCharacter);
        if (!controller.getPlayer().askWin() && !controller.getCom().askWin()) { // If Nobody won yet
            toEndPhase();
        }
        else {
            System.out.println("Thanks for playing");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SelectingAttackTargetPhase)) {
            return false;
        }
        SelectingAttackTargetPhase phase = (SelectingAttackTargetPhase) o;
        return controller == phase.controller;
    }
}
