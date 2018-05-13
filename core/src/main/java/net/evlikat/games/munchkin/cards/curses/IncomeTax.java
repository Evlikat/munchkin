package net.evlikat.games.munchkin.cards.curses;

import net.evlikat.games.munchkin.Curse;
import net.evlikat.games.munchkin.Game;
import net.evlikat.games.munchkin.cards.Item;
import net.evlikat.games.munchkin.player.Player;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * IncomeTax
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class IncomeTax extends Curse {

    @Override
    public void apply(Player player, Game game) {
        Item item = player.askChooseCard(player.allItems().collect(toList()));
        game.getPlayers().stream()
            .sorted(Comparator.comparing(Player::getIndex))
            .forEach(p -> {
                List<? extends Item> itemsToDiscard =
                    p.allItems().filter(i -> i.getPrice() >= item.getPrice()).collect(toList());
                if (itemsToDiscard.isEmpty()) {
                    // todo:
                    p.loseLevels(1);
                } else if (itemsToDiscard.size() == 1) {
                    itemsToDiscard.get(0).moveTo(game.getRoutingDiscardPile());
                } else {
                    Item itemToDiscard = p.askChooseCard(itemsToDiscard);
                    itemToDiscard.moveTo(game.getRoutingDiscardPile());
                }
            });
    }
}
