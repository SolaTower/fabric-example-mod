package net.fabricmc.example.items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModdedItems {



    public static final ItemGroup MODDED_GROUP = FabricItemGroupBuilder.create(
                    new Identifier("tutorial", "other"))
            .icon(() -> new ItemStack(Items.BOWL)).build();

    // Not to be used it is only another way to initialize an item group
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("tutorial", "general"),
            () -> new ItemStack(Blocks.COBBLESTONE));

    public static final Item RUBY = new CustomItem(new FabricItemSettings().group(MODDED_GROUP).maxCount(16));
    public static final Block RUBY_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));


    public static void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("tutorial", "ruby_block"), RUBY_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("tutorial", "ruby_block_item"),
                new BlockItem(RUBY_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("tutorial", "ruby"), RUBY);

        FuelRegistry.INSTANCE.add(RUBY, 300);
    }
}
