package net.midget807.narchaotics.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
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
        translationBuilder.add("container.narchaotics.evaporate_workbench", "Evaporating & Ashing");
        translationBuilder.add("container.narchaotics.dissolve_workbench", "Dissolving & Mixing");
    }
}
