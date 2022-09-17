package net.tisola.tutorial;

import net.fabricmc.api.ModInitializer;
import net.tisola.tutorial.block.ModBlocks;
import net.tisola.tutorial.particle.ModParticle;
import net.tisola.tutorial.item.ModItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Tutorial implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");
    public static final String MOD_ID = "tutorial";

    @Override
    public void onInitialize() {

        ModParticle.registerModParticles();
        ModItem.registerModItems();
        ModBlocks.registerModBlocks();

//        ModBlockEntities.registerAllBlockentities();
    }
}
