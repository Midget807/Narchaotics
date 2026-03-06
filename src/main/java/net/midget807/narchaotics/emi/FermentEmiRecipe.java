package net.midget807.narchaotics.emi;

import dev.emi.emi.api.FabricEmiStack;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.midget807.narchaotics.recipe.FermentRecipe;
import net.midget807.narchaotics.registry.ModEmiPlugin;
import net.midget807.narchaotics.util.ModEmiUtils;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FermentEmiRecipe implements EmiRecipe {
    private final Identifier id;
    private final List<EmiIngredient> inputs;
    private final List<EmiStack> outputs;
    private final FermentRecipe recipe;

    public FermentEmiRecipe(RecipeEntry<FermentRecipe> recipe) {
        this.id = recipe.id();
        this.inputs = List.of(
                FabricEmiStack.of(recipe.value().input.variant(), recipe.value().input.amount() * 81),
                EmiIngredient.of(recipe.value().catalyst)
        );
        this.outputs = List.of(
                FabricEmiStack.of(recipe.value().output.variant(), recipe.value().output.amount() * 81)
        );
        this.recipe = recipe.value();
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return ModEmiPlugin.FERMENT_CATEGORY;
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
        return 98;
    }

    @Override
    public int getDisplayHeight() {
        return 20;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addFillingArrow(49, 1, 50 * recipe.getFermentTime()).tooltip((mx, my) -> {
            return List.of(TooltipComponent.of(ModEmiUtils.ordered(ModEmiUtils.translatable("emi.cooking.time", recipe.getFermentTime() / 20f))));
        });

        widgets.addTank(inputs.get(0), 1, 1, 18, 18, 250 * 81);
        widgets.addSlot(inputs.get(1), 25, 1);

        widgets.addTank(outputs.getFirst(), 79, 1, 18, 18, 250 * 81).recipeContext(this);
    }
}
