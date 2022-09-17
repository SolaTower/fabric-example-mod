package net.tisola.tutorial.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tisola.tutorial.Tutorial;

public class ModParticle {

    public static final DefaultParticleType GREEN_FLAME = registerParticle(
            "green_flame",
            FabricParticleTypes.simple()
    );


    private static DefaultParticleType registerParticle(String name, DefaultParticleType item) {
        return Registry.register(
                Registry.PARTICLE_TYPE,
                new Identifier(Tutorial.MOD_ID, name),
                item
        );
    }

    public static void registerModParticles() {
        Tutorial.LOGGER.info("Registering Mod Particles for " + Tutorial.MOD_ID);
    }

}
