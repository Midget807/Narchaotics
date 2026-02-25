package net.midget807.narchaotics;

import net.fabricmc.api.ModInitializer;

import net.midget807.narchaotics.registry.ModBlockEntities;
import net.midget807.narchaotics.registry.ModBlocks;
import net.midget807.narchaotics.registry.ModCauldronBehaviors;
import net.midget807.narchaotics.registry.ModColorProviderRegistry;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.midget807.narchaotics.registry.ModEffects;
import net.midget807.narchaotics.registry.ModFluids;
import net.midget807.narchaotics.registry.ModItemGroups;
import net.midget807.narchaotics.registry.ModItems;
import net.midget807.narchaotics.registry.ModRecipes;
import net.midget807.narchaotics.registry.ModScreenHandlers;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NarchaoticsMain implements ModInitializer {
	public static final String MOD_ID = "narchaotics";
    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("jesse we need to cook");

        ModCustomCauldronBehaviours.registerModCustomBehaviours();
        ModBlocks.registerModBlocks();
        ModItems.registerModItems();
        ModItemGroups.registerModItemGroups();
        ModBlockEntities.registerModBlockEntities();
        ModFluids.registerModFluids();
        ModCauldronBehaviors.registerModCauldronBehaviors();
        ModScreenHandlers.registerModScreenHandlers();
        ModColorProviderRegistry.registerBlockColors();
        ModColorProviderRegistry.registerItemColors();
        ModRecipes.registerModRecipes();
        ModEffects.registerModEffects();
	}
}