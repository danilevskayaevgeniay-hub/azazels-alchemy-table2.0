package ru.azazel.alchemytable.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import ru.azazel.alchemytable.AzazelSAlchemyTable;

public final class ModItems {

    public static final Item MAGIC_WAND = Registry.register(
            BuiltInRegistries.ITEM,
            AzazelSAlchemyTable.id("magic_wand"),
            new Item(new Item.Properties().stacksTo(1))
    );

    public static final Item LIGHT_MAGIC_WAND = Registry.register(
            BuiltInRegistries.ITEM,
            AzazelSAlchemyTable.id("light_magic_wand"),
            new ChargedMagicWandItem(new Item.Properties().stacksTo(1))
    );
    public static final Item FLAME_MAGIC_WAND = Registry.register(
            BuiltInRegistries.ITEM,
            AzazelSAlchemyTable.id("flame_magic_wand"),
            new ChargedMagicWandItem(new Item.Properties().stacksTo(1))
    );
    public static final Item SEA_MAGIC_WAND = Registry.register(
            BuiltInRegistries.ITEM,
            AzazelSAlchemyTable.id("sea_magic_wand"),
            new ChargedMagicWandItem(new Item.Properties().stacksTo(1))
    );
    public static final Item REDSTONE_MAGIC_WAND = Registry.register(
            BuiltInRegistries.ITEM,
            AzazelSAlchemyTable.id("redstone_magic_wand"),
            new ChargedMagicWandItem(new Item.Properties().stacksTo(1))
    );
    public static final Item SEEKING_MAGIC_WAND = Registry.register(
            BuiltInRegistries.ITEM,
            AzazelSAlchemyTable.id("seeking_magic_wand"),
            new ChargedMagicWandItem(new Item.Properties().stacksTo(1))
    );
    public static final Item WIND_MAGIC_WAND = Registry.register(
            BuiltInRegistries.ITEM,
            AzazelSAlchemyTable.id("wind_magic_wand"),
            new ChargedMagicWandItem(new Item.Properties().stacksTo(1))
    );

    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(
                CreativeModeTabs.TOOLS_AND_UTILITIES
        ).register(entries -> {
            entries.accept(MAGIC_WAND);
            entries.accept(LIGHT_MAGIC_WAND);
            entries.accept(FLAME_MAGIC_WAND);
            entries.accept(SEA_MAGIC_WAND);
            entries.accept(WIND_MAGIC_WAND);
            entries.accept(SEEKING_MAGIC_WAND);
            entries.accept(REDSTONE_MAGIC_WAND);
        });
    }

    private ModItems() {
    }
}
