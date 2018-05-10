package net.evlikat.games.munchkin.cards;

import net.evlikat.games.munchkin.Card;

import java.util.Collections;

/**
 * Deck
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Deck<C extends Card> extends CardSet<C> {

    public Deck(Class<C> acceptable) {
        super(acceptable);
    }

    public CardSet shuffled() {
        Collections.shuffle(cards);
        return this;
    }

    public C top(){
        int last = cards.size() - 1;
        return cards.get(last);
    }
}
