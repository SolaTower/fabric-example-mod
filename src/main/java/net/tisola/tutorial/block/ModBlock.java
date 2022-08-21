package net.tisola.tutorial.block;

import net.tisola.tutorial.block.entity.RubyBlockEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModBlock {
    public static final Block RUBY = new RubyBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());
    public static final BlockEntityType<RubyBlockEntity> RUBY_BLOCK_ENTITY = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier("tutorial", "ruby_block_entity"),
            FabricBlockEntityTypeBuilder.create(RubyBlockEntity::new, RUBY).build()
    );

    public static void registerModBlocks() {
        Registry.register(
                Registry.BLOCK,
                new Identifier("tutorial", "ruby_block"),
                RUBY
        );
        Registry.register(
                Registry.ITEM,
                new Identifier("tutorial", "ruby_block"),
                new BlockItem(RUBY, new FabricItemSettings().group(ItemGroup.MISC))
        );
    }



}