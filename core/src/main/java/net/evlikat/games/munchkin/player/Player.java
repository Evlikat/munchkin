package net.evlikat.games.munchkin.player;

import net.evlikat.games.munchkin.Battle;
import net.evlikat.games.munchkin.Card;
import net.evlikat.games.munchkin.Equipment;
import net.evlikat.games.munchkin.Level;
import net.evlikat.games.munchkin.cards.CardSet;
import net.evlikat.games.munchkin.cards.Deck;
import net.evlikat.games.munchkin.cards.Item;
import net.evlikat.games.munchkin.exceptions.GameOver;
import net.evlikat.games.munchkin.player.cls.NoClass;
import net.evlikat.games.munchkin.player.exceptions.WrongEquipmentException;
import net.evlikat.games.munchkin.player.race.Human;
import net.evlikat.games.munchkin.utils.Dice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Player
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Player {

    // to resolve ordering
    private final int index;
    private Sex sex;
    private final PlayerControl control;
    private Level level = new Level();

    private Complex<PlayerClass> playerClass = new Complex<>(new NoClass());
    private Complex<PlayerRace> playerRace = new Complex<>(new Human());

    private final Inventory inventory = new Inventory();
    private final CardSet<Card> hand = new Deck<>(Card.class);

    public Player(int index, Sex sex, PlayerControl control) {
        this.index = index;
        this.sex = sex;
        this.control = control;
    }

    public PlayerControl ctrl() {
        return control;
    }

    public int getIndex() {
        return index;
    }

    public int getLevel() {
        return level.get();
    }

    public Sex getSex() {
        return sex;
    }

    public int effectiveLevel() {
        return level.get() + allEquipment().mapToInt(e -> e.bonus(this)).sum();
    }

    public boolean hasBigEquipment() {
        return allEquipment().anyMatch(Equipment::isBig);
    }

    public List<PlayerClass> getPlayerClasses() {
        return playerClass.all();
    }

    public List<PlayerRace> getPlayerRaces() {
        return playerRace.all();
    }

    public <C extends PlayerClass> boolean hasClass(Class<C> cls) {
        return playerClass.has(cls);
    }

    public <R extends PlayerRace> boolean is(Class<R> race) {
        return playerRace.has(race);
    }

    public Stream<? extends Item> allItems() {
        return inventory.allItems();
    }

    public Stream<? extends Equipment> allEquipment() {
        return inventory.allEquipment();
    }

    public boolean wins(int level, Player helper) {
        // todo: handle warrior
        return getLevel()
            + allEquipment().mapToInt(e -> e.bonus(this)).sum()
            + (helper == null ? 0 : helper.getLevel()) > level;
    }

    public <C extends PlayerClass> void changeClass(Complex<C> cls) {
        //todo:
    }

    public <R extends PlayerRace> void changeRace(Complex<R> race) {
        //todo:
    }

    public void die() {
        // todo:
    }

    public void setLevel(int lvl) {

    }

    public boolean mustDiscard() {
        // TODO: handle dwarf
        return hand.size() > 5;
    }

    public Item askChooseItem() {
        return control.askChooseAmong(inventory.allItems().collect(Collectors.toList()));
    }

    public Player askChoosePlayer(List<? extends Player> players) {
        return control.askChoosePlayer(players);
    }

    public <C extends Card> C askChooseCard(List<C> cards) {
        return control.askChooseCard(cards);
    }

    public Item askChooseAmong(List<? extends Item> items) {
        return control.askChooseAmong(items);
    }

    public boolean ask(String message) {
        return control.ask(message);
    }

    public void equip(Equipment equipment) {
        if (!equipment.usableBy(this)) {
            throw new WrongEquipmentException("Your avatar can not wear " + equipment);
        }
        inventory.equip(equipment);
    }

    public void obtain(Item item) {
        // todo:
    }

    public void lose(Item item) {
        // todo:
    }

    public boolean wantToFight() {
        // todo:
        return false;
    }


    public void loseHead() {
        // todo:
    }

    public void gainLevelsEvenLast(int levels) {
        level.plusEvenLast(levels);
        if (getLevel() >= 10) {
            throw new GameOver(this);
        }
    }

    public void gainLevels(int levels) {
        level.plus(levels);
    }

    public void loseLevels(int levels) {
        level.minus(levels);
    }

    public CardSet<Card> hand() {
        return hand;
    }

    public <C extends Card> Optional<C> getCard(Class<C> target) {
        return hand.stream().filter(target::isInstance).map(target::cast).findAny();
    }

    public boolean freePlay() {
        return control.freePlay();
    }

    public boolean battlePlay(Battle battle) {
        return control.battlePlay(battle);
    }

    public boolean otherBattlePlay(Battle battle) {
        return control.otherBattlePlay(battle);
    }

    public boolean beforeRunAwayPlay(Battle battle) {
        return control.beforeRunAwayPlay(battle);
    }

    public boolean afterSuccessfulRunAwayPlay(Battle battle) {
        return control.afterSuccessfulRunAwayPlay(battle);
    }

    public boolean canRetryAfterFailedRunAwayPlay(Battle battle) {
        // todo:
        return false;
    }

    public int runAwayModifier() {
        // todo:
        return 0;
    }

    public boolean otherAfterSuccessfulRunAwayPlay(Battle battle) {
        return false;
    }

    public Dice dice() {
        return control.dice();
    }
}
