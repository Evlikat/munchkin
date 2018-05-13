package net.evlikat.games.munchkin.cards.curses;

import net.evlikat.games.munchkin.Curse;
import net.evlikat.games.munchkin.Equipment;
import net.evlikat.games.munchkin.Game;
import net.evlikat.games.munchkin.cards.Item;
import net.evlikat.games.munchkin.utils.CollectionUtils;
import net.evlikat.games.munchkin.player.Player;

import java.util.List;

/**
 * TrulyObnoxiousCurse
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class TrulyObnoxiousCurse extends Curse {

    @Override
    public void apply(Player player, Game game) {
        List<Equipment> maxBonusEquippable = CollectionUtils.allMaxOf(player.allEquipment(), e -> e.bonus(player));
        Item item;
        if (maxBonusEquippable.isEmpty()) {
            return;
        } else if (maxBonusEquippable.size() == 1) {
            item = maxBonusEquippable.get(0);
        } else {
            item = player.askChooseCard(maxBonusEquippable);
        }
        item.moveTo(game.getRoutingDiscardPile());
    }
}
