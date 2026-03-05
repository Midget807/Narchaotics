package net.midget807.narchaotics.datagen.json_builder;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.midget807.narchaotics.recipe.DistillationRecipe;
import net.midget807.narchaotics.recipe.FluidStack;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.midget807.narchaotics.datagen.ModRecipeProvider.conditionsFromChemistry;
import static net.midget807.narchaotics.datagen.ModRecipeProvider.hasChemistry;

public class DistillationRecipeJsonBuilder {
    private final Ingredient input1;
    private final Ingredient input2;
    private final FluidStack reactant1;
    private final FluidStack reactant2;
    private final Ingredient fuel;
    private final int cookingTime;
    private final Item output1;
    private final Item output2;
    private final FluidStack product1;
    private final FluidStack product2;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap();

    public DistillationRecipeJsonBuilder(Ingredient input1, Ingredient input2, FluidStack reactant1, FluidStack reactant2, Ingredient fuel, int cookingTime, ItemConvertible output1, ItemConvertible output2, FluidStack product1, FluidStack product2) {
        this.input1 = input1;
        this.input2 = input2;
        this.reactant1 = reactant1;
        this.reactant2 = reactant2;
        this.fuel = fuel;
        this.cookingTime = cookingTime;
        this.output1 = output1.asItem();
        this.output2 = output2.asItem();
        this.product1 = product1;
        this.product2 = product2;
    }

    public static DistillationRecipeJsonBuilder create(Ingredient input1, Ingredient input2, Fluid fluidInput1, long fluidInput1Amount, Fluid fluidInput2, long fluidInput2Amount, Ingredient fuel, int cookingTime, ItemConvertible output1, ItemConvertible output2, Fluid fluidOutput1, long fluidOutput1Amount, Fluid fluidOutput2, long fluidOutput2Amount) {
        return new DistillationRecipeJsonBuilder(
                input1,
                input2,
                new FluidStack(FluidVariant.of(fluidInput1), fluidInput1Amount),
                new FluidStack(FluidVariant.of(fluidInput2), fluidInput2Amount),
                fuel,
                cookingTime,
                output1,
                output2,
                new FluidStack(FluidVariant.of(fluidOutput1), fluidOutput1Amount),
                new FluidStack(FluidVariant.of(fluidOutput2), fluidOutput2Amount)
        );
    }

    public static DistillationRecipeJsonBuilder createConcentrating(Fluid fluidInput1, Ingredient fuel, int cookingTime, Fluid fluidOutput1, long inputAmount, long outputAmount) {
        return new DistillationRecipeJsonBuilder(
                Ingredient.EMPTY,
                Ingredient.EMPTY,
                new FluidStack(FluidVariant.of(fluidInput1), inputAmount),
                FluidStack.EMPTY,
                fuel,
                cookingTime,
                Items.AIR,
                Items.AIR,
                new FluidStack(FluidVariant.of(fluidOutput1), outputAmount),
                new FluidStack(FluidVariant.of(Fluids.WATER), inputAmount - outputAmount)
        ).criterion(hasChemistry(), conditionsFromChemistry());
    }
    public static DistillationRecipeJsonBuilder createConcentrating(Fluid fluidInput1, int cookingTime, Fluid fluidOutput1, long inputAmount, long outputAmount) {
        return new DistillationRecipeJsonBuilder(
                Ingredient.EMPTY,
                Ingredient.EMPTY,
                new FluidStack(FluidVariant.of(fluidInput1), inputAmount),
                FluidStack.EMPTY,
                Ingredient.EMPTY,
                cookingTime,
                Items.AIR,
                Items.AIR,
                new FluidStack(FluidVariant.of(fluidOutput1), outputAmount),
                new FluidStack(FluidVariant.of(Fluids.WATER), inputAmount - outputAmount)
        ).criterion(hasChemistry(), conditionsFromChemistry());
    }
    public static DistillationRecipeJsonBuilder createConcentrating(Fluid fluidInput1, int cookingTime, Fluid fluidOutput1, Fluid solvent, long inputAmount, long outputAmount) {
        return new DistillationRecipeJsonBuilder(
                Ingredient.EMPTY,
                Ingredient.EMPTY,
                new FluidStack(FluidVariant.of(fluidInput1), inputAmount),
                FluidStack.EMPTY,
                Ingredient.EMPTY,
                cookingTime,
                Items.AIR,
                Items.AIR,
                new FluidStack(FluidVariant.of(fluidOutput1), outputAmount),
                new FluidStack(FluidVariant.of(solvent), inputAmount - outputAmount)
        ).criterion(hasChemistry(), conditionsFromChemistry());
    }

    public static DistillationRecipeJsonBuilder createOneFluid2OneItemOneFluid(Fluid fluidInput1, long inputAmount, Ingredient fuel, int cookingTime, ItemConvertible itemOutput, Fluid fluidOutput1, long outputAmount) {
        return new DistillationRecipeJsonBuilder(
                Ingredient.EMPTY,
                Ingredient.EMPTY,
                new FluidStack(FluidVariant.of(fluidInput1), inputAmount),
                FluidStack.EMPTY,
                fuel,
                cookingTime,
                itemOutput,
                Items.AIR,
                new FluidStack(FluidVariant.of(fluidOutput1), outputAmount),
                FluidStack.EMPTY
        ).criterion(hasChemistry(), conditionsFromChemistry());
    }
    public static DistillationRecipeJsonBuilder createOneFluid2OneItemOneFluid(Fluid fluidInput1, long inputAmount, int cookingTime, ItemConvertible itemOutput, Fluid fluidOutput1, long outputAmount) {
        return new DistillationRecipeJsonBuilder(
                Ingredient.EMPTY,
                Ingredient.EMPTY,
                new FluidStack(FluidVariant.of(fluidInput1), inputAmount),
                FluidStack.EMPTY,
                Ingredient.EMPTY,
                cookingTime,
                itemOutput,
                Items.AIR,
                new FluidStack(FluidVariant.of(fluidOutput1), outputAmount),
                FluidStack.EMPTY
        ).criterion(hasChemistry(), conditionsFromChemistry());
    }

    public static DistillationRecipeJsonBuilder createOneItemOneFluid2OneItemOneFluid(Ingredient input1, Fluid fluidInput1, long inputAmount, Ingredient fuel, int cookingTime, ItemConvertible itemOutput, Fluid fluidOutput1, long outputAmount) {
        return new DistillationRecipeJsonBuilder(
                input1,
                Ingredient.EMPTY,
                new FluidStack(FluidVariant.of(fluidInput1), inputAmount),
                FluidStack.EMPTY,
                fuel,
                cookingTime,
                itemOutput,
                Items.AIR,
                new FluidStack(FluidVariant.of(fluidOutput1), outputAmount),
                FluidStack.EMPTY
        ).criterion(hasChemistry(), conditionsFromChemistry());
    }
    public static DistillationRecipeJsonBuilder createOneItemOneFluid2OneItemOneFluid(Ingredient input1, Fluid fluidInput1, long inputAmount, int cookingTime, ItemConvertible itemOutput, Fluid fluidOutput1, long outputAmount) {
        return new DistillationRecipeJsonBuilder(
                input1,
                Ingredient.EMPTY,
                new FluidStack(FluidVariant.of(fluidInput1), inputAmount),
                FluidStack.EMPTY,
                Ingredient.EMPTY,
                cookingTime,
                itemOutput,
                Items.AIR,
                new FluidStack(FluidVariant.of(fluidOutput1), outputAmount),
                FluidStack.EMPTY
        ).criterion(hasChemistry(), conditionsFromChemistry());
    }

    public DistillationRecipeJsonBuilder criterion(String string, AdvancementCriterion<?> advancementCriterion) {
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
        DistillationRecipe distillationRecipe = new DistillationRecipe(
                this.input1,
                this.input2,
                this.reactant1,
                this.reactant2,
                this.fuel,
                this.cookingTime,
                this.output1 == Items.AIR ? ItemStack.EMPTY : new ItemStack(this.output1),
                this.output2 == Items.AIR ? ItemStack.EMPTY : new ItemStack(this.output2),
                this.product1,
                this.product2
        );
        exporter.accept(recipeId, distillationRecipe, builder.build(recipeId.withPrefixedPath("recipes/")));
    }

    private void validate(Identifier recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }

}
