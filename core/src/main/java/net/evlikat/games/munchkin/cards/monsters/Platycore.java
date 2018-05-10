package net.evlikat.games.munchkin.cards.monsters;

import net.evlikat.games.munchkin.CardZone;
import net.evlikat.games.munchkin.Monster;
import net.evlikat.games.munchkin.player.Player;
import net.evlikat.games.munchkin.player.cls.Wizard;

/**
 * Platycore
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Platycore extends Monster {

    private final CardZone outside;

    public Platycore(CardZone outside) {
        super(6, 2);
        this.outside = outside;
    }

    @Override
    public int effectiveLevelAgainst(Player player) {
        return super.effectiveLevelAgainst(player) + (player.hasClass(Wizard.class) ? 6 : 0);
    }

    @Override
    public void badStuff(Player player) {
        boolean discard = player.ask("Discard all hand? (You will lose 2 levels otherwise)");
        if (discard) {
            player.hand().stream().forEach(c -> moveTo(outside));
        } else {
            player.loseLevels(2);
        }
    }
}
