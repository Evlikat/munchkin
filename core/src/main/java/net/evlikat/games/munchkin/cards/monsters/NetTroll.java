package net.evlikat.games.munchkin.cards.monsters;

import net.evlikat.games.munchkin.Game;
import net.evlikat.games.munchkin.Monster;
import net.evlikat.games.munchkin.cards.Item;
import net.evlikat.games.munchkin.utils.CollectionUtils;
import net.evlikat.games.munchkin.player.Player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * NetTroll
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class NetTroll extends Monster {

    public NetTroll() {
        super(10, 3);
    }

    @Override
    public void badStuff(Player player, Game game) {
        List<Player> playersOfMaxLevel = CollectionUtils.allMaxOf(
            game.getPlayers().stream().filter(p -> !p.equals(player)),
            Player::getLevel);
        playersOfMaxLevel.sort(Comparator.comparing(Player::getIndex));
        for (Player maxLevelPlayer : playersOfMaxLevel) {
            Item item = maxLevelPlayer.askChooseCard(player.allEquipment().collect(Collectors.toList()));
            if (item == null) {
                return;
            }
            item.moveTo(maxLevelPlayer.hand());
        }
    }
}
