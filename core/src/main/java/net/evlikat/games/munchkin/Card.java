package net.evlikat.games.munchkin;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Card
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public abstract class Card implements ICard {

    private static final AtomicInteger SEQ = new AtomicInteger(1);

    private final String id = getClass().getSimpleName() + SEQ.getAndIncrement();
    private CardZone zone;

    public String getId() {
        return id;
    }

    public void moveTo(CardZone newZone) {
        if (!newZone.canEnter(getClass())) {
            throw new RuntimeException("The card " + this + " cannot be placed into " + newZone);
        }
        if (zone != null) {
            zone.leave(this);
        }
        newZone.enter(this);
        zone = newZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
