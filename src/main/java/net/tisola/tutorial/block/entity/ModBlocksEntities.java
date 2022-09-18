package net.tisola.tutorial.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tisola.tutorial.Tutorial;
import static net.tisola.tutorial.block.ModBlocks.MUSHI;


public class ModBlocksEntities {

    public static final BlockEntityType<MushiEntity> MUSHI_BLOCK_ENTITY = registerBlockEntity(
            "mushi", MUSHI
    );


    private static BlockEntityType<MushiEntity> registerBlockEntity(String name, Block block) {
        return Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Tutorial.MOD_ID, name),
                FabricBlockEntityTypeBuilder.create(MushiEntity::new, block).build()
        );
//        registerBlockItem(name, block, group);
//        return Registry.register(Registry.BLOCK, new Identifier(Tutorial.MOD_ID, name), block);
    }
}