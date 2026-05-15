package net.marz.badgemod.items;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.marz.badgemod.MarzsGymBadgeMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {

    public static final Item STEEL_BADGE = registerItem( "steel_badge", new Item(new Item.Settings()));
    public static final Item ICE_BADGE = registerItem( "ice_badge", new Item(new Item.Settings()));
    public static final Item POISON_BADGE = registerItem( "ice_badge", new Item(new Item.Settings()));
    public static final Item GHOST_BADGE = registerItem( "ghost_badge", new Item(new Item.Settings()));

    private static Item registerItem(String name,Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MarzsGymBadgeMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MarzsGymBadgeMod.LOGGER.info("Registering Mod Items for" + MarzsGymBadgeMod.MOD_ID);

//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
//            entries.add(STEEL_BADGE);
//        });
//
//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
//            entries.add(ICE_BADGE);
//        });
    }
}
