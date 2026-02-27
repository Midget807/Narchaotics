package net.midget807.narchaotics.datagen.json_builder;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.midget807.narchaotics.recipe.DissolveRecipe;
import net.midget807.narchaotics.recipe.DistillationRecipe;
import net.midget807.narchaotics.recipe.FluidStack;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class DissolveRecipeJsonBuilder {
    private final Ingredient input1;
    private final Ingredient input2;
    private final FluidStack reactant1;
    private final FluidStack reactant2;
    private final Ingredient fuel;
    private final int cookingTime;
    private final FluidStack product;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap();

    public DissolveRecipeJsonBuilder(Ingredient input1, Ingredient input2, FluidStack reactant1, FluidStack reactant2, Ingredient fuel, int cookingTime, FluidStack product) {
        this.input1 = input1;
        this.input2 = input2;
        this.reactant1 = reactant1;
        this.reactant2 = reactant2;
        this.fuel = fuel;
        this.cookingTime = cookingTime;
        this.product = product;
    }

    public static DissolveRecipeJsonBuilder create(Ingredient input1, Ingredient input2, Fluid fluidInput1, long fluidInput1Amount, Fluid fluidInput2, long fluidInput2Amount, Ingredient fuel, int cookingTime, ItemConvertible output1, ItemConvertible output2, Fluid fluidOutput1, long fluidOutput1Amount, Fluid fluidOutput2, long fluidOutput2Amount) {
        return new DissolveRecipeJsonBuilder(
                input1,
                input2,
                new FluidStack(FluidVariant.of(fluidInput1), fluidInput1Amount),
                new FluidStack(FluidVariant.of(fluidInput2), fluidInput2Amount),
                fuel,
                cookingTime,
                new FluidStack(FluidVariant.of(fluidOutput1), fluidOutput1Amount)
        );
    }

    public DissolveRecipeJsonBuilder criterion(String string, AdvancementCriterion<?> advancementCriterion) {
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
        DissolveRecipe dissolveRecipe = new DissolveRecipe(
                this.input1,
                this.input2,
                this.reactant1,
                this.reactant2,
                this.fuel,
                this.cookingTime,
                this.product
        );
        exporter.accept(recipeId, dissolveRecipe, builder.build(recipeId.withPrefixedPath("recipes/")));
    }

    private void validate(Identifier recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }

}
