package net.evlikat.games.munchkin.cards.curses;

import net.evlikat.games.munchkin.Curse;
import net.evlikat.games.munchkin.Game;
import net.evlikat.games.munchkin.player.Complex;
import net.evlikat.games.munchkin.player.race.Human;
import net.evlikat.games.munchkin.player.Player;
import net.evlikat.games.munchkin.player.PlayerRace;

/**
 * ChangeRace
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class ChangeRace extends Curse {

    @Override
    public void apply(Player player, Game game) {
        if (player.is(Human.class)) {
            return;
        }
        PlayerRace newRace = (PlayerRace) game.getDoorDiscardPile().last(c -> c instanceof PlayerRace);
        if (newRace != null) {
            player.changeRace(new Complex<>(newRace));
        } else {
            player.changeRace(new Complex<>(new Human()));
        }
    }
}
