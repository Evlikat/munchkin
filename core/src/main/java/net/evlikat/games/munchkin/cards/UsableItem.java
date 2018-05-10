package net.evlikat.games.munchkin.cards;

import net.evlikat.games.munchkin.Game;
import net.evlikat.games.munchkin.player.Player;

/**
 * UsableItem
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public abstract class UsableItem extends Item {

    private final int maxUsage;

    public UsableItem(int price, int maxUsage) {
        super(price);
        this.maxUsage = maxUsage;
    }

    public abstract void play(Player user, Game game);
}
