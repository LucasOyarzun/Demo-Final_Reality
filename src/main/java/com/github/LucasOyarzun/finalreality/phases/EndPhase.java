package com.github.LucasOyarzun.finalreality.phases;

import com.github.LucasOyarzun.finalreality.controller.GameController;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;

import java.util.List;
import java.util.Random;

/**
 * A Class to reference the Phase when the player is waiting the character's queue.
 */
public class EndPhase extends Phase {
    public EndPhase(GameController controller) {
        super(controller);
        this.canPickCharacter = true;

    }

    @Override
    public void toMainPhase() {
        changePhase(new MainPhase(controller));
    }

    @Override
    public void pickCharacterFromQueue() throws InvalidDecisionException, InvalidTransitionException, InterruptedException {
        super.pickCharacterFromQueue();
        if (controller.getActualCharacter()!=null) {
                toMainPhase();
        } else {
            Thread.sleep(1000);
            pickCharacterFromQueue();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EndPhase)) {
            return false;
        }
        EndPhase phase = (EndPhase) o;
        return controller == phase.controller;
    }

    @Override
    public String getName() {
        return "End Phase";
    }
}
