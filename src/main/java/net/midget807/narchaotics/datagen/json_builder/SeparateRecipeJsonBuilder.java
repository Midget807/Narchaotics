package net.midget807.narchaotics.datagen.json_builder;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.midget807.narchaotics.recipe.FilterRecipe;
import net.midget807.narchaotics.recipe.FluidStack;
import net.midget807.narchaotics.recipe.SeparateRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class SeparateRecipeJsonBuilder {
    private final FluidStack input;
    private final int cookingTime;
    private final FluidStack output;
    private final FluidStack remainder;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap();

    public SeparateRecipeJsonBuilder(FluidStack input, int cookingTime, FluidStack output, FluidStack remainder) {
        this.input = input;
        this.cookingTime = cookingTime;
        this.output = output;
        this.remainder = remainder;
    }



    public static SeparateRecipeJsonBuilder create(Fluid input, int cookingTime, Fluid output, Fluid remainder, long fluidAmount) {
        return new SeparateRecipeJsonBuilder(
                new FluidStack(FluidVariant.of(input), fluidAmount),
                cookingTime,
                new FluidStack(FluidVariant.of(output), fluidAmount),
                new FluidStack(FluidVariant.of(remainder), fluidAmount)
        );
    }

    public SeparateRecipeJsonBuilder criterion(String string, AdvancementCriterion<?> advancementCriterion) {
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
        SeparateRecipe separateRecipe = new SeparateRecipe(
                this.input,
                this.cookingTime,
                this.output,
                this.remainder
        );
        exporter.accept(recipeId, separateRecipe, builder.build(recipeId.withPrefixedPath("recipes/")));
    }

    private void validate(Identifier recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }
}
