package net.midget807.narchaotics.registry;

import net.midget807.narchaotics.NarchaoticsMain;
import net.minecraft.block.cauldron.CauldronBehavior;

public class ModCustomCauldronBehaviours {
    public static final CauldronBehavior.CauldronBehaviorMap ETHANOL_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("ethanol");

    public static void registerModCustomBehaviours() {
        NarchaoticsMain.LOGGER.info("Registering Mod Custom Behaviours");
    }
}
