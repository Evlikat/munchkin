package net.evlikat.games.munchkin.cards.abilities;

import net.evlikat.games.munchkin.BattleMonsterModifierCard;

/**
 * Ancient
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Ancient extends BattleMonsterModifierCard {

    @Override
    public int value() {
        return 10;
    }

    @Override
    public int treasures() {
        return 2;
    }
}
