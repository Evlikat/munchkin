package net.evlikat.games.munchkin.cards.monsters;

import net.evlikat.games.munchkin.Monster;
import net.evlikat.games.munchkin.player.Player;

/**
 * Mr. Bones
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class MrBones extends Monster {

    public MrBones() {
        super(2, 1, MonsterType.UNDEAD);
    }

    @Override
    public void beforeRunAway(Player player) {
        player.loseLevels(1);
    }

    @Override
    public void badStuff(Player player) {
        player.loseLevels(2);
    }
}
