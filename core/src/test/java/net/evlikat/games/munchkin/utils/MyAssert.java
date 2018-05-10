package net.evlikat.games.munchkin.utils;

import net.evlikat.games.munchkin.Card;
import net.evlikat.games.munchkin.cards.CardSet;

/**
 * MyAssert
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class MyAssert {

    public static <C extends Card> CardSetAssert<C> assertThat(CardSet<C> actual) {
        return new CardSetAssert<>(actual);
    }
}
