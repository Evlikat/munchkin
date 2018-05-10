package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.cards.Item;
import net.evlikat.games.munchkin.player.Player;

/**
 * Equipment
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public abstract class Equipment extends Item {

    private final SlotType slotType;
    private final boolean big;

    public Equipment(int price, SlotType slotType, boolean big) {
        super(price);
        this.slotType = slotType;
        this.big = big;
    }

    public boolean usableBy(Player player) {
        return true;
    }

    public abstract int bonus(Player player);

    public boolean isFire() {
        return false;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public boolean isBig() {
        return big;
    }
}
