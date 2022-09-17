package net.tisola.tutorial.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tisola.tutorial.Tutorial;

import net.tisola.tutorial.block.ModBlocks;


public class ModItem {



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Tutorial.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Tutorial.LOGGER.info("Registering Mod Items for " + Tutorial.MOD_ID);
    }
}