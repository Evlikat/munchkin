package net.evlikat.games.munchkin.cards;

import net.evlikat.games.munchkin.Card;

import java.util.function.Predicate;

/**
 * DiscardPile
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class DiscardPile<C extends Card> extends Deck<C> {

    public DiscardPile(Class<C> acceptable) {
        super(acceptable);
    }

    public C last() {
        return last(s -> true);
    }

    public C last(Predicate<C> predicate) {
        for (int i = cards.size() - 1; i >= 0; i--) {
            C c = cards.get(i);
            if (predicate.test(c)) {
                return c;
            }
        }
        return null;
    }

    public void put(C card) {
        cards.add(card);
    }
}
