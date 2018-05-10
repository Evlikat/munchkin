package net.evlikat.games.munchkin.cards.monsters;

import net.evlikat.games.munchkin.Monster;
import net.evlikat.games.munchkin.player.race.Halfling;
import net.evlikat.games.munchkin.player.Player;

/**
 * StonedGolem
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class StonedGolem extends Monster {

    public StonedGolem() {
        super(14, 4);
    }

    @Override
    public boolean wantToFight(Player player) {
        return player.is(Halfling.class) || player.wantToFight();
    }

    @Override
    public void badStuff(Player player) {
        player.die();
    }
}
