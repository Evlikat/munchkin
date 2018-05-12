package net.evlikat.games.munchkin;

/**
 * MonsterModifier
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public interface MonsterModifier extends Modifier {

    default int treasures() {
        return 0;
    }

    default int levels() {
        return 0;
    }
}
