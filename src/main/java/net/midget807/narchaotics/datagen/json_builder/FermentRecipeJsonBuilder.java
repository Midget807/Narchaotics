package net.midget807.narchaotics.datagen.json_builder;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.midget807.narchaotics.recipe.FermentRecipe;
import net.midget807.narchaotics.recipe.FluidStack;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.fluid.Fluid;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class FermentRecipeJsonBuilder {
    private final FluidStack input;
    private final Ingredient catalyst;
    private final int fermentTime;
    private final FluidStack output;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap();

    public FermentRecipeJsonBuilder(FluidStack input, Ingredient catalyst, int fermentTime, FluidStack output) {
        this.input = input;
        this.catalyst = catalyst;
        this.fermentTime = fermentTime;
        this.output = output;
    }


    public static FermentRecipeJsonBuilder create(Fluid input, Ingredient catalyst, int fermentTime, Fluid output, long fluidAmount) {
        return new FermentRecipeJsonBuilder(
                new FluidStack(FluidVariant.of(input), fluidAmount),
                catalyst,
                fermentTime,
                new FluidStack(FluidVariant.of(output), fluidAmount)
        );
    }

    public FermentRecipeJsonBuilder criterion(String string, AdvancementCriterion<?> advancementCriterion) {
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
        FermentRecipe fermentRecipe = new FermentRecipe(
                this.input,
                this.catalyst,
                this.fermentTime,
                this.output
        );
        exporter.accept(recipeId, fermentRecipe, builder.build(recipeId.withPrefixedPath("recipes/")));
    }

    private void validate(Identifier recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }
}
