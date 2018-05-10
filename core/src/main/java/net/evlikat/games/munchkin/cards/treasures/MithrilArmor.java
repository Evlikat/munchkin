package net.evlikat.games.munchkin.cards.treasures;

import net.evlikat.games.munchkin.Equipment;
import net.evlikat.games.munchkin.SlotType;
import net.evlikat.games.munchkin.player.Player;
import net.evlikat.games.munchkin.player.cls.Wizard;

/**
 * MithrilArmor
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class MithrilArmor extends Equipment {

    public MithrilArmor() {
        super(600, SlotType.BODY, true);
    }

    @Override
    public boolean usableBy(Player player) {
        return !player.hasClass(Wizard.class);
    }

    @Override
    public int bonus(Player player) {
        return 3;
    }
}
