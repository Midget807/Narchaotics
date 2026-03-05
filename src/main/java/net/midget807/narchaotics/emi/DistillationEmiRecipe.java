package net.midget807.narchaotics.emi;

import dev.emi.emi.api.FabricEmiStack;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.midget807.narchaotics.recipe.DistillationRecipe;
import net.midget807.narchaotics.registry.ModEmiPlugin;
import net.midget807.narchaotics.util.ModEmiUtils;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DistillationEmiRecipe implements EmiRecipe {
    private final Identifier id;
    private final List<EmiIngredient> inputs;
    private final List<EmiStack> outputs;
    private final DistillationRecipe recipe;

    public DistillationEmiRecipe(RecipeEntry<DistillationRecipe> recipe) {
        this.id = recipe.id();
        this.inputs = List.of(
                EmiIngredient.of(recipe.value().item1),
                EmiIngredient.of(recipe.value().item2),
                FabricEmiStack.of(recipe.value().fluid1.variant(), recipe.value().fluid1.amount() * 81),
                FabricEmiStack.of(recipe.value().fluid2.variant(), recipe.value().fluid2.amount() * 81),
                EmiIngredient.of(recipe.value().fuelType)
        );
        this.outputs = List.of(
                EmiStack.of(recipe.value().result1),
                EmiStack.of(recipe.value().result2),
                FabricEmiStack.of(recipe.value().product1.variant(), recipe.value().product1.amount() * 81),
                FabricEmiStack.of(recipe.value().product2.variant(), recipe.value().product2.amount() * 81)
        );
        this.recipe = recipe.value();
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return ModEmiPlugin.DISTILLATION_CATEGORY;
    }

    @Override
    public @Nullable Identifier getId() {
        return this.id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return this.inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return this.outputs;
    }

    @Override
    public int getDisplayWidth() {
        return 142;
    }

    @Override
    public int getDisplayHeight() {
        return 88;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addFillingArrow(58, 36, 50 * recipe.getCookingTime()).tooltip((mx, my) -> {
            return List.of(TooltipComponent.of(ModEmiUtils.ordered(ModEmiUtils.translatable("emi.cooking.time", recipe.getCookingTime() / 20f))));
        });
        widgets.addTexture(EmiTexture.FULL_FLAME, 64, 54);

        widgets.addSlot(inputs.get(0), 34, 20);
        widgets.addSlot(inputs.get(1), 34, 50);
        widgets.addTank(inputs.get(2), 1, 1, 18, 18, 250 * 81);
        widgets.addTank(inputs.get(3), 1, 60, 18, 18, 250 * 81);
        widgets.addSlot(inputs.get(4), 62, 69);

        widgets.addSlot(outputs.get(0), 90, 20).recipeContext(this);
        widgets.addSlot(outputs.get(1), 90, 50).recipeContext(this);
        widgets.addTank(outputs.get(2), 123, 1, 18, 18, 250 * 81).recipeContext(this);
        widgets.addTank(outputs.get(3), 123, 60, 18, 18, 250 * 81).recipeContext(this);
    }
}
