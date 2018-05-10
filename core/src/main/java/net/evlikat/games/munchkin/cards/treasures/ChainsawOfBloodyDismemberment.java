package net.evlikat.games.munchkin.cards.treasures;

import net.evlikat.games.munchkin.Equipment;
import net.evlikat.games.munchkin.SlotType;
import net.evlikat.games.munchkin.player.Player;

/**
 * ChainsawOfBloodyDismemberment
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class ChainsawOfBloodyDismemberment extends Equipment {

    public ChainsawOfBloodyDismemberment() {
        super(600, SlotType.TWO_HANDS, true);
    }

    public int bonus(Player player) {
        return 3;
    }
}
