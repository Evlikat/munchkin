package net.evlikat.games.munchkin.cards.curses;

import net.evlikat.games.munchkin.Curse;
import net.evlikat.games.munchkin.Game;
import net.evlikat.games.munchkin.cards.Item;
import net.evlikat.games.munchkin.player.Player;

/**
 * LoseOneSmallItem
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class LoseOneSmallItem extends Curse {

    @Override
    public void apply(Player player, Game game) {
        Item item = player.askChooseItem();
        item.moveTo(game.getRoutingDiscardPile());
    }
}
