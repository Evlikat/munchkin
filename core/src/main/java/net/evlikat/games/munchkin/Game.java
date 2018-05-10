package net.evlikat.games.munchkin;

import net.evlikat.games.munchkin.cards.CardSet;
import net.evlikat.games.munchkin.cards.Deck;
import net.evlikat.games.munchkin.cards.DiscardPile;
import net.evlikat.games.munchkin.cards.RoutingDeck;
import net.evlikat.games.munchkin.exceptions.GameOver;
import net.evlikat.games.munchkin.player.PlayerControl;
import net.evlikat.games.munchkin.player.Sex;
import net.evlikat.games.munchkin.utils.CollectionUtils;
import net.evlikat.games.munchkin.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Game
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Game {

    private final List<Player> players = new ArrayList<>();

    private final Deck<Treasure> treasures = new Deck<>(Treasure.class);
    private final Deck<Door> doors = new Deck<>(Door.class);
    private final RoutingDeck<DiscardPile<Treasure>, DiscardPile<Door>> routingDiscardPile =
        new RoutingDeck<>(new DiscardPile<>(Treasure.class), new DiscardPile<>(Door.class));

    private boolean ended = false;

    public Game withPlayer(Sex sex, PlayerControl control) {
        players.add(new Player(players.size(), sex, control));
        return this;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Game withTreasure(Treasure treasure) {
        treasure.moveTo(treasures);
        return this;
    }

    public Game withTreasures(Treasure... newTreasures) {
        for (Treasure treasure : newTreasures) {
            this.withTreasure(treasure);
        }
        return this;
    }

    public Deck<Treasure> getTreasures() {
        return treasures;
    }

    public Game withDoor(Door door) {
        door.moveTo(doors);
        return this;
    }

    public Game withDoors(Door... newDoors) {
        for (Door door : newDoors) {
            this.withDoor(door);
        }
        return this;
    }

    public CardSet<Door> getDoors() {
        return doors;
    }

    public RoutingDeck<DiscardPile<Treasure>, DiscardPile<Door>> getRoutingDiscardPile() {
        return routingDiscardPile;
    }

    public DiscardPile<Door> getDoorDiscardPile() {
        return routingDiscardPile.getDoors();
    }

    /**
     * Players draw card.
     */
    public void prepare(boolean fast) {
        for (Player player : players) {
            int count = fast ? 4 : 2;
            for (int i = 0; i < count; i++) {
                treasures.top().moveTo(player.hand());
            }
            for (int i = 0; i < count; i++) {
                doors.top().moveTo(player.hand());
            }
        }
    }

    /**
     * Starts an infinite game loop.
     */
    public Player start() {
        try {
            while (true) {
                for (Player currentPlayer : players) {
                    playerTurn(currentPlayer);
                }
            }
        } catch (GameOver gameOver) {
            return gameOver.getWinner();
        }
    }

    public void playerTurn(Player currentPlayer) {
        currentPlayer.freePlay();
        boolean canPhase2 = kickDoor(currentPlayer);
        if (canPhase2) {
            List<Monster> monsters = currentPlayer.hand().stream()
                .filter(c -> c instanceof Monster)
                .map(c -> (Monster) c)
                .collect(Collectors.toList());
            if (!monsters.isEmpty() && currentPlayer.ask("Do you want to look for troubles? (otherwise you must draw a door card)")) {
                Monster monster = monsters.size() > 1 ? currentPlayer.askChooseCard(monsters) : monsters.get(0);
                battle(currentPlayer, monster);
            } else {
                doors.top().moveTo(currentPlayer.hand());
            }
        }
        currentPlayer.freePlay();
        cleanUp(currentPlayer);
    }

    private boolean kickDoor(Player currentPlayer) {
        Door door = doors.top();
        if (door instanceof Monster) {
            return battle(currentPlayer, (Monster) door);
        } else if (door instanceof Curse) {
            ((Curse) door).apply(currentPlayer, this);
            door.moveTo(getDoorDiscardPile());
            return true;
        } else {
            door.moveTo(currentPlayer.hand());
            return true;
        }
    }

    private boolean battle(Player currentPlayer, Monster monster) {
        Battle battle = new Battle(
            this,
            currentPlayer,
            monster,
            otherPlayers(currentPlayer));
        boolean noFight = battle.play();
        battle.allMonsters().forEach(m -> m.moveTo(routingDiscardPile));
        return noFight;
    }

    private List<Player> otherPlayers(Player currentPlayer) {
        ArrayList<Player> other = this.players.stream()
            .filter(p -> !p.equals(currentPlayer))
            .collect(Collectors.toCollection(ArrayList::new));
        Collections.rotate(other, currentPlayer.getIndex());
        return other;
    }

    private void cleanUp(Player currentPlayer) {
        if (!currentPlayer.mustDiscard()) {
            return;
        }
        List<Player> playersOfMinLevel = CollectionUtils.allMaxOf(otherPlayers(currentPlayer).stream(), p -> -p.getLevel());
        if (playersOfMinLevel.get(0).getLevel() >= currentPlayer.getLevel()) {
            do {
                Card card = currentPlayer.askChooseCard(currentPlayer.hand().stream().collect(Collectors.toList()));
                card.moveTo(routingDiscardPile);
            } while (currentPlayer.mustDiscard());
            return;
        }
        do {
            Card card = currentPlayer.askChooseCard(currentPlayer.hand().stream().collect(Collectors.toList()));
            // TODO: minimize the difference between players
            Player player = currentPlayer.askChoosePlayer(playersOfMinLevel);
            card.moveTo(player.hand());
        } while (currentPlayer.mustDiscard());
    }
}
