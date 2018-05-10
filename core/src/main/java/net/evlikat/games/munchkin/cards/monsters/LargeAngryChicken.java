package net.evlikat.games.munchkin.cards.monsters;

import net.evlikat.games.munchkin.Equipment;
import net.evlikat.games.munchkin.Monster;
import net.evlikat.games.munchkin.player.Player;

/**
 * LargeAngryChicken
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class LargeAngryChicken extends Monster {

    public LargeAngryChicken() {
        super(2, 1);
    }

    @Override
    public int levels(Player player) {
        if (player.allEquipment().anyMatch(Equipment::isFire)) {
            return super.levels(player) + 1;
        }
        return super.levels(player);
    }

    @Override
    public void badStuff(Player player) {
        player.loseLevels(1);
    }
}
