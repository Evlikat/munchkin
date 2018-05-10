package net.evlikat.games.munchkin.cards.monsters;

import net.evlikat.games.munchkin.Game;
import net.evlikat.games.munchkin.Monster;
import net.evlikat.games.munchkin.player.Player;

/**
 * Ghoulfiends
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Ghoulfiends extends Monster {

    public Ghoulfiends() {
        super(8, 2);
    }

    @Override
    public int playerEffectiveLevel(Player player) {
        return player.getLevel();
    }

    @Override
    public void badStuff(Player player, Game game) {
        player.setLevel(game.getPlayers().stream().mapToInt(Player::getLevel).min().orElse(1));
    }
}
