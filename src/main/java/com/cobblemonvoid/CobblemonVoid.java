package com.cobblemonvoid;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class CobblemonVoid implements ModInitializer {
    public static final String MOD_ID = "cobblemon_void";

    public static Item COSMIC_STONE;
    public static Block COSMIC_STONE_ORE;
    public static Block DEEPSLATE_COSMIC_STONE_ORE;

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        COSMIC_STONE = Registry.register(BuiltInRegistries.ITEM, id("cosmic_stone"), new Item(new Item.Properties()));

        COSMIC_STONE_ORE = Registry.register(BuiltInRegistries.BLOCK, id("cosmic_stone_ore"),
                new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
        DEEPSLATE_COSMIC_STONE_ORE = Registry.register(BuiltInRegistries.BLOCK, id("deepslate_cosmic_stone_ore"),
                new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));

        Item oreItem = Registry.register(BuiltInRegistries.ITEM, id("cosmic_stone_ore"),
                new BlockItem(COSMIC_STONE_ORE, new Item.Properties()));
        Item deepOreItem = Registry.register(BuiltInRegistries.ITEM, id("deepslate_cosmic_stone_ore"),
                new BlockItem(DEEPSLATE_COSMIC_STONE_ORE, new Item.Properties()));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(e -> e.accept(COSMIC_STONE));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(e -> {
            e.accept(oreItem);
            e.accept(deepOreItem);
        });

        // Ore generation (rare: 4 attempts per band) in Lush Caves and Dripstone Caves only; the Cosmic biome (common, 8) comes later. Two overlapping height bands, both defined in data/cobblemon_void/worldgen/placed_feature
        for (String band : new String[]{"cosmic_stone_ore_low", "cosmic_stone_ore_high"}) {
            ResourceKey<PlacedFeature> key = ResourceKey.create(Registries.PLACED_FEATURE, id(band));
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.LUSH_CAVES, Biomes.DRIPSTONE_CAVES), GenerationStep.Decoration.UNDERGROUND_ORES, key);
        }
    }
}
