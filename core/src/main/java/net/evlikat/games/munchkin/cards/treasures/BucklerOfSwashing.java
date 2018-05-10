package net.evlikat.games.munchkin.cards.treasures;

import net.evlikat.games.munchkin.Equipment;
import net.evlikat.games.munchkin.SlotType;
import net.evlikat.games.munchkin.player.Player;

/**
 * BucklerOfSwashing
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class BucklerOfSwashing extends Equipment {

    public BucklerOfSwashing() {
        super(400, SlotType.ONE_HAND, false);
    }

    @Override
    public int bonus(Player player) {
        return 2;
    }
}
