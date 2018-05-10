package net.evlikat.games.munchkin.cards.treasures;

import net.evlikat.games.munchkin.Equipment;
import net.evlikat.games.munchkin.SlotType;
import net.evlikat.games.munchkin.player.Player;

/**
 * BootsOfButtKicking
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class BootsOfButtKicking extends Equipment {

    public BootsOfButtKicking() {
        super(400, SlotType.BOOTS, false);
    }

    @Override
    public int bonus(Player player) {
        return 2;
    }
}
