package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.player.Player;

/**
 * BattleAbility
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public abstract class BattleAbility extends Door {

    public abstract void play(Player player, Battle battle);
}
