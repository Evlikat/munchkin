package net.evlikat.games.munchkin.player;

import net.evlikat.games.munchkin.cards.treasures.BootsOfButtKicking;
import net.evlikat.games.munchkin.cards.treasures.BucklerOfSwashing;
import net.evlikat.games.munchkin.cards.treasures.ChainsawOfBloodyDismemberment;
import net.evlikat.games.munchkin.cards.treasures.ElevenFootPole;
import net.evlikat.games.munchkin.cards.treasures.HelmOfCourage;
import net.evlikat.games.munchkin.cards.treasures.MithrilArmor;
import net.evlikat.games.munchkin.cards.treasures.SneakyBastardSword;
import net.evlikat.games.munchkin.cards.treasures.SpikyKnees;
import net.evlikat.games.munchkin.player.exceptions.WrongEquipmentException;
import org.junit.Before;
import org.junit.Test;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class InventoryTest {

    private Inventory inventory;

    @Before
    public void setUp() throws Exception {
        inventory = new Inventory();
    }

    @Test
    public void shouldEquipAllItemsTwoHands() {
        ElevenFootPole weapon = inventory.equip(new ElevenFootPole());
        HelmOfCourage helm = inventory.equip(new HelmOfCourage());
        BootsOfButtKicking boots = inventory.equip(new BootsOfButtKicking());
        MithrilArmor armor = inventory.equip(new MithrilArmor());

        assertThat(inventory.allEquipment().collect(toList()))
            .containsOnly(weapon, helm, boots, armor);
    }

    @Test
    public void shouldEquipOtherEquipment() {
        SpikyKnees knees1 = inventory.equip(new SpikyKnees());
        SpikyKnees knees2 = inventory.equip(new SpikyKnees());
        SpikyKnees knees3 = inventory.equip(new SpikyKnees());

        assertThat(inventory.allEquipment().collect(toList()))
            .containsOnly(knees1, knees2, knees3);
    }

    @Test
    public void shouldEquipAllItemsOneHand() {
        SneakyBastardSword sword = inventory.equip(new SneakyBastardSword());
        BucklerOfSwashing shield = inventory.equip(new BucklerOfSwashing());
        HelmOfCourage helm = inventory.equip(new HelmOfCourage());
        BootsOfButtKicking boots = inventory.equip(new BootsOfButtKicking());
        MithrilArmor armor = inventory.equip(new MithrilArmor());

        assertThat(inventory.allEquipment().collect(toList()))
            .containsOnly(sword, shield, helm, boots, armor);
    }

    @Test
    public void shouldEquipOneHandInsteadTwoHand() {
        ElevenFootPole weapon = inventory.equip(new ElevenFootPole());
        SneakyBastardSword sword = inventory.equip(new SneakyBastardSword());

        assertThat(inventory.allEquipment().collect(toList()))
            .containsOnly(sword);
        assertThat(inventory.allItems().collect(toList()))
            .containsOnly(sword, weapon);
    }

    @Test
    public void shouldEquipTwoHandInsteadTwoOneHands() {
        SneakyBastardSword sword = inventory.equip(new SneakyBastardSword());
        BucklerOfSwashing shield = inventory.equip(new BucklerOfSwashing());
        ElevenFootPole weapon = inventory.equip(new ElevenFootPole());

        assertThat(inventory.allEquipment().collect(toList()))
            .containsOnly(weapon);
        assertThat(inventory.allItems().collect(toList()))
            .containsOnly(sword, shield, weapon);
    }

    @Test
    public void shouldEquipTwoHandInsteadOneHand() {
        SneakyBastardSword sword = inventory.equip(new SneakyBastardSword());
        ElevenFootPole weapon = inventory.equip(new ElevenFootPole());

        assertThat(inventory.allEquipment().collect(toList()))
            .containsOnly(weapon);
        assertThat(inventory.allItems().collect(toList()))
            .containsOnly(sword, weapon);
    }

    @Test
    public void shouldEquipOneHandInsteadTwoOneHands() {
        SneakyBastardSword sword = inventory.equip(new SneakyBastardSword());
        BucklerOfSwashing shield = inventory.equip(new BucklerOfSwashing());
        SneakyBastardSword sword2 = inventory.equip(new SneakyBastardSword());

        assertThat(inventory.allEquipment().collect(toList()))
            .containsOnly(sword, sword2);
        assertThat(inventory.allItems().collect(toList()))
            .containsOnly(sword, shield, sword2);
    }

    @Test(expected = WrongEquipmentException.class)
    public void shouldNotEquipTwoBigItems() {
        inventory.equip(new ChainsawOfBloodyDismemberment());
        inventory.equip(new MithrilArmor());
    }

    @Test
    public void shouldEquipTwoBigItemsWhenAllowed() {
        inventory.setMaxBigEquipment(500);
        ChainsawOfBloodyDismemberment chainsaw = inventory.equip(new ChainsawOfBloodyDismemberment());
        MithrilArmor armor = inventory.equip(new MithrilArmor());

        assertThat(inventory.allEquipment().collect(toList()))
            .containsOnly(chainsaw, armor);
    }
}