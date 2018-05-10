package net.evlikat.games.munchkin.utils;

import net.evlikat.games.munchkin.Card;
import net.evlikat.games.munchkin.cards.CardSet;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.internal.Iterables;

import static java.util.stream.Collectors.toSet;

/**
 * CardSetAssert
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class CardSetAssert<C extends Card> extends AbstractObjectAssert<CardSetAssert<C>, CardSet<C>> {

    protected Iterables iterables = Iterables.instance();

    public CardSetAssert(CardSet<C> actual) {
        super(actual, CardSetAssert.class);
    }

    public CardSetAssert<C> hasSize(int expected) {
        iterables.assertHasSize(info, actual, expected);
        return this;
    }

    public CardSetAssert<C> containsOnlyInstancesOf(Class<?>... expectedClasses) {
        iterables.assertContainsOnly(info, actual.stream().map(Object::getClass).collect(toSet()), expectedClasses);
        return this;
    }
}
