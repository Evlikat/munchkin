package net.evlikat.games.munchkin.cards.monsters;

import net.evlikat.games.munchkin.Monster;
import net.evlikat.games.munchkin.player.race.Elf;
import net.evlikat.games.munchkin.player.Player;

/**
 * FaceSucker
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class FaceSucker extends Monster {

    public FaceSucker() {
        super(8, 2);
    }

    @Override
    public int effectiveLevelAgainst(Player player) {
        return player.is(Elf.class) ? super.effectiveLevelAgainst(player) + 6 : super.effectiveLevelAgainst(player);
    }

    @Override
    public void badStuff(Player player) {
        player.loseHead();
        player.loseLevels(2);
    }
}
