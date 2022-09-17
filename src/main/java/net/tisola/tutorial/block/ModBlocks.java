package net.tisola.tutorial.block;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tisola.tutorial.Tutorial;
import net.tisola.tutorial.block.custom.Mossy;
import net.tisola.tutorial.block.custom.Mushi;
import net.tisola.tutorial.block.entity.MushiEntity;
import net.tisola.tutorial.item.ModItemGroups;


public class ModBlocks {
    public static final Block MUSHI = registerBlock("mushi",
            new Mushi(FabricBlockSettings.of(Material.PLANT, MapColor.DARK_AQUA)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS)),
            ModItemGroups.BIOLUM_GROUP);

    public static final Block MOSSY = registerBlock("mossy_block",
            new Mossy(FabricBlockSettings.of(Material.PLANT, MapColor.DARK_AQUA)
                    .sounds(BlockSoundGroup.GRASS)),
            ModItemGroups.BIOLUM_GROUP);

    public static final BlockEntityType<MushiEntity> MUSHI_BLOCK_ENTITY = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier("tutorial", "mushi"),
            FabricBlockEntityTypeBuilder.create(MushiEntity::new, MUSHI).build()
    );


    // INITIALIZER
    private static void registerBlockItem(String name, Block block, ItemGroup group) {
        Registry.register(Registry.ITEM, new Identifier(Tutorial.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }


    private static void registerBlockItem(String name, Block block, ItemGroup group, String tooltipKey) {
        Registry.register(Registry.ITEM, new Identifier(Tutorial.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group))
//            {
//                @Override
//                public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
//                    tooltip.add(new MyText(tooltipKey));
//                }
//            }
        );
    }

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(Tutorial.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        Tutorial.LOGGER.info("Registering ModBlocks for " + Tutorial.MOD_ID);
    }

    private static Block registerBlock(String name, Block block, ItemGroup group, String tooltipKey) {
        registerBlockItem(name, block, group, tooltipKey);
        return Registry.register(Registry.BLOCK, new Identifier(Tutorial.MOD_ID, name), block);
    }

    public static void InitClientSide() {
        BlockRenderLayerMap.INSTANCE.putBlock(MUSHI, RenderLayer.getCutout());
    }


}