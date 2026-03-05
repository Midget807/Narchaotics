package net.midget807.narchaotics.emi;

import dev.emi.emi.api.FabricEmiStack;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.midget807.narchaotics.recipe.FilterRecipe;
import net.midget807.narchaotics.registry.ModEmiPlugin;
import net.midget807.narchaotics.util.ModEmiUtils;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FilterEmiRecipe implements EmiRecipe {
    private final Identifier id;
    private final List<EmiIngredient> inputs;
    private final List<EmiStack> outputs;
    private final FilterRecipe recipe;

    public FilterEmiRecipe(RecipeEntry<FilterRecipe> recipe) {
        this.id = recipe.id();
        this.inputs = List.of(
                FabricEmiStack.of(recipe.value().input.variant(), recipe.value().input.amount() * 81)
        );
        this.outputs = List.of(
                FabricEmiStack.of(recipe.value().filtrate.variant(), recipe.value().filtrate.amount() * 81),
                EmiStack.of(recipe.value().residue)
        );
        this.recipe = recipe.value();
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return ModEmiPlugin.FILTER_CATEGORY;
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
        return 84;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addFillingArrow(58, 36, 50 * recipe.getFilterTime()).tooltip((mx, my) -> {
            return List.of(TooltipComponent.of(ModEmiUtils.ordered(ModEmiUtils.translatable("emi.cooking.time", recipe.getFilterTime() / 20f))));
        });

        widgets.addTank(inputs.getFirst(), 1, 1, 18, 18, 250 * 81);

        widgets.addSlot(outputs.get(1), 90, 2).recipeContext(this);
        widgets.addTank(outputs.get(0), 60, 1, 18, 18, 250 * 81).recipeContext(this);
    }
}
