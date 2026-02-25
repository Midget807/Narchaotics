package net.midget807.narchaotics;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.midget807.narchaotics.registry.ModBlocks;
import net.midget807.narchaotics.registry.ModFluids;
import net.midget807.narchaotics.registry.ModScreenHandlers;
import net.midget807.narchaotics.screen.ChemistryWorkbenchScreen;
import net.midget807.narchaotics.screen.DissolveWorkbenchScreen;
import net.midget807.narchaotics.screen.DistillationWorkbenchScreen;
import net.midget807.narchaotics.screen.EvaporateWorkbenchScreen;
import net.midget807.narchaotics.screen.FilterWorkbenchScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class NarchaoticsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModFluids.registerFluidRenders();
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.DIRTY_SOLUTION, ModFluids.DIRTY_SOLUTION_FLOWING,
                ModFluids.ETHANOL, ModFluids.ETHANOL_FLOWING,
                ModFluids.SALT_WATER, ModFluids.SALT_WATER_FLOWING,
                ModFluids.VOLCANIC_WATER, ModFluids.VOLCANIC_WATER_FLOWING,
                ModFluids.NETHERRACK_SOLUTION, ModFluids.NETHERRACK_SOLUTION_FLOWING,
                ModFluids.RED_PHOSPHORUS_SOLUTION, ModFluids.RED_PHOSPHORUS_SOLUTION_FLOWING,
                ModFluids.RED_PHOSPHORUS, ModFluids.RED_PHOSPHORUS_FLOWING,
                ModFluids.DIRTY_ASH_SOLUTION, ModFluids.DIRTY_ASH_SOLUTION_FLOWING,
                ModFluids.CLEAN_ASH_SOLUTION, ModFluids.CLEAN_ASH_SOLUTION_FLOWING,
                ModFluids.DIRTY_IODINE_SOLUTION, ModFluids.DIRTY_IODINE_SOLUTION_FLOWING,
                ModFluids.CLEAN_IODINE_SOLUTION, ModFluids.CLEAN_IODINE_SOLUTION_FLOWING,
                ModFluids.IODINE, ModFluids.IODINE_FLOWING,
                ModFluids.SALT_SOLUTION, ModFluids.SALT_SOLUTION_FLOWING,
                ModFluids.CRYSTALISED_SALT_SOLUTION, ModFluids.CRYSTALISED_SALT_SOLUTION_FLOWING,
                ModFluids.SODIUM_CARBONATE_SOLUTION, ModFluids.SODIUM_CARBONATE_SOLUTION_FLOWING,
                ModFluids.SODIUM_CARBONATE, ModFluids.SODIUM_CARBONATE_FLOWING,
                ModFluids.AMMONIA_SLUDGE, ModFluids.AMMONIA_SLUDGE_FLOWING,
                ModFluids.AMMONIA_SOLUTION, ModFluids.AMMONIA_SOLUTION_FLOWING,
                ModFluids.AMMONIA, ModFluids.AMMONIA_FLOWING,
                ModFluids.HYDROGEN_PEROXIDE, ModFluids.HYDROGEN_PEROXIDE_FLOWING,
                ModFluids.BRINE, ModFluids.BRINE_FLOWING,
                ModFluids.CONCENTRATED_VOLCANIC_WATER, ModFluids.CONCENTRATED_VOLCANIC_WATER_FLOWING,
                ModFluids.SULPHURIC_ACID_SOLUTION, ModFluids.SULPHURIC_ACID_SOLUTION_FLOWING,
                ModFluids.SULPHURIC_ACID, ModFluids.SULPHURIC_ACID_FLOWING,
                ModFluids.HYDRAZINE_SOLUTION, ModFluids.HYDRAZINE_SOLUTION_FLOWING,
                ModFluids.HYDRAZINE, ModFluids.HYDRAZINE_FLOWING,
                ModFluids.HYDROIODIC_ACID, ModFluids.HYDROIODIC_ACID_FLOWING,
                ModFluids.EPHEDRA_SLUDGE, ModFluids.EPHEDRA_SLUDGE_FLOWING,
                ModFluids.DIRTY_EPHEDRA_SOLUTION, ModFluids.DIRTY_EPHEDRA_SOLUTION_FLOWING,
                ModFluids.CLEAN_EPHEDRA_SOLUTION, ModFluids.CLEAN_EPHEDRA_SOLUTION_FLOWING,
                ModFluids.CONCENTRATED_EPHEDRA_SOLUTION, ModFluids.CONCENTRATED_EPHEDRA_SOLUTION_FLOWING,
                ModFluids.ALKALINE_EPHEDRA_SOLUTION, ModFluids.ALKALINE_EPHEDRA_SOLUTION_FLOWING,
                ModFluids.EPHEDRINE_SOLUTION, ModFluids.EPHEDRINE_SOLUTION_FLOWING,
                ModFluids.PSEUDOEPHEDRINE, ModFluids.PSEUDOEPHEDRINE_FLOWING,
                ModFluids.METHAMPHETAMINE_ACID_SOLUTION, ModFluids.METHAMPHETAMINE_ACID_SOLUTION_FLOWING,
                ModFluids.METHAMPHETAMINE_SOLUTION, ModFluids.METHAMPHETAMINE_SOLUTION_FLOWING,
                ModFluids.METHAMPHETAMINE, ModFluids.METHAMPHETAMINE_FLOWING
        );


        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ModBlocks.EPHEDRA_CROP,
                ModBlocks.DISTILLATION_WORKBENCH
        );

        HandledScreens.register(ModScreenHandlers.CHEMISTRY_WORKBENCH, ChemistryWorkbenchScreen::new);
        HandledScreens.register(ModScreenHandlers.DISTILLING_WORKBENCH, DistillationWorkbenchScreen::new);
        HandledScreens.register(ModScreenHandlers.FILTER_WORKBENCH, FilterWorkbenchScreen::new);
        HandledScreens.register(ModScreenHandlers.EVAPORATE_WORKBENCH, EvaporateWorkbenchScreen::new);
        HandledScreens.register(ModScreenHandlers.DISSOLVE_WORKBENCH, DissolveWorkbenchScreen::new);

    }
}
