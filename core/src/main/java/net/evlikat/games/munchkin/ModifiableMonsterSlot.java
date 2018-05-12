package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.cards.CardSet;
import net.evlikat.games.munchkin.player.Player;

/**
 * ModifiableMonsterSlot
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class ModifiableMonsterSlot<C extends Monster, M extends BattleMonsterModifierCard> extends CardSet<M> {

    private final Slot<C> slot;

    public ModifiableMonsterSlot(Class<M> acceptable, Slot<C> slot) {
        super(acceptable);
        this.slot = slot;
    }

    public Slot<C> slot() {
        return slot;
    }

    public int effectiveLevelAgainst(Player player) {
        return slot.opt().map(m -> m.effectiveLevelAgainst(player)).orElse(0)
            + cards.stream().mapToInt(MonsterModifier::value).sum();
    }

    public int levels(Player player) {
        return Math.max(1, slot.opt().map(m -> m.levels(player)).orElse(0)
            + cards.stream().mapToInt(MonsterModifier::levels).sum());
    }

    public int treasures(Player player) {
        return Math.max(0, slot.opt().map(m -> m.treasures(player)).orElse(0)
            + cards.stream().mapToInt(MonsterModifier::treasures).sum());
    }
}
