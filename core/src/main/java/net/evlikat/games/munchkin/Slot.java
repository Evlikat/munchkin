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
public class Slot<C extends Card> extends CardZone {

    private final Class<C> acceptable;

    private C item;

    private Slot(Class<C> acceptable, C item) {
        this.acceptable = acceptable;
        this.item = item;
    }

    public static <C extends Card> Slot<C> of(Class<C> acceptable, C item) {
        return new Slot<C>(acceptable, item);
    }

    public static <C extends Card> Slot<C> empty(Class<C> acceptable) {
        return new Slot<C>(acceptable, null);
    }

    @Override
    public boolean canEnter(Class<? extends Card> cardClass) {
        return acceptable.isAssignableFrom(cardClass);
    }

    public C get() {
        return item;
    }

    public Optional<C> opt() {
        return Optional.ofNullable(item);
    }

    @Override
    public void enter(Card card) {
        item = (C) card;
    }

    @Override
    public void leave(Card card) {
        if (item == card) {
            item = null;
        }
    }

    public void ifPresent(Consumer<C> consumer) {
        if (item != null) {
            consumer.accept(item);
        }
    }

    public void ifPresentOrElse(Consumer<C> consumer, Runnable runnable) {
        if (item != null) {
            consumer.accept(item);
        } else {
            runnable.run();
        }
    }

    public Stream<C> stream() {
        if (item == null) {
            return Stream.empty();
        }
        return Stream.of(item);
    }
}
