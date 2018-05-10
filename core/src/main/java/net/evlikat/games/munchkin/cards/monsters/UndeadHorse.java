package net.evlikat.games.munchkin.cards.monsters;

import net.evlikat.games.munchkin.Monster;
import net.evlikat.games.munchkin.player.race.Dwarf;
import net.evlikat.games.munchkin.player.Player;

/**
 * UndeadHorse
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class UndeadHorse extends Monster {

    public UndeadHorse() {
        super(4, 2, MonsterType.UNDEAD);
    }

    @Override
    public int effectiveLevelAgainst(Player player) {
        return super.effectiveLevelAgainst(player) + (player.is(Dwarf.class) ? 5 : 0);
    }

    @Override
    public void badStuff(Player player) {
        player.loseLevels(2);
    }
}
