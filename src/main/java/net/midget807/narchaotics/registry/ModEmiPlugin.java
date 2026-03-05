package net.midget807.narchaotics.registry;

import dev.emi.emi.api.EmiInitRegistry;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.emi.DistillationEmiRecipe;
import net.midget807.narchaotics.emi.EvaporateEmiRecipe;
import net.midget807.narchaotics.emi.FilterEmiRecipe;
import net.midget807.narchaotics.recipe.AshRecipe;
import net.midget807.narchaotics.recipe.DissolveRecipe;
import net.midget807.narchaotics.recipe.DistillationRecipe;
import net.midget807.narchaotics.recipe.EvaporateRecipe;
import net.midget807.narchaotics.recipe.FermentRecipe;
import net.midget807.narchaotics.recipe.FilterRecipe;
import net.midget807.narchaotics.recipe.PhotoelectricExtractorRecipe;
import net.midget807.narchaotics.recipe.SeparateRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;

public class ModEmiPlugin implements EmiPlugin {
    public static final Identifier DISTILLATION_TEXTURE = NarchaoticsMain.id("textures/gui/container/distillation_menu.png");
    public static final Identifier FILTER_TEXTURE = NarchaoticsMain.id("textures/gui/container/filter_menu.png");
    public static final Identifier EVAPORATE_TEXTURE = NarchaoticsMain.id("textures/gui/container/evaporate_menu.png");
    public static final Identifier DISSOLVE_TEXTURE = NarchaoticsMain.id("textures/gui/container/dissolve_menu.png");
    public static final Identifier SEPARATE_TEXTURE = NarchaoticsMain.id("textures/gui/container/separate_menu.png");
    public static final Identifier PHOTOELECTRIC_TEXTURE = NarchaoticsMain.id("textures/gui/container/photoelectric_menu.png");
    public static final Identifier TANK_TEXTURE = NarchaoticsMain.id("textures/gui/container/tank_menu.png");
    public static final Identifier ICON_ATLAS = NarchaoticsMain.id("textures/gui/sprite/icon_atlas.png");
    public static final EmiStack DISTILLATION_WS = EmiStack.of(ModBlocks.DISTILLATION_WORKBENCH.asItem());
    public static final EmiStack FILTER_WS = EmiStack.of(ModBlocks.FILTER_WORKBENCH.asItem());
    public static final EmiStack EVAPORATE_WS = EmiStack.of(ModBlocks.EVAPORATE_WORKBENCH.asItem());
    public static final EmiStack DISSOLVE_WS = EmiStack.of(ModBlocks.DISSOLVE_WORKBENCH.asItem());
    public static final EmiStack SEPARATE_WS = EmiStack.of(ModBlocks.SEPARATE_WORKBENCH.asItem());
    public static final EmiStack PHOTOELECTRIC_WS = EmiStack.of(ModBlocks.PHOTOELECTRIC_EXTRACTOR.asItem());
    public static final EmiStack TANK_WS = EmiStack.of(ModBlocks.TANK.asItem());
    public static final EmiRecipeCategory DISTILLATION_CATEGORY = new EmiRecipeCategory(NarchaoticsMain.id("distillation"), DISTILLATION_WS, new EmiTexture(ICON_ATLAS, 0, 0, 16, 16));
    public static final EmiRecipeCategory FILTER_CATEGORY = new EmiRecipeCategory(NarchaoticsMain.id("filter"), FILTER_WS, new EmiTexture(ICON_ATLAS, 16, 0, 16, 16));
    public static final EmiRecipeCategory EVAPORATE_CATEGORY = new EmiRecipeCategory(NarchaoticsMain.id("evaporate"), EVAPORATE_WS, new EmiTexture(ICON_ATLAS, 0, 16, 16, 16));
    public static final EmiRecipeCategory DISSOLVE_CATEGORY = new EmiRecipeCategory(NarchaoticsMain.id("dissolve"), DISSOLVE_WS, new EmiTexture(ICON_ATLAS, 16, 16, 16, 16));
    public static final EmiRecipeCategory SEPARATE_CATEGORY = new EmiRecipeCategory(NarchaoticsMain.id("separate"), SEPARATE_WS, new EmiTexture(ICON_ATLAS, 32, 0, 16, 16));
    public static final EmiRecipeCategory PHOTOELECTRIC_CATEGORY = new EmiRecipeCategory(NarchaoticsMain.id("photoelectric"), PHOTOELECTRIC_WS, new EmiTexture(ICON_ATLAS, 32, 16, 16, 16));
    public static final EmiRecipeCategory ASH_CATEGORY = new EmiRecipeCategory(NarchaoticsMain.id("ash"), TANK_WS, new EmiTexture(ICON_ATLAS, 0, 32, 16, 16));
    public static final EmiRecipeCategory FERMENT_CATEGORY = new EmiRecipeCategory(NarchaoticsMain.id("ferment"), TANK_WS, new EmiTexture(ICON_ATLAS, 16, 32, 16, 16));

    @Override
    public void initialize(EmiInitRegistry registry) {
        EmiPlugin.super.initialize(registry);
    }

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(DISTILLATION_CATEGORY);
        registry.addCategory(FILTER_CATEGORY);
        registry.addCategory(EVAPORATE_CATEGORY);
        registry.addCategory(DISSOLVE_CATEGORY);
        registry.addCategory(SEPARATE_CATEGORY);
        registry.addCategory(PHOTOELECTRIC_CATEGORY);
        registry.addCategory(ASH_CATEGORY);
        registry.addCategory(FERMENT_CATEGORY);
        registry.addWorkstation(DISTILLATION_CATEGORY, DISTILLATION_WS);
        registry.addWorkstation(FILTER_CATEGORY, FILTER_WS);
        registry.addWorkstation(EVAPORATE_CATEGORY, EVAPORATE_WS);
        registry.addWorkstation(DISSOLVE_CATEGORY, DISSOLVE_WS);
        registry.addWorkstation(SEPARATE_CATEGORY, SEPARATE_WS);
        registry.addWorkstation(PHOTOELECTRIC_CATEGORY, PHOTOELECTRIC_WS);
        registry.addWorkstation(ASH_CATEGORY, TANK_WS);
        registry.addWorkstation(FERMENT_CATEGORY, TANK_WS);

        RecipeManager manager = registry.getRecipeManager();

        for (RecipeEntry<DistillationRecipe> recipe : manager.listAllOfType(ModRecipes.DISTILLATION_TYPE)) {
            registry.addRecipe(new DistillationEmiRecipe(recipe));
        }
        for (RecipeEntry<FilterRecipe> recipe : manager.listAllOfType(ModRecipes.FILTER_TYPE)) {
            registry.addRecipe(new FilterEmiRecipe(recipe));
        }
        for (RecipeEntry<EvaporateRecipe> recipe : manager.listAllOfType(ModRecipes.EVAPORATE_TYPE)) {
            registry.addRecipe(new EvaporateEmiRecipe(recipe));
        }/*
        for (RecipeEntry<DissolveRecipe> recipe : manager.listAllOfType(ModRecipes.DISSOLVE_TYPE)) {
            registry.addRecipe(new DissolveEmiRecipe(recipe));
        }
        for (RecipeEntry<SeparateRecipe> recipe : manager.listAllOfType(ModRecipes.SEPARATE_TYPE)) {
            registry.addRecipe(new SeparateEmiRecipe(recipe));
        }
        for (RecipeEntry<PhotoelectricExtractorRecipe> recipe : manager.listAllOfType(ModRecipes.PHOTOELECTRIC_TYPE)) {
            registry.addRecipe(new PhotoelectricEmiRecipe(recipe));
        }
        for (RecipeEntry<AshRecipe> recipe : manager.listAllOfType(ModRecipes.ASH_TYPE)) {
            registry.addRecipe(new AshEmiRecipe(recipe));
        }
        for (RecipeEntry<FermentRecipe> recipe : manager.listAllOfType(ModRecipes.FERMENT_TYPE)) {
            registry.addRecipe(new FermentEmiRecipe(recipe));
        }*/
    }
}
