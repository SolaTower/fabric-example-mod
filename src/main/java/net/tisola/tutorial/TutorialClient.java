package net.tisola.tutorial;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.tisola.tutorial.block.ModBlocks;
import net.tisola.tutorial.particle.ModParticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.tisola.tutorial.particle.ModParticle.GREEN_FLAME;

public class TutorialClient implements ClientModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");
    public static final String MOD_ID = "tutorial";

    @Override
    public void onInitializeClient() {
        ModBlocks.InitClientSide();
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(new Identifier("modid", "green_flame"));
        }));

        /* Registers our particle client-side.
         * First argument is our particle's instance, created previously on ExampleMod.
         * Second argument is the particle's factory. The factory controls how the particle behaves.
         * In this example, we'll use FlameParticle's Factory.*/
        ParticleFactoryRegistry.getInstance().register(GREEN_FLAME, FlameParticle.Factory::new);

    }
}
