package net.evlikat.games.munchkin.cards.treasures;

import net.evlikat.games.munchkin.Equipment;
import net.evlikat.games.munchkin.player.race.Dwarf;
import net.evlikat.games.munchkin.player.Player;

import static net.evlikat.games.munchkin.SlotType.ONE_HAND;

/**
 * HammerOfKneecapping
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class HammerOfKneecapping extends Equipment {

    public HammerOfKneecapping() {
        super(600, ONE_HAND, false);
    }

    @Override
    public boolean usableBy(Player player) {
        return player.is(Dwarf.class);
    }

    @Override
    public int bonus(Player player) {
        return 4;
    }
}
