package net.evlikat.games.munchkin.cards.monsters;

import net.evlikat.games.munchkin.Monster;
import net.evlikat.games.munchkin.player.Player;

/**
 * LameGoblin
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class LameGoblin extends Monster {

    public LameGoblin() {
        super(1, 1);
    }

    @Override
    public int runAwayLevel(Player player) {
        return super.runAwayLevel(player) - 1;
    }

    @Override
    public void badStuff(Player player) {
        player.loseLevels(1);
    }
}
