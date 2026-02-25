package net.midget807.narchaotics.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.fluid.AlkalineEphedraSolutionFluid;
import net.midget807.narchaotics.fluid.AmmoniaFluid;
import net.midget807.narchaotics.fluid.AmmoniaSludgeFluid;
import net.midget807.narchaotics.fluid.AmmoniaSolutionFluid;
import net.midget807.narchaotics.fluid.BrineFluid;
import net.midget807.narchaotics.fluid.CleanAshSolutionFluid;
import net.midget807.narchaotics.fluid.CleanEphedraSolutionFluid;
import net.midget807.narchaotics.fluid.CleanIodineSolutionFluid;
import net.midget807.narchaotics.fluid.ConcentratedEphedraSolutionFluid;
import net.midget807.narchaotics.fluid.ConcentratedVolcanicWaterFluid;
import net.midget807.narchaotics.fluid.CrystalisedSaltSolutionFluid;
import net.midget807.narchaotics.fluid.DirtyAshSolutionFluid;
import net.midget807.narchaotics.fluid.DirtyEphedraSolutionFluid;
import net.midget807.narchaotics.fluid.DirtyIodineSolutionFluid;
import net.midget807.narchaotics.fluid.DirtySolutionFluid;
import net.midget807.narchaotics.fluid.EphedraSludgeFluid;
import net.midget807.narchaotics.fluid.EphedrineSolutionFluid;
import net.midget807.narchaotics.fluid.EthanolFluid;
import net.midget807.narchaotics.fluid.HydrazineFluid;
import net.midget807.narchaotics.fluid.HydrazineSolutionFluid;
import net.midget807.narchaotics.fluid.HydrogenPeroxideFluid;
import net.midget807.narchaotics.fluid.HydroiodicAcidFluid;
import net.midget807.narchaotics.fluid.IodineFluid;
import net.midget807.narchaotics.fluid.MethamphetamineAcidSolutionFluid;
import net.midget807.narchaotics.fluid.MethamphetamineFluid;
import net.midget807.narchaotics.fluid.MethamphetamineSolutionFluid;
import net.midget807.narchaotics.fluid.NetherrackSolutionFluid;
import net.midget807.narchaotics.fluid.PseudoephedrineFluid;
import net.midget807.narchaotics.fluid.RedPhosphorusFluid;
import net.midget807.narchaotics.fluid.RedPhosphorusSolutionFluid;
import net.midget807.narchaotics.fluid.SaltSolutionFluid;
import net.midget807.narchaotics.fluid.SaltWaterFluid;
import net.midget807.narchaotics.fluid.SodiumCarbonateFluid;
import net.midget807.narchaotics.fluid.SodiumCarbonateSolutionFluid;
import net.midget807.narchaotics.fluid.SulphuricAcidFluid;
import net.midget807.narchaotics.fluid.SulphuricAcidSolutionFluid;
import net.midget807.narchaotics.fluid.VolcanicWaterFluid;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {
    public static final FlowableFluid DIRTY_SOLUTION = registerFluid("dirty_solution", new DirtySolutionFluid.Still());
    public static final FlowableFluid DIRTY_SOLUTION_FLOWING = registerFluid("dirty_solution_flowing", new DirtySolutionFluid.Flowing());


    public static final FlowableFluid ETHANOL = registerFluid("ethanol", new EthanolFluid.Still());
    public static final FlowableFluid ETHANOL_FLOWING = registerFluid("ethanol_flowing", new EthanolFluid.Flowing());
    public static final FlowableFluid SALT_WATER = registerFluid("salt_water", new SaltWaterFluid.Still());
    public static final FlowableFluid SALT_WATER_FLOWING = registerFluid("salt_water_flowing", new SaltWaterFluid.Flowing());
    public static final FlowableFluid VOLCANIC_WATER = registerFluid("volcanic_water", new VolcanicWaterFluid.Still());
    public static final FlowableFluid VOLCANIC_WATER_FLOWING = registerFluid("volcanic_water_flowing", new VolcanicWaterFluid.Flowing());

    public static final FlowableFluid NETHERRACK_SOLUTION = registerFluid("netherrack_solution", new NetherrackSolutionFluid.Still());
    public static final FlowableFluid NETHERRACK_SOLUTION_FLOWING = registerFluid("netherrack_solution_flowing", new NetherrackSolutionFluid.Flowing());
    public static final FlowableFluid RED_PHOSPHORUS_SOLUTION = registerFluid("red_phosphorus_solution", new RedPhosphorusSolutionFluid.Still());
    public static final FlowableFluid RED_PHOSPHORUS_SOLUTION_FLOWING = registerFluid("red_phosphorus_solution_flowing", new RedPhosphorusSolutionFluid.Flowing());
    public static final FlowableFluid RED_PHOSPHORUS = registerFluid("red_phosphorus", new RedPhosphorusFluid.Still());
    public static final FlowableFluid RED_PHOSPHORUS_FLOWING = registerFluid("red_phosphorus_flowing", new RedPhosphorusFluid.Flowing());

    public static final FlowableFluid DIRTY_ASH_SOLUTION = registerFluid("dirty_ash_solution_water", new DirtyAshSolutionFluid.Still());
    public static final FlowableFluid DIRTY_ASH_SOLUTION_FLOWING = registerFluid("dirty_ash_solution_flowing", new DirtyAshSolutionFluid.Flowing());
    public static final FlowableFluid CLEAN_ASH_SOLUTION = registerFluid("clean_ash_solution", new CleanAshSolutionFluid.Still());
    public static final FlowableFluid CLEAN_ASH_SOLUTION_FLOWING = registerFluid("clean_ash_solution_flowing", new CleanAshSolutionFluid.Flowing());
    public static final FlowableFluid DIRTY_IODINE_SOLUTION = registerFluid("dirty_iodine_solution_water", new DirtyIodineSolutionFluid.Still());
    public static final FlowableFluid DIRTY_IODINE_SOLUTION_FLOWING = registerFluid("dirty_iodine_solution_flowing", new DirtyIodineSolutionFluid.Flowing());
    public static final FlowableFluid CLEAN_IODINE_SOLUTION = registerFluid("clean_iodine_solution", new CleanIodineSolutionFluid.Still());
    public static final FlowableFluid CLEAN_IODINE_SOLUTION_FLOWING = registerFluid("clean_iodine_solution_flowing", new CleanIodineSolutionFluid.Flowing());
    public static final FlowableFluid IODINE = registerFluid("iodine", new IodineFluid.Still());
    public static final FlowableFluid IODINE_FLOWING = registerFluid("iodine_flowing", new IodineFluid.Flowing());

    public static final FlowableFluid SALT_SOLUTION = registerFluid("salt_solution", new SaltSolutionFluid.Still());
    public static final FlowableFluid SALT_SOLUTION_FLOWING = registerFluid("salt_solution_flowing", new SaltSolutionFluid.Flowing());
    public static final FlowableFluid CRYSTALISED_SALT_SOLUTION = registerFluid("crystalised_salt_solution", new CrystalisedSaltSolutionFluid.Still());
    public static final FlowableFluid CRYSTALISED_SALT_SOLUTION_FLOWING = registerFluid("crystalised_salt_solution_flowing", new CrystalisedSaltSolutionFluid.Flowing());
    public static final FlowableFluid SODIUM_CARBONATE_SOLUTION = registerFluid("sodium_carbonate_solution", new SodiumCarbonateSolutionFluid.Still());
    public static final FlowableFluid SODIUM_CARBONATE_SOLUTION_FLOWING = registerFluid("sodium_carbonate_solution_flowing", new SodiumCarbonateSolutionFluid.Flowing());
    public static final FlowableFluid SODIUM_CARBONATE = registerFluid("sodium_carbonate", new SodiumCarbonateFluid.Still());
    public static final FlowableFluid SODIUM_CARBONATE_FLOWING = registerFluid("sodium_carbonate_flowing", new SodiumCarbonateFluid.Flowing());

    public static final FlowableFluid AMMONIA_SLUDGE = registerFluid("ammonia_sludge", new AmmoniaSludgeFluid.Still());
    public static final FlowableFluid AMMONIA_SLUDGE_FLOWING = registerFluid("ammonia_sludge_flowing", new AmmoniaSludgeFluid.Flowing());
    public static final FlowableFluid AMMONIA_SOLUTION = registerFluid("ammonia_solution", new AmmoniaSolutionFluid.Still());
    public static final FlowableFluid AMMONIA_SOLUTION_FLOWING = registerFluid("ammonia_solution_flowing", new AmmoniaSolutionFluid.Flowing());
    public static final FlowableFluid AMMONIA = registerFluid("ammonia", new AmmoniaFluid.Still());
    public static final FlowableFluid AMMONIA_FLOWING = registerFluid("ammonia_flowing", new AmmoniaFluid.Flowing());

    public static final FlowableFluid HYDROGEN_PEROXIDE = registerFluid("hydrogen_peroxide", new HydrogenPeroxideFluid.Still());
    public static final FlowableFluid HYDROGEN_PEROXIDE_FLOWING = registerFluid("hydrogen_peroxide_flowing", new HydrogenPeroxideFluid.Flowing());
    public static final FlowableFluid BRINE = registerFluid("brine", new BrineFluid.Still());
    public static final FlowableFluid BRINE_FLOWING = registerFluid("brine_flowing", new BrineFluid.Flowing());
    public static final FlowableFluid CONCENTRATED_VOLCANIC_WATER = registerFluid("concentrated_volcanic_water", new ConcentratedVolcanicWaterFluid.Still());
    public static final FlowableFluid CONCENTRATED_VOLCANIC_WATER_FLOWING = registerFluid("concentrated_volcanic_water_flowing", new ConcentratedVolcanicWaterFluid.Flowing());

    public static final FlowableFluid SULPHURIC_ACID_SOLUTION = registerFluid("sulphuric_acid_solution", new SulphuricAcidSolutionFluid.Still());
    public static final FlowableFluid SULPHURIC_ACID_SOLUTION_FLOWING = registerFluid("sulphuric_acid_solution_flowing", new SulphuricAcidSolutionFluid.Flowing());
    public static final FlowableFluid SULPHURIC_ACID = registerFluid("sulphuric_acid", new SulphuricAcidFluid.Still());
    public static final FlowableFluid SULPHURIC_ACID_FLOWING = registerFluid("sulphuric_acid_flowing", new SulphuricAcidFluid.Flowing());

    public static final FlowableFluid HYDRAZINE_SOLUTION = registerFluid("hydrazine_solution", new HydrazineSolutionFluid.Still());
    public static final FlowableFluid HYDRAZINE_SOLUTION_FLOWING = registerFluid("hydrazine_solution_flowing", new HydrazineSolutionFluid.Flowing());
    public static final FlowableFluid HYDRAZINE = registerFluid("hydrazine", new HydrazineFluid.Still());
    public static final FlowableFluid HYDRAZINE_FLOWING = registerFluid("hydrazine_flowing", new HydrazineFluid.Flowing());
    public static final FlowableFluid HYDROIODIC_ACID = registerFluid("hydroiodic_acid", new HydroiodicAcidFluid.Still());
    public static final FlowableFluid HYDROIODIC_ACID_FLOWING = registerFluid("hydroiodic_acid_flowing", new HydroiodicAcidFluid.Flowing());

    public static final FlowableFluid EPHEDRA_SLUDGE = registerFluid("ephedra_sludge", new EphedraSludgeFluid.Still());
    public static final FlowableFluid EPHEDRA_SLUDGE_FLOWING = registerFluid("ephedra_sludge_flowing", new EphedraSludgeFluid.Flowing());
    public static final FlowableFluid DIRTY_EPHEDRA_SOLUTION = registerFluid("dirty_ephedra_solution", new DirtyEphedraSolutionFluid.Still());
    public static final FlowableFluid DIRTY_EPHEDRA_SOLUTION_FLOWING = registerFluid("dirty_ephedra_solution_flowing", new DirtyEphedraSolutionFluid.Flowing());
    public static final FlowableFluid CLEAN_EPHEDRA_SOLUTION = registerFluid("clean_ephedra_solution", new CleanEphedraSolutionFluid.Still());
    public static final FlowableFluid CLEAN_EPHEDRA_SOLUTION_FLOWING = registerFluid("clean_ephedra_solution_flowing", new CleanEphedraSolutionFluid.Flowing());
    public static final FlowableFluid CONCENTRATED_EPHEDRA_SOLUTION = registerFluid("concentrated_ephedra_solution", new ConcentratedEphedraSolutionFluid.Still());
    public static final FlowableFluid CONCENTRATED_EPHEDRA_SOLUTION_FLOWING = registerFluid("concentrated_ephedra_solution_flowing", new ConcentratedEphedraSolutionFluid.Flowing());
    public static final FlowableFluid ALKALINE_EPHEDRA_SOLUTION = registerFluid("alkaline_ephedra_solution", new AlkalineEphedraSolutionFluid.Still());
    public static final FlowableFluid ALKALINE_EPHEDRA_SOLUTION_FLOWING = registerFluid("alkaline_ephedra_solution_flowing", new AlkalineEphedraSolutionFluid.Flowing());
    public static final FlowableFluid EPHEDRINE_SOLUTION = registerFluid("ephedrine_solution", new EphedrineSolutionFluid.Still());
    public static final FlowableFluid EPHEDRINE_SOLUTION_FLOWING = registerFluid("ephedrine_solution_flowing", new EphedrineSolutionFluid.Flowing());
    public static final FlowableFluid PSEUDOEPHEDRINE = registerFluid("pseudoephedrine", new PseudoephedrineFluid.Still());
    public static final FlowableFluid PSEUDOEPHEDRINE_FLOWING = registerFluid("pseudoephedrine_flowing", new PseudoephedrineFluid.Flowing());

    public static final FlowableFluid METHAMPHETAMINE_ACID_SOLUTION = registerFluid("methamphetamine_acid_solution", new MethamphetamineAcidSolutionFluid.Still());
    public static final FlowableFluid METHAMPHETAMINE_ACID_SOLUTION_FLOWING = registerFluid("methamphetamine_acid_solution_flowing", new MethamphetamineAcidSolutionFluid.Flowing());
    public static final FlowableFluid METHAMPHETAMINE_SOLUTION = registerFluid("methamphetamine_solution", new MethamphetamineSolutionFluid.Still());
    public static final FlowableFluid METHAMPHETAMINE_SOLUTION_FLOWING = registerFluid("methamphetamine_solution_flowing", new MethamphetamineSolutionFluid.Flowing());
    public static final FlowableFluid METHAMPHETAMINE = registerFluid("methamphetamine", new MethamphetamineFluid.Still());
    public static final FlowableFluid METHAMPHETAMINE_FLOWING = registerFluid("methamphetamine_flowing", new MethamphetamineFluid.Flowing());


    private static <T extends Fluid> T registerFluid(String name, T fluid) {
        return Registry.register(Registries.FLUID, NarchaoticsMain.id(name), fluid);
    }

    public static void registerModFluids() {
        NarchaoticsMain.LOGGER.info("Registering Mod Fluids");
    }

    @Environment(EnvType.CLIENT)
    public static void registerFluidRenders() {
        FluidRenderHandlerRegistry.INSTANCE.register(DIRTY_SOLUTION, DIRTY_SOLUTION_FLOWING, registerWaterTexturedFluid(0x332319));
        FluidRenderHandlerRegistry.INSTANCE.register(ETHANOL, ETHANOL_FLOWING, registerWaterTexturedFluid(0xF7F0CA));
        FluidRenderHandlerRegistry.INSTANCE.register(SALT_WATER, SALT_WATER_FLOWING, registerWaterTexturedFluid(0x090E47));
        FluidRenderHandlerRegistry.INSTANCE.register(VOLCANIC_WATER, VOLCANIC_WATER_FLOWING, registerWaterTexturedFluid(0x4b93f4));

        FluidRenderHandlerRegistry.INSTANCE.register(NETHERRACK_SOLUTION, NETHERRACK_SOLUTION_FLOWING, registerWaterTexturedFluid(0x812626));
        FluidRenderHandlerRegistry.INSTANCE.register(RED_PHOSPHORUS_SOLUTION, RED_PHOSPHORUS_SOLUTION_FLOWING, registerWaterTexturedFluid(0xc72929));
        FluidRenderHandlerRegistry.INSTANCE.register(RED_PHOSPHORUS, RED_PHOSPHORUS_FLOWING, registerWaterTexturedFluid(0xec1e1e));

        FluidRenderHandlerRegistry.INSTANCE.register(DIRTY_ASH_SOLUTION, DIRTY_ASH_SOLUTION_FLOWING, registerWaterTexturedFluid(0x292d29));
        FluidRenderHandlerRegistry.INSTANCE.register(CLEAN_ASH_SOLUTION, CLEAN_ASH_SOLUTION_FLOWING, registerWaterTexturedFluid(0x647062));
        FluidRenderHandlerRegistry.INSTANCE.register(DIRTY_IODINE_SOLUTION, DIRTY_IODINE_SOLUTION_FLOWING, registerWaterTexturedFluid(0x271f33));
        FluidRenderHandlerRegistry.INSTANCE.register(CLEAN_IODINE_SOLUTION, CLEAN_IODINE_SOLUTION_FLOWING, registerWaterTexturedFluid(0x8660bd));
        FluidRenderHandlerRegistry.INSTANCE.register(IODINE, IODINE_FLOWING, registerWaterTexturedFluid(0x8644e3));

        FluidRenderHandlerRegistry.INSTANCE.register(SALT_SOLUTION, SALT_SOLUTION_FLOWING, registerWaterTexturedFluid(0xffe1ba));
        FluidRenderHandlerRegistry.INSTANCE.register(CRYSTALISED_SALT_SOLUTION, CRYSTALISED_SALT_SOLUTION_FLOWING, registerWaterTexturedFluid(0xf7cd93));
        FluidRenderHandlerRegistry.INSTANCE.register(SODIUM_CARBONATE_SOLUTION, SODIUM_CARBONATE_SOLUTION_FLOWING, registerWaterTexturedFluid(0xd9ab6d));
        FluidRenderHandlerRegistry.INSTANCE.register(SODIUM_CARBONATE, SODIUM_CARBONATE_FLOWING, registerWaterTexturedFluid(0xe89627));

        FluidRenderHandlerRegistry.INSTANCE.register(AMMONIA_SLUDGE, AMMONIA_SLUDGE_FLOWING, registerWaterTexturedFluid(0x202f1e));
        FluidRenderHandlerRegistry.INSTANCE.register(AMMONIA_SOLUTION, AMMONIA_SOLUTION_FLOWING, registerWaterTexturedFluid(0x48a737));
        FluidRenderHandlerRegistry.INSTANCE.register(AMMONIA, AMMONIA_FLOWING, registerWaterTexturedFluid(0x2fcc14));

        FluidRenderHandlerRegistry.INSTANCE.register(HYDROGEN_PEROXIDE, HYDROGEN_PEROXIDE_FLOWING, registerWaterTexturedFluid(0x4429df));
        FluidRenderHandlerRegistry.INSTANCE.register(BRINE, BRINE_FLOWING, registerWaterTexturedFluid(0x778028));
        FluidRenderHandlerRegistry.INSTANCE.register(CONCENTRATED_VOLCANIC_WATER, CONCENTRATED_VOLCANIC_WATER_FLOWING, registerWaterTexturedFluid(0x1776f6));

        FluidRenderHandlerRegistry.INSTANCE.register(SULPHURIC_ACID_SOLUTION, SULPHURIC_ACID_SOLUTION_FLOWING, registerWaterTexturedFluid(0xf2a247));
        FluidRenderHandlerRegistry.INSTANCE.register(SULPHURIC_ACID, SULPHURIC_ACID_FLOWING, registerWaterTexturedFluid(0xff8700));

        FluidRenderHandlerRegistry.INSTANCE.register(HYDRAZINE_SOLUTION, HYDRAZINE_SOLUTION_FLOWING, registerWaterTexturedFluid(0x9126c5));
        FluidRenderHandlerRegistry.INSTANCE.register(HYDRAZINE, HYDRAZINE_FLOWING, registerWaterTexturedFluid(0x9e52c3));
        FluidRenderHandlerRegistry.INSTANCE.register(HYDROIODIC_ACID, HYDROIODIC_ACID_FLOWING, registerWaterTexturedFluid(0xac00ff));

        FluidRenderHandlerRegistry.INSTANCE.register(EPHEDRA_SLUDGE, EPHEDRA_SLUDGE_FLOWING, registerWaterTexturedFluid(0x473e1a));
        FluidRenderHandlerRegistry.INSTANCE.register(DIRTY_EPHEDRA_SOLUTION, DIRTY_EPHEDRA_SOLUTION_FLOWING, registerWaterTexturedFluid(0x655d3c));
        FluidRenderHandlerRegistry.INSTANCE.register(CLEAN_EPHEDRA_SOLUTION, CLEAN_EPHEDRA_SOLUTION_FLOWING, registerWaterTexturedFluid(0x655d3c));
        FluidRenderHandlerRegistry.INSTANCE.register(CONCENTRATED_EPHEDRA_SOLUTION, CONCENTRATED_EPHEDRA_SOLUTION_FLOWING, registerWaterTexturedFluid(0xbb8f24));
        FluidRenderHandlerRegistry.INSTANCE.register(ALKALINE_EPHEDRA_SOLUTION, ALKALINE_EPHEDRA_SOLUTION_FLOWING, registerWaterTexturedFluid(0xe7be5d));
        FluidRenderHandlerRegistry.INSTANCE.register(EPHEDRINE_SOLUTION, EPHEDRINE_SOLUTION_FLOWING, registerWaterTexturedFluid(0xffc335));
        FluidRenderHandlerRegistry.INSTANCE.register(PSEUDOEPHEDRINE, PSEUDOEPHEDRINE_FLOWING, registerWaterTexturedFluid(0xffbc1c));

        FluidRenderHandlerRegistry.INSTANCE.register(METHAMPHETAMINE_ACID_SOLUTION, METHAMPHETAMINE_ACID_SOLUTION_FLOWING, registerWaterTexturedFluid(0x539aa9));
        FluidRenderHandlerRegistry.INSTANCE.register(METHAMPHETAMINE_SOLUTION, METHAMPHETAMINE_SOLUTION_FLOWING, registerWaterTexturedFluid(0x39bad6));
        FluidRenderHandlerRegistry.INSTANCE.register(METHAMPHETAMINE, METHAMPHETAMINE_FLOWING, registerWaterTexturedFluid(0x03bde5));
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
