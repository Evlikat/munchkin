package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.cards.abilities.Intelligent;
import net.evlikat.games.munchkin.cards.monsters.LameGoblin;
import net.evlikat.games.munchkin.cards.monsters.MrBones;
import net.evlikat.games.munchkin.cards.monsters.Pukachu;
import net.evlikat.games.munchkin.cards.treasures.BootsOfButtKicking;
import net.evlikat.games.munchkin.cards.treasures.ChainsawOfBloodyDismemberment;
import net.evlikat.games.munchkin.cards.treasures.MithrilArmor;
import net.evlikat.games.munchkin.cards.treasures.PollymorphPotion;
import net.evlikat.games.munchkin.player.Player;
import net.evlikat.games.munchkin.player.PlayerControl;
import net.evlikat.games.munchkin.player.cls.Thief;
import net.evlikat.games.munchkin.player.race.Elf;
import net.evlikat.games.munchkin.utils.CheaterDice;
import net.evlikat.games.munchkin.utils.MyAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static net.evlikat.games.munchkin.player.Sex.FEMALE;
import static net.evlikat.games.munchkin.player.Sex.MALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    private Game game;

    private Player mikePl;
    private Player lisaPl;
    private Player johnPl;

    @Mock
    private PlayerControl mike;
    @Mock
    private PlayerControl lisa;
    @Mock
    private PlayerControl john;

    @Before
    public void setUp() {
        game = new Game()
            .withPlayer(MALE, mike)
            .withPlayer(FEMALE, lisa)
            .withPlayer(MALE, john).withDoors(
                // for john
                new Elf(),
                new Thief(),
                // for lisa
                new Elf(),
                new Thief(),
                // for mike
                new MrBones(),
                new Thief()
            )
            .withTreasures(
                // for john
                new ChainsawOfBloodyDismemberment(),
                new MithrilArmor(),
                // for lisa
                new ChainsawOfBloodyDismemberment(),
                new MithrilArmor(),
                // for mike
                new ChainsawOfBloodyDismemberment(),
                new MithrilArmor()
            )
        ;
        mikePl = game.getPlayers().get(0);
        lisaPl = game.getPlayers().get(1);
        johnPl = game.getPlayers().get(2);

        game.prepare(false);
    }

    /**
     *
     */
    @Test
    public void testPrepare() {
        assertThat(mikePl.hand().size()).isEqualTo(4);
        assertThat(lisaPl.hand().size()).isEqualTo(4);
        assertThat(johnPl.hand().size()).isEqualTo(4);
        assertThat(game.getTreasures().size()).isEqualTo(0);
        assertThat(game.getDoors().size()).isEqualTo(0);
    }

    /**
     *
     */
    @Test
    public void testBattleWinLameGoblin() {
        game.withTreasure(new PollymorphPotion());
        game.withDoor(new LameGoblin());

        doAnswer(c -> {
            mikePl.getCard(MithrilArmor.class).ifPresent(armor -> {
                mikePl.equip(armor);
            });
            return false;
        }).when(mike).freePlay();
        game.playerTurn(mikePl);
        // then
        assertThat(mikePl.getLevel()).isEqualTo(2);
        assertThat(mikePl.effectiveLevel()).isEqualTo(5);
        MyAssert.assertThat(mikePl.hand())
            .hasSize(4)
            .containsOnlyInstancesOf(
                ChainsawOfBloodyDismemberment.class,
                Thief.class,
                MrBones.class,
                PollymorphPotion.class);

        assertThat(game.getDoorDiscardPile().last()).isInstanceOf(LameGoblin.class);
    }

    /**
     *
     */
    @Test
    public void testBattleLosePukachu() {
        game.withTreasure(new PollymorphPotion());
        game.withDoor(new Pukachu());

        doAnswer(c -> {
            mikePl.getCard(MithrilArmor.class).ifPresent(armor -> {
                mikePl.equip(armor);
            });
            return false;
        }).when(mike).freePlay();
        doReturn(new CheaterDice(1)).when(mike).dice();
        game.playerTurn(mikePl);
        // then
        assertThat(mikePl.getLevel()).isEqualTo(1);
        assertThat(mikePl.effectiveLevel()).isEqualTo(4);
        assertThat(mikePl.hand()).hasSize(0);

        assertThat(game.getDoorDiscardPile().last()).isInstanceOf(Pukachu.class);
    }

    /**
     *
     */
    @Test
    public void testBattleRunAwayPukachu() {
        game.withTreasure(new PollymorphPotion());
        game.withDoor(new Pukachu());

        doAnswer(c -> {
            mikePl.getCard(MithrilArmor.class).ifPresent(armor -> {
                mikePl.equip(armor);
            });
            return false;
        }).when(mike).freePlay();
        doReturn(new CheaterDice(6)).when(mike).dice();
        game.playerTurn(mikePl);
        // then
        assertThat(mikePl.getLevel()).isEqualTo(1);
        assertThat(mikePl.effectiveLevel()).isEqualTo(4);
        MyAssert.assertThat(mikePl.hand()).hasSize(3)
            .containsOnlyInstancesOf(
                ChainsawOfBloodyDismemberment.class,
                Thief.class,
                MrBones.class);

        assertThat(game.getDoorDiscardPile().last()).isInstanceOf(Pukachu.class);
    }

    /**
     *
     */
    @Test
    public void testNoBattle() {
        Intelligent intelligent = new Intelligent();
        game.withDoor(new Pukachu())
            .withDoor(intelligent);

        doReturn(intelligent).when(mike).askChooseCard(any());
        game.playerTurn(mikePl);
        // then
        MyAssert.assertThat(mikePl.hand())
            .hasSize(5)
            .containsOnlyInstancesOf(
                MithrilArmor.class,
                ChainsawOfBloodyDismemberment.class,
                Thief.class,
                MrBones.class,
                Pukachu.class);

        assertThat(game.getDoorDiscardPile().last()).isInstanceOf(Intelligent.class);
    }

    /**
     *
     */
    @Test
    public void testLookingForTroubles() {
        game.withTreasure(new BootsOfButtKicking())
            .withDoor(new Intelligent());

        doAnswer(c -> {
            mikePl.getCard(MithrilArmor.class).ifPresent(armor -> mikePl.equip(armor));
            return false;
        }).when(mike).freePlay();
        doReturn(true).when(mike).ask(any());
        game.playerTurn(mikePl);
        // then
        assertThat(mikePl.getLevel()).isEqualTo(2);
        assertThat(mikePl.effectiveLevel()).isEqualTo(5);
        MyAssert.assertThat(mikePl.hand())
            .hasSize(4)
            .containsOnlyInstancesOf(
                Intelligent.class,
                ChainsawOfBloodyDismemberment.class,
                Thief.class,
                BootsOfButtKicking.class);

        assertThat(game.getDoorDiscardPile().last()).isInstanceOf(MrBones.class);
    }
}