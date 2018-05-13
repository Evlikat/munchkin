package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.cards.Item;
import net.evlikat.games.munchkin.player.Player;

/**
 * BattleCard
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public abstract class BattleCard extends Item {

    public BattleCard(int price) {
        super(price);
    }

    public abstract void apply(Player user, Battle battle);
}
