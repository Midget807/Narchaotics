package net.midget807.narchaotics.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.midget807.narchaotics.registry.ModBlocks;
import net.midget807.narchaotics.registry.ModItems;
import net.midget807.narchaotics.util.ModUtil;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModEnUSLangProvider extends FabricLanguageProvider {
    public ModEnUSLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("#narchaotics:unlock_narchaotics_recipes", "Unlocks Narchaotics Recipes");
        translationBuilder.add("#narchaotics:soul_burner", "Soul Burner Fuels");
        translationBuilder.add("#narchaotics:fermenter", "Fermentation Providers");
        translationBuilder.add("#narchaotics:photoelectric_catalysts", "Photoelectric Catalysts");
        translationBuilder.add("#narchaotics:meth_reducer", "Meth High Effect Reducers");
        translationBuilder.add("#narchaotics:fluid_input_items", "Fluid Insertable Items");
        translationBuilder.add("#narchaotics:fluid_buckets", "Narchaotics Buckets");
        translationBuilder.add("#narchaotics:fluid_bottles", "Narchaotics Bottles");
        translationBuilder.add("#narchaotics:fluid_remove_items", "Fluid Removable Items");
        translationBuilder.add("#narchaotics:conical_flask_fluids", "Fluid Conical Flasks");
        translationBuilder.add("#narchaotics:round_flask_fluids", "Fluid Round Flasks");
        translationBuilder.add("#narchaotics:beaker_fluids", "Fluid Beakers");
        translationBuilder.add("#narchaotics:test_tube_fluids", "Fluid Test Tubes");
        translationBuilder.add("#narchaotics:ammonia_soil", "Ammonia Rich Soils");

        translationBuilder.add("emi.cooking.time", "%s Seconds");
        translationBuilder.add("emi.category.narchaotics.distillation", "Distilling");
        translationBuilder.add("emi.category.narchaotics.filter", "Filtering");
        translationBuilder.add("emi.category.narchaotics.evaporate", "Evaporating");
        translationBuilder.add("emi.category.narchaotics.dissolve", "Dissolving & Mixing");
        translationBuilder.add("emi.category.narchaotics.separate", "Separating");
        translationBuilder.add("emi.category.narchaotics.photoelectric", "Photoelectric Extracting");
        translationBuilder.add("emi.category.narchaotics.ash", "Ashing");
        translationBuilder.add("emi.category.narchaotics.ferment", "Fermenting");

        translationBuilder.add("container.narchaotics.distillation_workbench", "Distilling");
        translationBuilder.add("container.narchaotics.filter_workbench", "Filtering");
        translationBuilder.add("container.narchaotics.evaporate_workbench", "Evaporating");
        translationBuilder.add("container.narchaotics.dissolve_workbench", "Dissolving & Mixing");
        translationBuilder.add("container.narchaotics.separate_workbench", "Separating");
        translationBuilder.add("container.narchaotics.photoelectric_extractor", "Photoelectric Extractor");
        translationBuilder.add("container.narchaotics.fluid_tank.store", "Fluid Tank");
        translationBuilder.add("container.narchaotics.fluid_tank.fermenting", "Fermenting");
        translationBuilder.add("container.narchaotics.fluid_tank.ashing", "Ashing");


        translationBuilder.add("itemGroup.narchaotics.main", "Narchaotics");

        translationBuilder.add(ModBlocks.EPHEDRA_CROP, "Ephedra");
        translationBuilder.add(ModBlocks.DISTILLATION_WORKBENCH, "Distilling Workbench");
        translationBuilder.add(ModBlocks.FILTER_WORKBENCH, "Filtering Workbench");
        translationBuilder.add(ModBlocks.EVAPORATE_WORKBENCH, "Evaporating Workbench");
        translationBuilder.add(ModBlocks.DISSOLVE_WORKBENCH, "Dissolving And Mixing Workbench");
        translationBuilder.add(ModBlocks.SEPARATE_WORKBENCH, "Separating Workbench");
        translationBuilder.add(ModBlocks.PHOTOELECTRIC_EXTRACTOR, "Photoelectric Extractor");
        translationBuilder.add(ModBlocks.TANK, "Fluid Tank");

        ModItems.BUCKETS.forEach((item, identifier) -> {
            translationBuilder.add(item, ModUtil.fluidItemDisplayName(identifier) + " Bucket");
        });
        ModItems.CONICAL_FLASKS.forEach((item, identifier) -> {
            if (item != ModItems.CONICAL_FLASK) {
                translationBuilder.add(item, ModUtil.fluidItemDisplayName(identifier) + " Conical Flask");
            }
        });
        ModItems.BEAKERS.forEach((item, identifier) -> {
            if (item != ModItems.BEAKER) {
                translationBuilder.add(item, ModUtil.fluidItemDisplayName(identifier) + " Beaker");
            }
        });
        ModItems.ROUND_FLASKS.forEach((item, identifier) -> {
            if (item != ModItems.ROUND_FLASK) {
                translationBuilder.add(item, ModUtil.fluidItemDisplayName(identifier) + " Round Flask");
            }
        });
        ModItems.TEST_TUBES.forEach((item, identifier) -> {
            if (item != ModItems.TEST_TUBE) {
                translationBuilder.add(item, ModUtil.fluidItemDisplayName(identifier) + " Test Tube");
            }
        });

        ModBlocks.FLUIDS.forEach((block, identifier) -> {
            if (block != Blocks.WATER) {
                translationBuilder.add(block, ModUtil.getFluidBlockDisplayName(identifier));
            }
        });

        ModItems.DUSTS.forEach((item, identifier) -> {
            translationBuilder.add(item, ModUtil.dustItemDisplayName(identifier) + " Dust");
        });


        translationBuilder.add(ModItems.BURNER, "Burner");
        translationBuilder.add(ModItems.BEAKER, "Beaker");
        translationBuilder.add(ModItems.CONICAL_FLASK, "Conical Flask");
        translationBuilder.add(ModItems.ROUND_FLASK, "Round Bottom Flask");
        translationBuilder.add(ModItems.TEST_TUBE, "Test Tube");
        translationBuilder.add(ModItems.FILTER_PAPER, "Filter Paper");
        translationBuilder.add(ModItems.FUNNEL, "Funnel");
        translationBuilder.add(ModItems.FILTER_FUNNEL, "Filter Funnel");
        translationBuilder.add(ModItems.STAND, "Stand");
        translationBuilder.add(ModItems.CLAMP, "Clamp");
        translationBuilder.add(ModItems.STAND_AND_CLAMP, "Stand And Clamp");
        translationBuilder.add(ModItems.CONDENSER, "Condenser");
        translationBuilder.add(ModItems.SEPARATORY_FUNNEL, "Separatory Funnel");
        translationBuilder.add(ModItems.MORTAR_AND_PESTLE, "Mortar And Pestle");

        translationBuilder.add(ModItems.RESIDUE, "Residue");
        translationBuilder.add(ModItems.NETHERRACK_RESIDUE, "Netherrack Residue");
        translationBuilder.add(ModItems.RED_PHOSPHORUS, "Red Phosphorus");
        translationBuilder.add(ModItems.KELP_ASH, "Kelp Ash");
        translationBuilder.add(ModItems.IODINE, "Iodine");
        translationBuilder.add(ModItems.MIXED_SALTS, "Mixed Salts");
        translationBuilder.add(ModItems.SODIUM_CARBONATE, "Sodium Carbonate");
        translationBuilder.add(ModItems.POTASSIUM_CHLORIDE, "Potassium Chloride");
        translationBuilder.add(ModItems.RICH_SOIL_CLUMP, "Rich Soil Clump");
        translationBuilder.add(ModItems.SOIL_CLUMP, "Soil Clump");
        translationBuilder.add(ModItems.SULPHURIC_ACID, "Sulphuric Acid");
        translationBuilder.add(ModItems.SCHEELITE_PEBBLE, "Scheelite Pebble");
        translationBuilder.add(ModItems.SCHEELITE_CLUMP, "Scheelite Clump");
        translationBuilder.add(ModItems.TUNGSTEN_OXIDE, "Tungsten Oxide");
        translationBuilder.add(ModItems.FERTILISER, "Fertiliser");
        translationBuilder.add(ModItems.EPHEDRA_SEEDS, "Ephedra Seeds");
        translationBuilder.add(ModItems.EPHEDRA, "Ephedra");
        translationBuilder.add(ModItems.DRIED_EPHEDRA, "Dried Ephedra");
        translationBuilder.add(ModItems.CRYSTAL_METHAMPHETAMINE, "Crystal Methamphetamine");
        translationBuilder.add(ModItems.METHAMPHETAMINE, "Methamphetamine");

    }
}
