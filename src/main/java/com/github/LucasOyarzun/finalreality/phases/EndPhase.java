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
    public void pickCharacterFromQueue() throws InvalidDecisionException, InvalidTransitionException {
        super.pickCharacterFromQueue();
        if (controller.getActualCharacter()!=null) {
            if(controller.getActualCharacter().isEnemy()) {
                makeEnemyMove();
            } else {
                toMainPhase();
            }
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

    /**
     * If the picked Character is an Enemy, the Enemy attacks ands Ends its turn.
     * @throws InvalidTransitionException
     */
    private void makeEnemyMove() throws InvalidTransitionException {
        Random randomNumber = new Random();
        List<ICharacter> characterList = controller.getPlayer().getCharacters();
        ICharacter character = characterList.get(randomNumber.nextInt(characterList.size()));
        toMainPhase();
        controller.tryToStartAttack();
        controller.tryToAttack(character);
    }
}
