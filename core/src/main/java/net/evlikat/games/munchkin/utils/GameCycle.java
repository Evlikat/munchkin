package net.evlikat.games.munchkin.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * GameCycle
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class GameCycle {

    private final List<BooleanSupplier> actors;

    public GameCycle(List<BooleanSupplier> actors) {
        this.actors = new ArrayList<>(actors);
    }

    public void run() {
        Iterator<BooleanSupplier> it = actors.iterator();
        int falseInRow = 0;
        while (it.hasNext() || (it = actors.iterator()).hasNext()) {
            if (it.next().getAsBoolean()) {
                falseInRow = 0;
                continue;
            }
            if (++falseInRow >= actors.size()) {
                return;
            }
        }
    }
}
