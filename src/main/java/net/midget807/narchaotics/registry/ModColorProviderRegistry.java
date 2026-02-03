package net.midget807.narchaotics.registry;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

public class ModColorProviderRegistry {
    public static void registerBlockColors() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xF7F0CA, ModBlocks.ETHANOL_CAULDRON);
    }
}
