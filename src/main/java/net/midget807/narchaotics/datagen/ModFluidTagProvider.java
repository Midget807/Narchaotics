package net.midget807.narchaotics.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.midget807.narchaotics.NarchaoticsMain;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagProvider extends FabricTagProvider.FluidTagProvider {
    public static final TagKey<Fluid> ETHANOL_EXTRACTOR = TagKey.of(RegistryKeys.FLUID, NarchaoticsMain.id("extracts_with_ethanol"));

    public ModFluidTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(ETHANOL_EXTRACTOR)
                .addOptional(Identifier.of("brewinandchewin:vodka"))
                .addOptionalTag(Identifier.of("c:ethanol"));
    }
}
