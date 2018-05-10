package net.evlikat.games.munchkin.cards.monsters;

import net.evlikat.games.munchkin.Game;
import net.evlikat.games.munchkin.Monster;
import net.evlikat.games.munchkin.player.Player;

/**
 * Pukachu
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Pukachu extends Monster {

    public Pukachu() {
        super(6, 2);
    }

    @Override
    public int levels(Player player) {
        // todo:
        return super.levels(player);
    }

    @Override
    public void badStuff(Player player, Game game) {
        player.hand().stream().forEach(c -> c.moveTo(game.getRoutingDiscardPile()));
    }
}
