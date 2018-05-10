package net.evlikat.games.munchkin.cards;

import net.evlikat.games.munchkin.Card;
import net.evlikat.games.munchkin.CardZone;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * CardSet
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class CardSet<C extends Card> extends CardZone implements Iterable<C> {

    protected final Class<C> acceptable;
    protected final ArrayList<C> cards = new ArrayList<>();

    public CardSet(Class<C> acceptable) {
        this.acceptable = acceptable;
    }

    public C get(int index) {
        return cards.get(index);
    }

    public Stream<C> stream() {
        return new ArrayList<>(cards).stream();
    }

    public int size() {
        return cards.size();
    }

    @Override
    public boolean canEnter(Class<? extends Card> cardClass) {
        return acceptable.isAssignableFrom(cardClass);
    }

    @Override
    public void enter(Card card) {
        cards.add(acceptable.cast(card));
    }

    @Override
    public void leave(Card card) {
        // todo: fix suspicious call?
        cards.remove(card);
    }

    @Override
    public String toString() {
        return acceptable.getSimpleName() + "s: " + cards.stream().map(Object::getClass).map(Class::getSimpleName).collect(joining(", "));
    }

    @Override
    public Iterator<C> iterator() {
        return cards.iterator();
    }
}
