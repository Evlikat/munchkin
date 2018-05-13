package net.evlikat.games.munchkin.cards.abilities;

import net.evlikat.games.munchkin.BattleMonsterModifierCard;

/**
 * Enraged
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Enraged extends BattleMonsterModifierCard {

    @Override
    public int value() {
        return 5;
    }

    @Override
    public int treasures() {
        return 1;
    }
}
