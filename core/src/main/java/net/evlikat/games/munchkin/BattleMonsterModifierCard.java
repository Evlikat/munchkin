package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.player.Player;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * BattleMonsterModifierCard
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public abstract class BattleMonsterModifierCard extends BattleAbility implements MonsterModifier {

    @Override
    public void play(Player player, Battle battle) {
        List<Monster> monsters = battle.allMonsters().collect(toList());
        Monster monster;
        if (monsters.size() == 1) {
            monster = monsters.get(0);
        } else {
            monster = player.askChooseCard(monsters);
        }
        battle.playOnTargetMonster(monster, this);
    }
}
