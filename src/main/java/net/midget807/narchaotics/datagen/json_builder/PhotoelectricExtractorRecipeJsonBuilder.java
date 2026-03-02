package net.midget807.narchaotics.datagen.json_builder;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.midget807.narchaotics.recipe.FluidStack;
import net.midget807.narchaotics.recipe.PhotoelectricExtractorRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class PhotoelectricExtractorRecipeJsonBuilder {
    private final FluidStack inputFluid;
    private final Ingredient catalyst;
    private final int extractTime;
    private final FluidStack outputFluid;
    private final Item outputItem;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap();

    public PhotoelectricExtractorRecipeJsonBuilder(FluidStack inputFluid, Ingredient catalyst, int extractTime, FluidStack outputFluid, ItemConvertible outputItem) {
        this.inputFluid = inputFluid;
        this.catalyst = catalyst;
        this.extractTime = extractTime;
        this.outputFluid = outputFluid;
        this.outputItem = outputItem.asItem();
    }

    public static PhotoelectricExtractorRecipeJsonBuilder create(Fluid fluidInput, long fluidAmount, Ingredient catalyst, int extractTime, Fluid outputFluid, ItemConvertible outputItem) {
        return new PhotoelectricExtractorRecipeJsonBuilder(
                new FluidStack(FluidVariant.of(fluidInput), fluidAmount),
                catalyst,
                extractTime,
                new FluidStack(FluidVariant.of(outputFluid), fluidAmount),
                outputItem
        );
    }
    public PhotoelectricExtractorRecipeJsonBuilder criterion(String string, AdvancementCriterion<?> advancementCriterion) {
        this.criteria.put(string, advancementCriterion);
        return this;
    }

    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        this.validate(recipeId);
        Advancement.Builder builder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        this.criteria.forEach(builder::criterion);
        PhotoelectricExtractorRecipe photoelectricExtractorRecipe = new PhotoelectricExtractorRecipe(
                this.inputFluid,
                this.catalyst,
                this.extractTime,
                this.outputFluid,
                this.outputItem == Items.AIR ? ItemStack.EMPTY : new ItemStack(this.outputItem)
        );
        exporter.accept(recipeId, photoelectricExtractorRecipe, builder.build(recipeId.withPrefixedPath("recipes/")));
    }

    private void validate(Identifier recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }
}
