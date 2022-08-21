package net.tisola.tutorial.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItem {


    public static final ItemGroup MODDED_GROUP = FabricItemGroupBuilder.create(
                    new Identifier("tutorial", "other"))
            .icon(() -> new ItemStack(Items.BOWL)).build();

    // Not to be used it is only another way to initialize an item group
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("tutorial", "general"),
            () -> new ItemStack(Blocks.COBBLESTONE));

    public static final Item RUBY = new RubyItem(new FabricItemSettings().group(MODDED_GROUP).maxCount(16));


    public static void registerModItems() {
        Registry.register(Registry.ITEM, new Identifier("tutorial", "ruby"), RUBY);
        FuelRegistry.INSTANCE.add(RUBY, 300);
    }
}
