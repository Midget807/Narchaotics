package net.midget807.narchaotics.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public static final TagKey<Item> SOUL_BURNER = TagKey.of(RegistryKeys.ITEM, NarchaoticsMain.id("soul_burner"));
    public static final TagKey<Item> PHOTOELECTRIC_CATALYSTS = TagKey.of(RegistryKeys.ITEM, NarchaoticsMain.id("photoelectric_catalysts"));
    public static final TagKey<Item> METH_REDUCER = TagKey.of(RegistryKeys.ITEM, NarchaoticsMain.id("meth_reducer"));
    public static final TagKey<Item> DISTILLATION_OUTPUT = TagKey.of(RegistryKeys.ITEM, NarchaoticsMain.id("distillation_output"));
    public static final TagKey<Item> FLUID_INPUT_ITEMS = TagKey.of(RegistryKeys.ITEM, NarchaoticsMain.id("fluid_input_items"));
    public static final TagKey<Item> FLUID_BUCKETS = TagKey.of(RegistryKeys.ITEM, NarchaoticsMain.id("fluid_buckets"));
    public static final TagKey<Item> FLUID_BOTTLES = TagKey.of(RegistryKeys.ITEM, NarchaoticsMain.id("fluid_bottles"));
    public static final TagKey<Item> FLUID_REMOVE_ITEMS = TagKey.of(RegistryKeys.ITEM, NarchaoticsMain.id("fluid_remove_items"));
    public static final TagKey<Item> CONICAL_FLASK_FLUIDS = TagKey.of(RegistryKeys.ITEM, NarchaoticsMain.id("conical_flask_fluids"));
    public static final TagKey<Item> ROUND_FLASK_FLUIDS = TagKey.of(RegistryKeys.ITEM, NarchaoticsMain.id("round_flask_fluids"));
    public static final TagKey<Item> BEAKER_FLUIDS = TagKey.of(RegistryKeys.ITEM, NarchaoticsMain.id("beaker_fluids"));
    public static final TagKey<Item> TEST_TUBE_FLUIDS = TagKey.of(RegistryKeys.ITEM, NarchaoticsMain.id("test_tube_fluids"));
    public static final TagKey<Item> AMMONIA_SOIL = TagKey.of(RegistryKeys.ITEM, NarchaoticsMain.id("ammonia_soil"));

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(SOUL_BURNER)
                .add(
                        Items.SOUL_SAND,
                        Items.SOUL_SOIL
                );
        this.getOrCreateTagBuilder(PHOTOELECTRIC_CATALYSTS)
                .add(
                        ModItems.TUNGSTEN_OXIDE,
                        ModItems.TUNGSTEN_OXIDE_DUST
                );
        this.getOrCreateTagBuilder(METH_REDUCER)
                .add(
                        Items.SNOW_BLOCK,
                        Items.ICE,
                        Items.PACKED_ICE,
                        Items.BLUE_ICE
                );
        this.getOrCreateTagBuilder(DISTILLATION_OUTPUT)
                .add(
                        ModItems.CONICAL_FLASK,
                        ModItems.BEAKER,
                        ModItems.ROUND_FLASK
                );

        this.getOrCreateTagBuilder(FLUID_BOTTLES)
                .add(
                        ModItems.CONICAL_FLASK,
                        ModItems.BEAKER,
                        ModItems.ROUND_FLASK,
                        ModItems.TEST_TUBE
                );

        ModItems.BUCKETS.forEach((item, identifier) -> this.getOrCreateTagBuilder(FLUID_BUCKETS).add(item));

        ModItems.CONICAL_FLASKS.forEach((item, identifier) -> this.getOrCreateTagBuilder(CONICAL_FLASK_FLUIDS).add(item));

        ModItems.ROUND_FLASKS.forEach((item, identifier) -> this.getOrCreateTagBuilder(ROUND_FLASK_FLUIDS).add(item));

        ModItems.BEAKERS.forEach((item, identifier) -> this.getOrCreateTagBuilder(BEAKER_FLUIDS).add(item));

        ModItems.TEST_TUBES.forEach((item, identifier) -> this.getOrCreateTagBuilder(TEST_TUBE_FLUIDS).add(item));

        this.getOrCreateTagBuilder(FLUID_INPUT_ITEMS)
                .add(
                        Items.WATER_BUCKET
                )
                .addTag(FLUID_BUCKETS)
                .addTag(CONICAL_FLASK_FLUIDS)
                .addTag(ROUND_FLASK_FLUIDS)
                .addTag(BEAKER_FLUIDS)
                .addTag(TEST_TUBE_FLUIDS);

        this.getOrCreateTagBuilder(FLUID_REMOVE_ITEMS)
                .add(
                        Items.BUCKET
                )
                .addTag(FLUID_BOTTLES);

        this.getOrCreateTagBuilder(AMMONIA_SOIL)
                .add(Items.PODZOL)
                .addOptional(Identifier.of("farmersdelight", "rich_soil"));
    }
}
