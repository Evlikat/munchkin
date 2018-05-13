package net.evlikat.games.munchkin;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Slot
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Slot<C extends ICard> extends CardZone {

    private final Class<C> acceptable;

    private C card;

    protected Slot(Class<C> acceptable, C card) {
        this.acceptable = acceptable;
        this.card = card;
    }

    public static <C extends Card> Slot<C> of(Class<C> acceptable, C item) {
        return new Slot<C>(acceptable, item);
    }

    public static <C extends Card> Slot<C> empty(Class<C> acceptable) {
        return new Slot<C>(acceptable, null);
    }

    @Override
    public boolean canEnter(Class<? extends ICard> cardClass) {
        return acceptable.isAssignableFrom(cardClass);
    }

    public boolean contains(C card) {
        return this.card.equals(card);
    }

    public C get() {
        return card;
    }

    public Optional<C> opt() {
        return Optional.ofNullable(card);
    }

    @Override
    public void enter(ICard card) {
        this.card = (C) card;
    }

    @Override
    public void leave(ICard card) {
        if (this.card == card) {
            this.card = null;
        }
    }

    public void ifPresent(Consumer<C> consumer) {
        if (card != null) {
            consumer.accept(card);
        }
    }

    public void ifPresentOrElse(Consumer<C> consumer, Runnable runnable) {
        if (card != null) {
            consumer.accept(card);
        } else {
            runnable.run();
        }
    }

    public Stream<C> stream() {
        if (card == null) {
            return Stream.empty();
        }
        return Stream.of(card);
    }
}
