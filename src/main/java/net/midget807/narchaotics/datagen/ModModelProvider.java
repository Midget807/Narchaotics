package net.midget807.narchaotics.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.registry.ModBlocks;
import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

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
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.SEPARATE_WORKBENCH);
        blockStateModelGenerator.registerSingleton(ModBlocks.PHOTOELECTRIC_EXTRACTOR, TexturedModel.CUBE_BOTTOM_TOP);

        registerCauldrons(blockStateModelGenerator);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ICON, Models.GENERATED);

        itemModelGenerator.register(ModItems.CONICAL_FLASK, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROUND_FLASK, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEAKER, Models.GENERATED);
        itemModelGenerator.register(ModItems.TEST_TUBE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BURNER, Models.GENERATED);
        itemModelGenerator.register(ModItems.FILTER_PAPER, Models.GENERATED);
        itemModelGenerator.register(ModItems.FUNNEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.FILTER_FUNNEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.STAND, Models.GENERATED);
        itemModelGenerator.register(ModItems.CLAMP, Models.GENERATED);
        itemModelGenerator.register(ModItems.STAND_AND_CLAMP, Models.GENERATED);
        itemModelGenerator.register(ModItems.CONDENSER, Models.GENERATED);

        itemModelGenerator.register(ModItems.EPHEDRA, Models.GENERATED);
        itemModelGenerator.register(ModItems.DRIED_EPHEDRA, Models.GENERATED);

        itemModelGenerator.register(ModItems.METHAMPHETAMINE, Models.GENERATED);

        uploadPowderTexturedDust(ModItems.NETHERRACK_DUST, itemModelGenerator);
        uploadPowderTexturedDust(ModItems.RED_PHOSPHORUS_DUST, itemModelGenerator);
        uploadPowderTexturedDust(ModItems.KELP_ASH, itemModelGenerator);
        uploadPowderTexturedDust(ModItems.SODIUM_CARBONATE_DUST, itemModelGenerator);
        uploadPowderTexturedDust(ModItems.EPHEDRA_DUST, itemModelGenerator);

        uploadCrystalTexturedDust(ModItems.IODINE_DUST, itemModelGenerator);
        uploadCrystalTexturedDust(ModItems.MIXED_SALTS, itemModelGenerator);
        uploadCrystalTexturedDust(ModItems.POTASSIUM_CHLORIDE_DUST, itemModelGenerator);
        uploadCrystalTexturedDust(ModItems.SULPHURIC_ACID, itemModelGenerator);
        itemModelGenerator.register(ModItems.CALCITE_DUST, Models.GENERATED);
        uploadCrystalTexturedDust(ModItems.TUNGSTEN_OXIDE_DUST, itemModelGenerator);

        ModItems.BUCKETS.forEach((item, identifier) -> {
            uploadFluidContainers(identifier, "bucket/fluid_bucket", "bucket/fluid_bucket_overlay", itemModelGenerator);
        });
        ModItems.CONICAL_FLASKS.forEach((item, identifier) -> {
            if (item != ModItems.CONICAL_FLASK) uploadFluidContainers(identifier, "conical_flask/fluid_conical_flask", "conical_flask/fluid_conical_flask_overlay", itemModelGenerator);
        });
        ModItems.ROUND_FLASKS.forEach((item, identifier) -> {
            if (item != ModItems.ROUND_FLASK) uploadFluidContainers(identifier, "round_flask/fluid_round_flask", "round_flask/fluid_round_flask_overlay", itemModelGenerator);
        });
        ModItems.BEAKERS.forEach((item, identifier) -> {
            if (item != ModItems.BEAKER) uploadFluidContainers(identifier, "beaker/fluid_beaker", "beaker/fluid_beaker_overlay", itemModelGenerator);
        });
        ModItems.TEST_TUBES.forEach((item, identifier) -> {
            if (item != ModItems.TEST_TUBE) uploadFluidContainers(identifier, "test_tube/fluid_test_tube", "test_tube/fluid_test_tube_overlay", itemModelGenerator);
        });

    }

    private void uploadPowderTexturedDust(Item item, ItemModelGenerator itemModelGenerator) {
        Identifier id = Registries.ITEM.getId(item);
        Models.GENERATED.upload(Identifier.of(id.getNamespace(), "item/" + id.getPath()), new TextureMap().put(TextureKey.LAYER0, NarchaoticsMain.id("item/dust/powder")), itemModelGenerator.writer);
    }
    private void uploadCrystalTexturedDust(Item item, ItemModelGenerator itemModelGenerator) {
        Identifier id = Registries.ITEM.getId(item);
        Models.GENERATED.upload(Identifier.of(id.getNamespace(), "item/" + id.getPath()), new TextureMap().put(TextureKey.LAYER0, NarchaoticsMain.id("item/dust/crystal")), itemModelGenerator.writer);
    }

    private void uploadFluidContainers(Identifier item, String layer0, String layer1, ItemModelGenerator itemModelGenerator) {
        Models.GENERATED_TWO_LAYERS.upload(Identifier.of(item.getNamespace(), "item/" + item.getPath()), TextureMap.layered(NarchaoticsMain.id("item/" + layer0), NarchaoticsMain.id("item/" + layer1)), itemModelGenerator.writer);
    }

    private static void registerCauldrons(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.DIRTY_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.DIRTY_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.ETHANOL_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.ETHANOL_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.SALT_WATER_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.SALT_WATER_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.VOLCANIC_WATER_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.VOLCANIC_WATER_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.NETHERRACK_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.NETHERRACK_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.RED_PHOSPHORUS_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.RED_PHOSPHORUS_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.RED_PHOSPHORUS_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.RED_PHOSPHORUS_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.DIRTY_ASH_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.DIRTY_ASH_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.CLEAN_ASH_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.CLEAN_ASH_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.DIRTY_IODINE_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.DIRTY_IODINE_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.CLEAN_IODINE_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.CLEAN_IODINE_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.IODINE_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.IODINE_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.SALT_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.SALT_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.CRYSTALISED_SALT_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.CRYSTALISED_SALT_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.SODIUM_CARBONATE_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.SODIUM_CARBONATE_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.SODIUM_CARBONATE_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.SODIUM_CARBONATE_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.AMMONIA_SLUDGE_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.AMMONIA_SLUDGE_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.AMMONIA_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.AMMONIA_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.AMMONIA_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.AMMONIA_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.HYDROGEN_PEROXIDE_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.HYDROGEN_PEROXIDE_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.BRINE_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.BRINE_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.CONCENTRATED_VOLCANIC_WATER_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.CONCENTRATED_VOLCANIC_WATER_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.SULPHURIC_ACID_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.SULPHURIC_ACID_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.SULPHURIC_ACID_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.SULPHURIC_ACID_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.HYDRAZINE_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.HYDRAZINE_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.HYDRAZINE_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.HYDRAZINE_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.HYDROIODIC_ACID_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.HYDROIODIC_ACID_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.EPHEDRA_SLUDGE_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.EPHEDRA_SLUDGE_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.DIRTY_EPHEDRA_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.DIRTY_EPHEDRA_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.CLEAN_EPHEDRA_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.CLEAN_EPHEDRA_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.CONCENTRATED_EPHEDRA_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.CONCENTRATED_EPHEDRA_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.ALKALINE_EPHEDRA_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.ALKALINE_EPHEDRA_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.EPHEDRINE_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.EPHEDRINE_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.PSEUDOEPHEDRINE_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.PSEUDOEPHEDRINE_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.METHAMPHETAMINE_ACID_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.METHAMPHETAMINE_ACID_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.METHAMPHETAMINE_SOLUTION_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.METHAMPHETAMINE_SOLUTION_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));

        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(
                ModBlocks.METHAMPHETAMINE_CAULDRON,
                Models.TEMPLATE_CAULDRON_FULL.upload(ModBlocks.METHAMPHETAMINE_CAULDRON, TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)
        ));
    }
}
