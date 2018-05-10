package net.evlikat.games.munchkin.exceptions;

import net.evlikat.games.munchkin.player.Player;

/**
 * GameOver
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class GameOver extends RuntimeException {

    private Player winner;

    public GameOver(Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }
}
