package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.player.Player;

import java.util.stream.Collectors;

/**
 * BattleModifierCard
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public abstract class BattleModifierCard extends BattleCard implements MonsterModifier {

    private final int value;

    public BattleModifierCard(int price, int value) {
        super(price);
        this.value = value;
    }

    public final void apply(Player user, Battle battle) {
        boolean onMonster = user.ask("Do you want to apply it on monster? (Otherwise you will apply it on players");
        if (onMonster) {
            Monster monster = user.askChooseCard(battle.allMonsters().collect(Collectors.toList()));
            battle.playOnTargetMonster(monster, this);
        } else {
            Player targetPlayer = user.askChoosePlayer(battle.allPlayers());
            battle.playOnTargetPlayer(targetPlayer, this);
        }
    }

    public int value() {
        return value;
    }
}
