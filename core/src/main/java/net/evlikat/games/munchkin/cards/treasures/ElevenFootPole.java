package net.evlikat.games.munchkin.cards.treasures;

import net.evlikat.games.munchkin.Equipment;
import net.evlikat.games.munchkin.SlotType;
import net.evlikat.games.munchkin.player.Player;

/**
 * ElevenFootPole
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class ElevenFootPole extends Equipment {

    public ElevenFootPole() {
        super(200, SlotType.TWO_HANDS, false);
    }

    @Override
    public int bonus(Player player) {
        return 1;
    }
}
