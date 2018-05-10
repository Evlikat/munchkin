package net.evlikat.games.munchkin.cards;

import net.evlikat.games.munchkin.Game;
import net.evlikat.games.munchkin.player.Player;

/**
 * OnceOnlyUsableItem
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public abstract class OnceOnlyUsableItem extends UsableItem {

    public OnceOnlyUsableItem(int price) {
        super(price, 1);
    }

    @Override
    public void play(Player user, Game game) {
        apply(user, game);
        user.lose(this);
    }

    protected abstract void apply(Player user, Game game);
}
