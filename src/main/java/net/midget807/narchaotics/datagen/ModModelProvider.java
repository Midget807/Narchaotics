package net.midget807.narchaotics.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.midget807.narchaotics.registry.ModBlocks;
import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.item.Items;
import net.minecraft.state.property.Properties;

import static net.minecraft.data.client.BlockStateModelGenerator.createSingletonBlockState;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.EPHEDRA_CROP, BlockStateModelGenerator.TintType.NOT_TINTED, Properties.AGE_7, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.DISTILLATION_WORKBENCH);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.FILTER_WORKBENCH);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.EVAPORATE_WORKBENCH);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.DISSOLVE_WORKBENCH);

        blockStateModelGenerator.blockStateCollector.accept(
                createSingletonBlockState(
                        ModBlocks.ETHANOL_CAULDRON,
                        Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.ETHANOL_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
                )
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ICON, Models.GENERATED);

        itemModelGenerator.register(ModItems.ETHANOL_BUCKET, Models.GENERATED);

        itemModelGenerator.register(ModItems.BURNER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CONICAL_FLASK, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEAKER, Models.GENERATED);
        itemModelGenerator.register(ModItems.FILTER_PAPER, Models.GENERATED);
        itemModelGenerator.register(ModItems.FUNNEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.FILTER_FUNNEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.STAND, Models.GENERATED);
        itemModelGenerator.register(ModItems.CLAMP, Models.GENERATED);
        itemModelGenerator.register(ModItems.STAND_AND_CLAMP, Models.GENERATED);
        itemModelGenerator.register(ModItems.CONDENSER, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROUND_FLASK, Models.GENERATED);
        itemModelGenerator.register(ModItems.TEST_TUBE, Models.GENERATED);

        itemModelGenerator.register(ModItems.EPHEDRA, Models.GENERATED);
        itemModelGenerator.register(ModItems.DRIED_EPHEDRA, Models.GENERATED);
        itemModelGenerator.register(ModItems.EPHEDRA_DUST, Models.GENERATED);

        itemModelGenerator.register(ModItems.METHAMPHETAMINE, Models.GENERATED);
    }
}
