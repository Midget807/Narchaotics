package net.midget807.narchaotics.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.fluid.EthanolFluid;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {
    public static final FlowableFluid ETHANOL = registerFluid("ethanol", new EthanolFluid.Still());
    public static final FlowableFluid ETHANOL_FLOWING = registerFluid("ethanol_flowing", new EthanolFluid.Flowing());

    private static <T extends Fluid> T registerFluid(String name, T fluid) {
        return Registry.register(Registries.FLUID, NarchaoticsMain.id(name), fluid);
    }

    public static void registerModFluids() {
        NarchaoticsMain.LOGGER.info("Registering Mod Fluids");
    }

    @Environment(EnvType.CLIENT)
    public static void registerFluidRenders() {
        FluidRenderHandlerRegistry.INSTANCE.register(ETHANOL, ETHANOL_FLOWING, registerWaterTexturedFluid(0xF7F0CA));
    }
    @Environment(EnvType.CLIENT)
    public static SimpleFluidRenderHandler registerWaterTexturedFluid(int color) {
        return new SimpleFluidRenderHandler(
                Identifier.ofVanilla("block/water_still"),
                Identifier.ofVanilla("block/water_flow"),
                color
        );
    }
}
