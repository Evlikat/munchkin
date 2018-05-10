package net.evlikat.games.munchkin.utils;

/**
 * CheaterDice
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class CheaterDice extends Dice {

    private final int always;

    public CheaterDice(int always) {
        super(6);
        this.always = always;
    }

    @Override
    public int thrown() {
        return always;
    }
}
