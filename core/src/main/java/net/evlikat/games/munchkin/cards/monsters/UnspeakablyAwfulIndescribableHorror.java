package net.evlikat.games.munchkin.cards.monsters;

import net.evlikat.games.munchkin.Monster;
import net.evlikat.games.munchkin.player.Complex;
import net.evlikat.games.munchkin.player.cls.NoClass;
import net.evlikat.games.munchkin.player.Player;
import net.evlikat.games.munchkin.player.PlayerClass;
import net.evlikat.games.munchkin.player.cls.Warrior;
import net.evlikat.games.munchkin.player.cls.Wizard;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UnspeakablyAwfulIndescribableHorror
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class UnspeakablyAwfulIndescribableHorror extends Monster {

    public UnspeakablyAwfulIndescribableHorror() {
        super(14, 4);
    }

    @Override
    public void badStuff(Player player) {
        if (!player.hasClass(Wizard.class)) {
            player.die();
        }
        List<PlayerClass> classesAfter =
            player.getPlayerClasses().stream().filter(c -> !(c instanceof Wizard)).collect(Collectors.toList());
        if (classesAfter.isEmpty()) {
            player.changeClass(new Complex<>(new NoClass()));
        } else {
            player.changeClass(new Complex<>(classesAfter.get(0)));
        }
    }

    @Override
    public int effectiveLevelAgainst(Player player) {
        return super.effectiveLevelAgainst(player) + (player.hasClass(Warrior.class) ? 4 : 0);
    }
}
