package net.evlikat.games.munchkin.player;

import net.evlikat.games.munchkin.Equipment;
import net.evlikat.games.munchkin.Slot;
import net.evlikat.games.munchkin.WeaponSlots;
import net.evlikat.games.munchkin.cards.Item;
import net.evlikat.games.munchkin.cards.CardSet;
import net.evlikat.games.munchkin.player.exceptions.WrongEquipmentException;

import java.util.stream.Stream;

/**
 * Inventory
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Inventory {

    private WeaponSlots hands = WeaponSlots.empty();
    private Slot<Equipment> body = Slot.empty(Equipment.class);
    private Slot<Equipment> head = Slot.empty(Equipment.class);
    private Slot<Equipment> boots = Slot.empty(Equipment.class);

    private final CardSet<Equipment> otherEquipment = new CardSet<>(Equipment.class);
    private final CardSet<Item> items = new CardSet<>(Item.class);

    private int maxBigEquipment = 1;

    public Stream<Equipment> allEquipment() {
        return Stream.of(
            hands.stream(),
            body.stream(),
            head.stream(),
            boots.stream(),
            otherEquipment.stream())
            .flatMap(s -> s);
    }

    public void setMaxBigEquipment(int maxBigEquipment) {
        this.maxBigEquipment = maxBigEquipment;
    }

    public Stream<Item> allItems() {
        return Stream.concat(allEquipment(), items.stream());
    }

    public <E extends Equipment> E equip(E equipment) {
        switch (equipment.getSlotType()) {
            case BODY:
                equip(body, equipment);
                return equipment;
            case BOOTS:
                equip(boots, equipment);
                return equipment;
            case HEAD:
                equip(head, equipment);
                return equipment;
            case ONE_HAND:
                hands.oneHand(equipment, items);
                return equipment;
            case TWO_HANDS:
                hands.twoHands(equipment, items);
                return equipment;
            case OTHER:
                equipment.moveTo(otherEquipment);
                return equipment;
            default:
                throw new WrongEquipmentException("Unrecognized otherEquipment");
        }
    }

    private void equip(Slot<Equipment> slot, Equipment newEquipment) {
        if (newEquipment.isBig()) {
            long bigEquipped = allEquipment()
                .filter(Equipment::isBig)
                .count() - slot.opt().filter(Equipment::isBig).map(e -> 1).orElse(0);
            if (bigEquipped >= maxBigEquipment) {
                throw new WrongEquipmentException("Cannot wear more big treasures");
            }
        }
        slot.ifPresent(b -> {
            b.moveTo(items);
        });
        newEquipment.moveTo(slot);
    }
}
