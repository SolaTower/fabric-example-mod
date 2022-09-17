package net.tisola.tutorial.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.tisola.tutorial.Tutorial;
import net.tisola.tutorial.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup BIOLUM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(Tutorial.MOD_ID, "bio_cave"),
            () -> new ItemStack(ModBlocks.MUSHI)
    );
}
