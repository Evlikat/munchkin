package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.player.Player;

/**
 * Curse
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public abstract class Curse extends Door {

    public abstract void apply(Player player, Game game);
}
