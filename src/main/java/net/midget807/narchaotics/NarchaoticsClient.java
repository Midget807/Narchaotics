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
                ModFluids.ETHANOL_STILL,
                ModFluids.ETHANOL_FLOWING
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
