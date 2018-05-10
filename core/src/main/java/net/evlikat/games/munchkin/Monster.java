package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.cards.monsters.MonsterType;
import net.evlikat.games.munchkin.player.Player;

import java.util.Collections;
import java.util.EnumSet;

/**
 * Monster
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public abstract class Monster extends Door {

    private final int level;
    private final int treasures;
    private final EnumSet<MonsterType> types = EnumSet.noneOf(MonsterType.class);

    public Monster(int level, int treasures, MonsterType... types) {
        this.level = level;
        this.treasures = treasures;
        Collections.addAll(this.types, types);
    }

    public boolean is(MonsterType type) {
        return types.contains(type);
    }

    public EnumSet<MonsterType> getTypes() {
        return types;
    }

    public int levels(Player player) {
        return 1;
    }

    public boolean wantToFight(Player player) {
        return true;
    }

    public void beforeRunAway(Player player) {
        // nothing
    }

    public int runAwayLevel(Player player) {
        return 5;
    }

    public int playerEffectiveLevel(Player player) {
        return player.effectiveLevel();
    }

    public int effectiveLevelAgainst(Player player) {
        return level;
    }

    public int treasures(Player player) {
        return treasures;
    }

    protected void badStuff(Player player) {
        // nothing
    }

    public void badStuff(Player player, Game game) {
        badStuff(player);
    }
}
