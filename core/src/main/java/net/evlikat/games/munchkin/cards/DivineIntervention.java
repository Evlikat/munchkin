package net.evlikat.games.munchkin.cards;

import net.evlikat.games.munchkin.Door;
import net.evlikat.games.munchkin.Game;
import net.evlikat.games.munchkin.player.cls.Cleric;

/**
 * DivineIntervention
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class DivineIntervention extends Door {

    @Override
    public void onPickUp(Game game) {
        game.getPlayers()
            .stream()
            .filter(p -> p.hasClass(Cleric.class))
            .forEach(p -> p.gainLevelsEvenLast(1));
    }
}
