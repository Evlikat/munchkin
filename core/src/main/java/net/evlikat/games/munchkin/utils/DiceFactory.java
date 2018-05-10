package net.evlikat.games.munchkin.utils;

/**
 * DiceFactory
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class DiceFactory {

    public Dice dice() {
        return new Dice(6);
    }
}
