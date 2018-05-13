package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.cards.CardSet;
import net.evlikat.games.munchkin.player.Player;

import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * ModifiablePlayer
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class ModifiablePlayer<M extends BattleModifierCard> extends CardSet<M> {

    private final Player player;

    public ModifiablePlayer(Class<M> acceptable, Player player) {
        super(acceptable);
        this.player = player;
    }

    public Optional<Player> get() {
        return ofNullable(player);
    }

    public boolean wins(int levelToBeat, ModifiablePlayer<M> helper) {
        return player.wins(levelToBeat, helper.get().orElse(null));
    }
}
