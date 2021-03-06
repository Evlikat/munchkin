package net.evlikat.games.munchkin.cards.treasures;

import net.evlikat.games.munchkin.Equipment;
import net.evlikat.games.munchkin.SlotType;
import net.evlikat.games.munchkin.player.Player;

/**
 * HelmOfCourage
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class HelmOfCourage extends Equipment {

    public HelmOfCourage() {
        super(200, SlotType.HEAD, false);
    }

    @Override
    public int bonus(Player player) {
        return 1;
    }
}
