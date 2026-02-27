package net.midget807.narchaotics.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.midget807.narchaotics.registry.ModBlocks;
import net.midget807.narchaotics.registry.ModItems;
import net.midget807.narchaotics.util.ModUtil;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModEnUSLangProvider extends FabricLanguageProvider {
    public ModEnUSLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("container.narchaotics.distillation_workbench", "Distilling");
        translationBuilder.add("container.narchaotics.filter_workbench", "Filtering");
        translationBuilder.add("container.narchaotics.evaporate_workbench", "Evaporating");
        translationBuilder.add("container.narchaotics.dissolve_workbench", "Dissolving & Mixing");
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

        translationBuilder.add(ModItems.EPHEDRA_SEEDS, "Ephedra Seeds");
        translationBuilder.add(ModItems.EPHEDRA, "Ephedra");
        translationBuilder.add(ModItems.DRIED_EPHEDRA, "Dried Ephedra");

    }
}
