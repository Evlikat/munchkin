package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.cards.abilities.Ancient;
import net.evlikat.games.munchkin.cards.monsters.LameGoblin;
import net.evlikat.games.munchkin.player.Player;
import net.evlikat.games.munchkin.player.PlayerControl;
import net.evlikat.games.munchkin.player.Sex;
import net.evlikat.games.munchkin.utils.CheaterDice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BattleTest {

    private Player mikePl;
    private Player lisaPl;

    @Mock
    private PlayerControl mike;
    @Mock
    private PlayerControl lisa;

    @Mock
    private Game game;

    private Battle battle;
    private LameGoblin goblin;

    @Before
    public void setUp() throws Exception {
        mikePl = Mockito.spy(new Player(0, Sex.MALE, mike));
        lisaPl = Mockito.spy(new Player(1, Sex.FEMALE, lisa));
        goblin = new LameGoblin();
        battle = new Battle(game, mikePl, goblin, Collections.singletonList(lisaPl));
    }

    @Test
    public void shouldPlayModifiers() {
        Ancient modifier = new Ancient();
        when(mike.battlePlay(battle))
            .thenAnswer(c -> false)
            .thenAnswer(c -> false);
        when(lisa.otherBattlePlay(battle))
            .thenAnswer(c -> {
                battle.playOnTargetMonster(goblin, modifier);
                return true;
            })
            .thenAnswer(c -> false);
        doReturn(new CheaterDice(6)).when(mike).dice();
        battle.play();
        verify(mikePl).wins(modifier.value() + 1, null);
    }
}