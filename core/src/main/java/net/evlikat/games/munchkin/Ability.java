package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.player.Player;

/**
 * Ability
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public abstract class Ability extends Door {

    public abstract void play(Player player, Game game);
}
