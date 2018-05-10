package net.evlikat.games.munchkin.cards.treasures;

import net.evlikat.games.munchkin.Equipment;
import net.evlikat.games.munchkin.SlotType;
import net.evlikat.games.munchkin.player.Player;

/**
 * SpikyKnees
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class SpikyKnees extends Equipment {

    public SpikyKnees() {
        super(200, SlotType.OTHER, false);
    }

    @Override
    public int bonus(Player player) {
        return 1;
    }
}
