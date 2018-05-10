package net.evlikat.games.munchkin;

/**
 * Level
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Level {

    private int value = 1;

    public int get() {
        return value;
    }

    public void set(int value, int max) {
        this.value = Math.min(Math.max(value, 1), max);
    }

    public void plusEvenLast(int d) {
        this.set(this.value + d, 10);
    }

    public void plus(int d) {
        this.set(this.value + d, 9);
    }

    public void minus(int d) {
        this.set(this.value - d, 10);
    }
}
