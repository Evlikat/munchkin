package net.evlikat.games.munchkin.utils;

import java.util.ArrayList;
import java.util.Collections;
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
    private int startIndex = 0;

    public GameCycle(List<BooleanSupplier> actors) {
        this.actors = new ArrayList<>(actors);
    }

    public void run() {
        Iterator<BooleanSupplier> it = actors.iterator();
        while (it.hasNext()) {
            startIndex++;
            if (it.next().getAsBoolean()) {
                Collections.rotate(actors, -startIndex);
                it = actors.iterator();
            }
        }
    }
}
