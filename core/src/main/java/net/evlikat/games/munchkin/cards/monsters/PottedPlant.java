package net.evlikat.games.munchkin.cards.monsters;

import net.evlikat.games.munchkin.Monster;
import net.evlikat.games.munchkin.player.race.Elf;
import net.evlikat.games.munchkin.player.Player;

/**
 * PottedPlant
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class PottedPlant extends Monster {

    public PottedPlant() {
        super(1, 1);
    }

    @Override
    public int treasures(Player player) {
        return player.is(Elf.class)
            ? super.treasures(player) + 1
            : super.treasures(player);
    }
}
