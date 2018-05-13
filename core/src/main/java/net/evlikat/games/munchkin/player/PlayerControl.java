package net.evlikat.games.munchkin.player;

import net.evlikat.games.munchkin.Battle;
import net.evlikat.games.munchkin.Card;
import net.evlikat.games.munchkin.utils.Dice;
import net.evlikat.games.munchkin.utils.DiceFactory;

import java.util.List;

/**
 * PlayerControl
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class PlayerControl {

    private final DiceFactory diceFactory;

    public PlayerControl(DiceFactory diceFactory) {
        this.diceFactory = diceFactory;
    }

    public Player askChoosePlayer(List<? extends Player> players) {
        // todo:
        if (players.size() == 1) {
            return players.get(0);
        }
        return null;
    }

    public <C extends Card> C askChooseCard(List<C> cards) {
        // todo:
        if (cards.size() == 1) {
            return cards.get(0);
        }
        return null;
    }

    public boolean ask(String message) {
        // todo:
        return false;
    }

    public boolean freePlay() {
        // todo:
        return false;
    }

    public boolean battlePlay(Battle battle) {
        // todo:
        return false;
    }

    public boolean otherBattlePlay(Battle battle) {
        // todo:
        return false;
    }

    public boolean beforeRunAwayPlay(Battle battle) {
        // todo:
        return false;
    }

    public boolean afterSuccessfulRunAwayPlay(Battle battle) {
        // todo:
        return false;
    }

    public Dice dice() {
        return diceFactory.dice();
    }
}
