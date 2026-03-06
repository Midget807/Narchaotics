package net.midget807.narchaotics.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.midget807.narchaotics.block.EphedraCropBlock;
import net.midget807.narchaotics.registry.ModBlocks;
import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.EPHEDRA_CROP, this.cropDrops(ModBlocks.EPHEDRA_CROP, ModItems.EPHEDRA, ModItems.EPHEDRA_SEEDS, BlockStatePropertyLootCondition.builder(ModBlocks.EPHEDRA_CROP).properties(StatePredicate.Builder.create().exactMatch(EphedraCropBlock.AGE, 7))));
        addDrop(ModBlocks.DISTILLATION_WORKBENCH);
        addDrop(ModBlocks.FILTER_WORKBENCH);
        addDrop(ModBlocks.EVAPORATE_WORKBENCH);
        addDrop(ModBlocks.DISSOLVE_WORKBENCH);
        addDrop(ModBlocks.SEPARATE_WORKBENCH);
        addDrop(ModBlocks.PHOTOELECTRIC_EXTRACTOR);
    }
}
