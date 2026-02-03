package net.midget807.narchaotics.registry;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.screen.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<ChemistryWorkbenchScreenHandler> CHEMISTRY_WORKBENCH =
            Registry.register(Registries.SCREEN_HANDLER, NarchaoticsMain.id("chemistry_workbench"),
                    new ExtendedScreenHandlerType<>(ChemistryWorkbenchScreenHandler::new, BlockPos.PACKET_CODEC)
            );
    public static final ScreenHandlerType<DistillationScreenHandler> DISTILLING_WORKBENCH =
            Registry.register(Registries.SCREEN_HANDLER, NarchaoticsMain.id("distilling_workbench"),
                    new ExtendedScreenHandlerType<>(DistillationScreenHandler::new, BlockPos.PACKET_CODEC)
            );
    public static final ScreenHandlerType<FilterScreenHandler> FILTER_WORKBENCH =
            Registry.register(Registries.SCREEN_HANDLER, NarchaoticsMain.id("filter_workbench"),
                    new ExtendedScreenHandlerType<>(FilterScreenHandler::new, BlockPos.PACKET_CODEC)
            );
    public static final ScreenHandlerType<EvaporateScreenHandler> EVAPORATE_WORKBENCH =
            Registry.register(Registries.SCREEN_HANDLER, NarchaoticsMain.id("evaporate_workbench"),
                    new ExtendedScreenHandlerType<>(EvaporateScreenHandler::new, BlockPos.PACKET_CODEC)
            );
    public static final ScreenHandlerType<DissolveScreenHandler> DISSOLVE_WORKBENCH =
            Registry.register(Registries.SCREEN_HANDLER, NarchaoticsMain.id("dissolve_workbench"),
                    new ExtendedScreenHandlerType<>(DissolveScreenHandler::new, BlockPos.PACKET_CODEC)
            );

    public static void registerModScreenHandlers() {
        NarchaoticsMain.LOGGER.info("Registering Mod Screen Handlers");
    }
}
