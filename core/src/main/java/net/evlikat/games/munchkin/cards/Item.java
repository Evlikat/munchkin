package net.evlikat.games.munchkin.cards;

import net.evlikat.games.munchkin.Treasure;

/**
 * Item
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Item extends Treasure {

    private final int price;

    public Item(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
