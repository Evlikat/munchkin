package net.evlikat.games.munchkin;

/**
 * CardZone
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public abstract class CardZone {

    public abstract boolean canEnter(Class<? extends Card> cardClass);

    public abstract void enter(Card card);

    public abstract void leave(Card card);
}
