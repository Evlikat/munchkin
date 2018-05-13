package net.evlikat.games.munchkin;

/**
 * CardZone
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public abstract class CardZone {

    public abstract boolean canEnter(Class<? extends ICard> cardClass);

    public abstract void enter(ICard card);

    public abstract void leave(ICard card);
}
