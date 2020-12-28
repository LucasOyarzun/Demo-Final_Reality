package com.github.LucasOyarzun.finalreality.phases;

import com.github.LucasOyarzun.finalreality.controller.GameController;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.InvalidEquipException;
import com.github.LucasOyarzun.finalreality.model.weapon.IWeapon;

/**
 * A Class that represents differents moments in the game.
 */
public class Phase {
    protected GameController controller;
    protected boolean canChangeWeapon = false;
    protected boolean canAttack = false;
    protected boolean canPickCharacter = false;

    /**
     * Creates a Phase with the game's controller.
     * @param controller game's GameController.
     */
    public Phase(GameController controller) {
        setController(controller);
    }

    /**
     * Set the Controller of this Phase.
     * @param controller game's GameController.
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }

    /**
     * Change between Phases
     * @param phase new phase.
     */
    public void changePhase(Phase phase) {
        controller.setPhase(phase);
    }

    /**
     * Changes to MainPhase
     */
    public void toMainPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to MainPhase");
    }

    /**
     * Changes to SelectingAttackTargetPhase
     */
    public void toSelectingAttackTargetPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to SelectingTargetPhase");
    }

    /**
     * Changes to EndPhase
     */
    public void toEndPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to EndPhase");
    }

    /**
     * Changes actualCharacter's equippedWeapon.
     * @param weapon new weapon to equip.
     */
    public void changeWeapon(IWeapon weapon) throws InvalidDecisionException, InvalidEquipException {
        throw new InvalidDecisionException("Can't change weapon now");
    }

    /**
     * Attacks an objective with the actualCharacter.
     * @param character character attacked.
     */
    public void attack(ICharacter character) throws InvalidDecisionException {
        if (!canAttack) {
            throw new InvalidDecisionException("Can't attack now");
        } else {
            controller.attack(character);
        }
    }

    /**
     * Picks the first character in character turn's queue.
     * If picked character is an enemy, it make it do an attack.
     */
    public void pickCharacterFromQueue() throws InvalidDecisionException, InvalidTransitionException, InterruptedException {
        if (!canPickCharacter) {
            throw new InvalidDecisionException("Nobody is ready yet");
        } else {
            controller.pickCharacterFromQueue();
        }
    }

    /**
     * Return a String with the phase name.
     */
    public String getName() {
        return "";
    }
}
