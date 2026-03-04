package net.midget807.narchaotics.screen;

import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.util.ModScreenUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FluidTankScreen extends HandledScreen<FluidTankScreenHandler> {
    public static final Identifier MENU_TEXTURE = NarchaoticsMain.id("textures/gui/container/chemistry_workbench_menu.png");
    public static final Identifier DISTILLATION_TEXTURE = NarchaoticsMain.id("textures/gui/container/distillation_menu.png");
    public static final Identifier FILTER_TEXTURE = NarchaoticsMain.id("textures/gui/container/filter_menu.png");
    public static final Identifier EVAPORATE_TEXTURE = NarchaoticsMain.id("textures/gui/container/evaporate_menu.png");
    public static final Identifier DISSOLVE_TEXTURE = NarchaoticsMain.id("textures/gui/container/dissolve_menu.png");
    public static final Identifier PHOTOELECTRIC_TEXTURE = NarchaoticsMain.id("textures/gui/container/photoelectric_menu.png");
    public static final Identifier TANK_TEXTURE = NarchaoticsMain.id("textures/gui/container/tank_menu.png");
    public static final Identifier MENU_TAB_AVAILABLE_TEXTURE = NarchaoticsMain.id("textures/gui/container/menu_tab_available.png");
    public static final Identifier MENU_TAB_SELECTED_TEXTURE = NarchaoticsMain.id("textures/gui/container/menu_tab_selected.png");
    public static final Identifier DISTILLATION_TAB_UNAVAILABLE_TEXTURE = NarchaoticsMain.id("textures/gui/container/distillation_tab_unavailable.png");
    public static final Identifier DISTILLATION_TAB_AVAILABLE_TEXTURE = NarchaoticsMain.id("textures/gui/container/distillation_tab_available.png");
    public static final Identifier DISTILLATION_TAB_SELECTED_TEXTURE = NarchaoticsMain.id("textures/gui/container/distillation_tab_selected.png");
    public static final Identifier FILTER_TAB_UNAVAILABLE_TEXTURE = NarchaoticsMain.id("textures/gui/container/filter_tab_unavailable.png");
    public static final Identifier FILTER_TAB_AVAILABLE_TEXTURE = NarchaoticsMain.id("textures/gui/container/filter_tab_available.png");
    public static final Identifier FILTER_TAB_SELECTED_TEXTURE = NarchaoticsMain.id("textures/gui/container/filter_tab_selected.png");
    public static final Identifier EVAPORATE_TAB_UNAVAILABLE_TEXTURE = NarchaoticsMain.id("textures/gui/container/evaporate_tab_unavailable.png");
    public static final Identifier EVAPORATE_TAB_AVAILABLE_TEXTURE = NarchaoticsMain.id("textures/gui/container/evaporate_tab_available.png");
    public static final Identifier EVAPORATE_TAB_SELECTED_TEXTURE = NarchaoticsMain.id("textures/gui/container/evaporate_tab_selected.png");
    public static final Identifier DISSOLVE_TAB_UNAVAILABLE_TEXTURE = NarchaoticsMain.id("textures/gui/container/dissolve_tab_unavailable.png");
    public static final Identifier DISSOLVE_TAB_AVAILABLE_TEXTURE = NarchaoticsMain.id("textures/gui/container/dissolve_tab_available.png");
    public static final Identifier DISSOLVE_TAB_SELECTED_TEXTURE = NarchaoticsMain.id("textures/gui/container/dissolve_tab_selected.png");
    public static final Identifier DISTILLATION_ARROW = NarchaoticsMain.id("textures/gui/container/distillation_progress_arrow.png");
    public static final Identifier FILTER_ARROW = NarchaoticsMain.id("textures/gui/container/filter_progress_arrow.png");
    public static final Identifier EVAPORATE_ARROW = NarchaoticsMain.id("textures/gui/container/evaporate_progress_arrow.png");
    public static final Identifier DISSOLVE_ARROW = NarchaoticsMain.id("textures/gui/container/dissolve_progress_arrow.png");
    public static final Identifier FILTER_ARROW_H = NarchaoticsMain.id("textures/gui/container/filter_progress_arrow_horizontal.png");
    public static final Identifier FILTER_ARROW_V = NarchaoticsMain.id("textures/gui/container/filter_progress_arrow_vertical.png");
    public static final Identifier FLAME = NarchaoticsMain.id("textures/gui/container/burner_level.png");
    public static final Identifier FLAME_SOUL = NarchaoticsMain.id("textures/gui/container/burner_level_soul.png");
    public static final Identifier CATALYST = NarchaoticsMain.id("textures/gui/container/catalyst.png");
    public static final Identifier SUNLIGHT = NarchaoticsMain.id("textures/gui/container/sunlight.png");

    public static final int BACKGROUND_WIDTH = 212;
    public static final int BACKGROUND_HEIGHT = 222;
    public static final int BACKGROUND_X_OFFSET = 29;
    public static final int TAB_WIDTH = 32;
    public static final int TAB_HEIGHT = 26;
    public static final int TAB_OFFSET = 4;


    public FluidTankScreen(FluidTankScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = BACKGROUND_WIDTH;
        this.backgroundHeight = BACKGROUND_HEIGHT;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }


    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - BACKGROUND_WIDTH) / 2;
        int y = (height - BACKGROUND_HEIGHT) / 2;

        context.drawTexture(TANK_TEXTURE, x, y, 0, BACKGROUND_X_OFFSET, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, 256, 256);

        renderProgressArrow(context, x, y);

    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (this.handler.isCooking()) {
            context.drawTexture(EVAPORATE_ARROW, x + 93, y + 63, 0, 0, 0, this.handler.getScaledArrowProgress(), 16, 32, 16);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawFluid(context, handler.blockEntity.reactantFluidStorage1.variant, 13, 49, 46, ModScreenUtil.getHeightForVolume(handler.blockEntity.reactantFluidStorage1, 46), 46);
        drawFluid(context, handler.blockEntity.productFluidStorage1.variant, 155, 61, 22, ModScreenUtil.getHeightForVolume(handler.blockEntity.productFluidStorage1, 22), 22);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @SuppressWarnings("deprecation")
    public void drawFluid(DrawContext context, FluidVariant fluidVariant, int dx, int dy, int width, int height, int maxHeight) {
        if (fluidVariant.isBlank()) return;
        FluidRenderHandler fluidRenderHandler = FluidRenderHandlerRegistry.INSTANCE.get(fluidVariant.getFluid());
        if (fluidRenderHandler == null) return;
        int color = fluidRenderHandler.getFluidColor(null, null, fluidVariant.getFluid().getDefaultState());

        float r = (color >> 16 & 0xFF) / 255f;
        float g = (color >> 8 & 0xFF) / 255f;
        float b = (color & 0xFF) / 255f;

        Sprite sprite = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).apply(Identifier.ofVanilla("block/water_still"));

        int x = (this.width - BACKGROUND_WIDTH) / 2;
        int y = (this.height - BACKGROUND_HEIGHT) / 2;
        int yOffset = maxHeight - height;

        context.drawSprite(x + dx , y + dy + yOffset, 1, width, height, sprite, r, g, b, 0.9f);
    }
}
