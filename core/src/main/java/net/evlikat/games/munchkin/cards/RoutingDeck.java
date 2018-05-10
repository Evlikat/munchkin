package net.evlikat.games.munchkin.cards;

import net.evlikat.games.munchkin.Card;
import net.evlikat.games.munchkin.CardZone;
import net.evlikat.games.munchkin.Door;
import net.evlikat.games.munchkin.Treasure;

/**
 * RoutingDeck
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class RoutingDeck<T extends Deck<? extends Treasure>, D extends Deck<? extends Door>> extends CardZone {

    private final T treasures;
    private final D doors;

    public RoutingDeck(T treasures, D doors) {
        this.treasures = treasures;
        this.doors = doors;
    }

    public T getTreasures() {
        return treasures;
    }

    public D getDoors() {
        return doors;
    }

    @Override
    public boolean canEnter(Class<? extends Card> cardClass) {
        return true;
    }

    @Override
    public void enter(Card card) {
        if (card instanceof Treasure) {
            treasures.enter(card);
        } else if (card instanceof Door) {
            doors.enter(card);
        }
    }

    @Override
    public void leave(Card card) {
        if (card instanceof Treasure) {
            treasures.leave(card);
        } else if (card instanceof Door) {
            doors.leave(card);
        }
    }
}
