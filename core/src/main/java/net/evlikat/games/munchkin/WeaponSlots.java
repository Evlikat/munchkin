package net.evlikat.games.munchkin;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * WeaponSlots
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class WeaponSlots {

    // either
    private Slot<Equipment> leftHand = Slot.empty(Equipment.class);
    private Slot<Equipment> rightHand = Slot.empty(Equipment.class);
    // or
    private Slot<Equipment> twoHands = Slot.empty(Equipment.class);

    public static WeaponSlots empty() {
        return new WeaponSlots();
    }

    public Stream<? extends Equipment> stream() {
        return Stream.of(leftHand, rightHand, twoHands).map(Slot::get).filter(Objects::nonNull);
    }

    public void oneHand(Equipment equipment, CardZone outside) {
        twoHands.ifPresentOrElse(
            th -> {
                th.moveTo(outside);
                equipment.moveTo(leftHand);
            },
            () -> rightHand.ifPresentOrElse(
                r -> {
                    r.moveTo(outside);
                    equipment.moveTo(rightHand);
                },
                () -> leftHand.ifPresentOrElse(
                    l -> {
                        equipment.moveTo(rightHand);
                    },
                    () -> equipment.moveTo(leftHand)
                )
            )
        );
    }

    public void twoHands(Equipment equipment, CardZone outside) {
        twoHands.ifPresentOrElse(
            th -> th.moveTo(outside),
            () -> {
                leftHand.ifPresent(l -> l.moveTo(outside));
                rightHand.ifPresent(r -> r.moveTo(outside));
            }
        );
        equipment.moveTo(twoHands);
    }
}
