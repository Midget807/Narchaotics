package net.midget807.narchaotics.screen;

import net.midget807.narchaotics.NarchaoticsMain;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DistillationWorkbenchScreen extends HandledScreen<DistillationScreenHandler> {
    public static final Identifier MENU_TEXTURE = NarchaoticsMain.id("textures/gui/container/chemistry_workbench_menu.png");
    public static final Identifier DISTILLATION_TEXTURE = NarchaoticsMain.id("textures/gui/container/distillation_menu.png");
    public static final Identifier FILTER_TEXTURE = NarchaoticsMain.id("textures/gui/container/filter_menu.png");
    public static final Identifier EVAPORATE_TEXTURE = NarchaoticsMain.id("textures/gui/container/evaporate_menu.png");
    public static final Identifier DISSOLVE_TEXTURE = NarchaoticsMain.id("textures/gui/container/dissolve_menu.png");
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

    public static final int BACKGROUND_WIDTH = 212;
    public static final int BACKGROUND_HEIGHT = 222;
    public static final int BACKGROUND_X_OFFSET = 29;
    public static final int TAB_WIDTH = 32;
    public static final int TAB_HEIGHT = 26;
    public static final int TAB_OFFSET = 4;


    public DistillationWorkbenchScreen(DistillationScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = BACKGROUND_WIDTH;
        this.backgroundHeight = BACKGROUND_HEIGHT;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }


    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - BACKGROUND_WIDTH) / 2;
        int y = (height - BACKGROUND_HEIGHT) / 2;

        context.drawTexture(DISTILLATION_TEXTURE, x, y, 0, BACKGROUND_X_OFFSET, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, 256, 256);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
