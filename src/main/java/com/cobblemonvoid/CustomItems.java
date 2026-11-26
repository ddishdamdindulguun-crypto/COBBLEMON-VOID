package com.cobblemonvoid;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

/** Simple custom items (evolution items, fossils, trophies). Behaviour comes from evolution/fossil data files. */
public final class CustomItems {
    public static final String[] IDS = {
            "champions_sword",
            "water_trophy",
            "ancient_virus",
            "ancient_treasure",
            "alien_dna",
            "cosmic_treat",
            "dragon_scale",
            "car_tire",
            "flame_schematic",
            "wind_schematic",
            "aqua_schematic",
            "talon_fossil",
            "horn_fossil",
            "mind_fossil",
            "thorax_fossil",
            "gear_fossil",
            "skull_fossil",
            "hammer_fossil",
            "amalgamation_fossil",
            "deep_fossil",
            "burrow_fossil",
            "impact_fossil",
            "melody_fossil",
            "meteor_fragment"
    };
    public static final List<Item> ITEMS = new ArrayList<>();

    public static void register() {
        for (String id : IDS) {
            ITEMS.add(Registry.register(BuiltInRegistries.ITEM, CobblemonVoid.id(id), new Item(new Item.Properties())));
        }
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(e -> ITEMS.forEach(e::accept));
    }

    private CustomItems() {}
}
