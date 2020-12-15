package com.github.LucasOyarzun.finalreality.model;

import com.github.LucasOyarzun.finalreality.controller.IEventHandler;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;

import java.util.ArrayList;

public interface IPlayer {

    /**
     * Return true if this player won the game.
     */
    boolean askWin();

    /**
     * The player wins the game.
     */
    void win();

    /**
     * Return this Player's list of enemies.
     */
    ArrayList<ICharacter> getCharacters();

    /**
     * empty the Player's character list.
     */
    void clearList();

    /**
     * Order an enemy to attack a character.
     * @param actual     character ordered.
     * @param objective character attacked.
     */
    void attack(ICharacter actual, ICharacter objective);

    /**
     * Add a handler to this Player.
     * @param handler  Handler of enemy's deaths.
     */
    void addListener(IEventHandler handler);

}
