package net.marz.badgemod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.marz.badgemod.MarzsGymBadgeMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.marz.badgemod.block.custom.Badge;

public class ModBlocks {
    public static final Block ice_badge = registerBlock("ice_badge",
            new Badge(AbstractBlock.Settings.create().strength(0.8f, 50f).sounds(BlockSoundGroup.AMETHYST_BLOCK).nonOpaque()));

    public static final Block steel_badge = registerBlock("steel_badge",
            new Badge(AbstractBlock.Settings.create().strength(0.8f, 50f).sounds(BlockSoundGroup.COPPER).nonOpaque()));

    public static final Block poison_badge = registerBlock("poison_badge",
            new Badge(AbstractBlock.Settings.create().strength(0.8f, 50f).sounds(BlockSoundGroup.GLASS).nonOpaque()));

    public static final Block ghost_badge = registerBlock("ghost_badge",
            new Badge(AbstractBlock.Settings.create().strength(0.8f, 50f).sounds(BlockSoundGroup.GLASS).nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MarzsGymBadgeMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MarzsGymBadgeMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings().requires()));
    }

    public static void registerModBlocks() {
        MarzsGymBadgeMod.LOGGER.info("Registering Mod Blocks for " + MarzsGymBadgeMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.ice_badge);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.steel_badge);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.poison_badge);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.ghost_badge);
        });
    }
}
