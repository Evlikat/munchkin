package net.evlikat.games.munchkin.utils;

import java.security.SecureRandom;

/**
 * Dice
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Dice {

    private static final SecureRandom RND = new SecureRandom();

    private final int max;

    public Dice(int max) {
        this.max = max;
    }

    public int thrown() {
        return RND.nextInt(max - 1) + 1;
    }
}
