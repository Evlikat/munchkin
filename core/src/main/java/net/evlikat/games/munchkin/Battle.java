package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.player.Player;
import net.evlikat.games.munchkin.utils.GameCycle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Battle
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Battle {

    private final Game game;

    private ModifiableMonsterSlot<Monster, BattleMonsterModifierCard> monsterInBattle = new ModifiableMonsterSlot<>(BattleMonsterModifierCard.class, Slot.empty(Monster.class));
    // todo:
    private List<ModifiableMonsterSlot<Monster, BattleMonsterModifierCard>> additionalMonsters = new ArrayList<>();

    private Player player;
    // todo:
    private Player helper;
    private List<Player> otherPlayers;

    public Battle(Game game, Player player, Monster monster, List<Player> otherPlayers) {
        this.game = game;
        this.player = player;
        this.otherPlayers = otherPlayers;

        monster.moveTo(this.monsterInBattle.slot());
    }

    public void playOnTargetMonster(Monster monster, BattleMonsterModifierCard modifier) {
        if (monsterInBattle.slot().contains(monster)) {
            modifier.moveTo(monsterInBattle);
        }
        additionalMonsters.stream()
            .filter(s -> s.slot().contains(monster))
            .findFirst()
            .ifPresent(modifier::moveTo);
    }

    public void addMonster(Monster newMonster) {
        Slot<Monster> slot = Slot.empty(Monster.class);
        additionalMonsters.add(new ModifiableMonsterSlot<>(BattleMonsterModifierCard.class, slot));
        newMonster.moveTo(slot);
    }

    public void removeMonster(Monster monster, CardZone outside) {
        if (monsterInBattle.slot().contains(monster)
            || additionalMonsters.stream().anyMatch(s -> s.slot().contains(monster))) {
            monster.moveTo(outside);
        }
    }

    public Stream<Monster> allMonsters() {
        return Stream.concat(
            monsterInBattle.slot().stream(),
            additionalMonsters.stream().map(ModifiableMonsterSlot::slot).flatMap(Slot::stream));
    }

    private Stream<ModifiableMonsterSlot<Monster, BattleMonsterModifierCard>> allMonstersInBattle() {
        return Stream.concat(Stream.of(monsterInBattle), additionalMonsters.stream());
    }

    public boolean play() {
        GameCycle cycle = new GameCycle(
            Stream.<BooleanSupplier>concat(
                Stream.of(() -> player.battlePlay(this)),
                otherPlayers.stream().map(p -> () -> p.otherBattlePlay(this))
            ).collect(toList()));
        cycle.run();
        int sumEffectiveLevel = allMonstersInBattle().mapToInt(m -> m.effectiveLevelAgainst(player)).sum();
        if (player.wins(sumEffectiveLevel, helper)) {
            // levels
            int levelsForFight = allMonstersInBattle().mapToInt(m -> m.levels(player)).sum();
            player.gainLevelsEvenLast(levelsForFight);
            // treasures
            int treasuresForFight = allMonstersInBattle().mapToInt(m -> m.treasures(player)).sum();
            for (int i = 0; i < treasuresForFight; i++) {
                game.getTreasures().top().moveTo(player.hand());
            }
        } else {
            player.beforeRunAwayPlay(this);
            allMonsters().forEach(m -> {
                m.beforeRunAway(player);
                if (helper != null) {
                    m.beforeRunAway(helper);
                }
            });
            allMonsters().forEach(m -> {
                handleRunAway(player, m);
                if (helper != null) {
                    handleRunAway(helper, m);
                }
            });
        }
        // todo: handle when battle resolves with no fight
        return false;
    }

    private void handleRunAway(final Player runner, final Monster monsterToRunAway) {
        while (true) {
            int runAwayThrow = player.dice().thrown() + runner.runAwayModifier();
            int runAwayLevel = monsterToRunAway.runAwayLevel(runner);

            if (runAwayThrow >= runAwayLevel) {
                runner.afterSuccessfulRunAwayPlay(this);
                if (otherPlayerBreaksSuccessfulRunAway()) {
                    continue;
                }
                // run away successful
                return;
            } else {
                boolean retryAfterFailedRunAwayPlay = runner.canRetryAfterFailedRunAwayPlay(this);
                if (retryAfterFailedRunAwayPlay) {
                    continue;
                }
                // run away failed
                monsterToRunAway.badStuff(runner, game);
                return;
            }
        }
    }

    private boolean otherPlayerBreaksSuccessfulRunAway() {
        for (Player otherPlayer : otherPlayers) {
            boolean broken = otherPlayer.otherAfterSuccessfulRunAwayPlay(this);
            if (broken) {
                return true;
            }
        }
        return false;
    }
}
