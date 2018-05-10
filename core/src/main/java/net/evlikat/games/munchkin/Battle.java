package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.cards.CardSet;
import net.evlikat.games.munchkin.player.Player;

import java.util.List;
import java.util.stream.Stream;

/**
 * Battle
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Battle {

    private final Game game;

    private Slot<Monster> monsterSlot = Slot.empty(Monster.class);
    // todo:
    private CardSet<Monster> additionalMonsters = new CardSet<>(Monster.class);

    private Player player;
    // todo:
    private Player helper;
    private List<Player> otherPlayers;

    public Battle(Game game, Player player, Monster monster, List<Player> otherPlayers) {
        this.game = game;
        this.player = player;
        this.otherPlayers = otherPlayers;

        monster.moveTo(this.monsterSlot);
    }

    public void addMonster(Monster newMonster) {
        newMonster.moveTo(additionalMonsters);
    }

    public Stream<Monster> allMonsters() {
        return Stream.concat(monsterSlot.stream(), additionalMonsters.stream());
    }

    public boolean play() {
        // todo: update after plays
        player.battlePlay(this);
        for (Player otherPlayer : otherPlayers) {
            otherPlayer.otherBattlePlay(this);
        }
        int sumEffectiveLevel = allMonsters().mapToInt(m -> m.effectiveLevelAgainst(player)).sum();
        if (player.wins(sumEffectiveLevel, helper)) {
            // levels
            int levelsForFight = allMonsters().mapToInt(m -> m.levels(player)).sum();
            player.gainLevelsEvenLast(levelsForFight);
            // treasures
            int treasuresForFight = allMonsters().mapToInt(m -> m.treasures(player)).sum();
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
