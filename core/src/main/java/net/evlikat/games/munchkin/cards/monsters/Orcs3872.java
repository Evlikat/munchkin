package net.evlikat.games.munchkin.cards.monsters;

import net.evlikat.games.munchkin.Monster;
import net.evlikat.games.munchkin.player.Player;
import net.evlikat.games.munchkin.player.race.Dwarf;

/**
 * Orcs3872
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Orcs3872 extends Monster {

    public Orcs3872() {
        super(10, 3);
    }

    @Override
    public int effectiveLevelAgainst(Player player) {
        return super.effectiveLevelAgainst(player) + (player.is(Dwarf.class) ? 6 : 0);
    }

    @Override
    public void badStuff(Player player) {
        int thrown = player.dice().thrown();
        if (thrown <= 2) {
            player.die();
        } else {
            player.loseLevels(thrown);
        }
    }
}
