package net.evlikat.games.munchkin.cards.curses;

import net.evlikat.games.munchkin.Curse;
import net.evlikat.games.munchkin.Game;
import net.evlikat.games.munchkin.player.Complex;
import net.evlikat.games.munchkin.player.Player;
import net.evlikat.games.munchkin.player.PlayerClass;
import net.evlikat.games.munchkin.player.cls.NoClass;

import java.util.Arrays;
import java.util.List;

/**
 * LoseYourClass
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class LoseYourClass extends Curse {

    @Override
    public void apply(Player player, Game game) {
        if (player.hasClass(NoClass.class)) {
            player.loseLevels(1);
            return;
        }

        List<PlayerClass> playerClasses = player.getPlayerClasses();
        if (playerClasses.size() == 1) {
            player.changeClass(new Complex<>(new NoClass()));
        } else {
            PlayerClass cls = player.askChooseCard(Arrays.asList(playerClasses.get(0), playerClasses.get(1)));
            if (playerClasses.get(0).equals(cls)) {
                player.changeClass(new Complex<>(playerClasses.get(1)));
            } else {
                player.changeClass(new Complex<>(playerClasses.get(0)));
            }
        }
    }
}
