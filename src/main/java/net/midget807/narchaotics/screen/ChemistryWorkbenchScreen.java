package net.midget807.narchaotics.screen;

import net.midget807.narchaotics.NarchaoticsMain;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.midget807.narchaotics.block.ChemistryWorkbenchBlock.Modes;

public class ChemistryWorkbenchScreen extends HandledScreen<ChemistryWorkbenchScreenHandler> {
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

    private Modes selectedTab = Modes.MENU;
    @Nullable
    private List<Slot> slots;

    public ChemistryWorkbenchScreen(ChemistryWorkbenchScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = BACKGROUND_WIDTH;
        this.backgroundHeight = BACKGROUND_HEIGHT;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }


    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - BACKGROUND_WIDTH) / 2;
        int y = (height - BACKGROUND_HEIGHT) / 2;

        context.drawTexture(this.getTabTexture(handler, Modes.MENU), x - TAB_WIDTH + TAB_OFFSET, y + TAB_OFFSET, this.getTabTexture(handler, Modes.MENU).toString().contains("selected") ? 1 : 0, 0, 0, TAB_WIDTH, TAB_HEIGHT, 32, 26);
        context.drawTexture(this.getTabTexture(handler, Modes.DISTILLATION), x - TAB_WIDTH + TAB_OFFSET, y + TAB_OFFSET + (TAB_HEIGHT + 1), this.getTabTexture(handler, Modes.DISTILLATION).toString().contains("selected") ? 1 : 0, 0, 0, TAB_WIDTH, TAB_HEIGHT, 32, 26);
        context.drawTexture(this.getTabTexture(handler, Modes.FILTER), x - TAB_WIDTH + TAB_OFFSET, y + TAB_OFFSET + (TAB_HEIGHT + 1) * 2, this.getTabTexture(handler, Modes.FILTER).toString().contains("selected") ? 1 : 0, 0, 0, TAB_WIDTH, TAB_HEIGHT, 32, 26);
        context.drawTexture(this.getTabTexture(handler, Modes.EVAPORATE), x - TAB_WIDTH + TAB_OFFSET, y + TAB_OFFSET + (TAB_HEIGHT + 1) * 3, this.getTabTexture(handler, Modes.EVAPORATE).toString().contains("selected") ? 1 : 0, 0, 0, TAB_WIDTH, TAB_HEIGHT, 32, 26);
        context.drawTexture(this.getTabTexture(handler, Modes.DISSOLVE), x - TAB_WIDTH + TAB_OFFSET, y + TAB_OFFSET + (TAB_HEIGHT + 1) * 4, this.getTabTexture(handler, Modes.DISSOLVE).toString().contains("selected") ? 1 : 0, 0, 0, TAB_WIDTH, TAB_HEIGHT, 32, 26);

        context.drawTexture(this.getMainTexture(), x, y, 0, BACKGROUND_X_OFFSET, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, 256, 256);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        super.drawForeground(context, mouseX, mouseY);
    }

    private Identifier getMainTexture() {
        return switch (this.selectedTab) {
            case DISTILLATION -> DISTILLATION_TEXTURE;
            case FILTER -> FILTER_TEXTURE;
            case EVAPORATE -> EVAPORATE_TEXTURE;
            case DISSOLVE -> DISSOLVE_TEXTURE;
            default -> MENU_TEXTURE;
        };
    }

    private Identifier getTabTexture(ChemistryWorkbenchScreenHandler inventory, Modes modes) {
        return switch (modes) {
            case MENU:
                yield selectedTab == Modes.MENU ? MENU_TAB_SELECTED_TEXTURE : MENU_TAB_AVAILABLE_TEXTURE;
            case DISTILLATION:
                if (inventory.modePropertyDelegate.get(0) == 1) {
                    yield selectedTab == Modes.DISTILLATION ? DISTILLATION_TAB_SELECTED_TEXTURE : DISTILLATION_TAB_AVAILABLE_TEXTURE;
                } else {
                    yield DISTILLATION_TAB_UNAVAILABLE_TEXTURE;
                }
            case FILTER:
                if (inventory.modePropertyDelegate.get(1) == 1) {
                    yield selectedTab == Modes.FILTER ? FILTER_TAB_SELECTED_TEXTURE : FILTER_TAB_AVAILABLE_TEXTURE;
                } else {
                    yield FILTER_TAB_UNAVAILABLE_TEXTURE;
                }
            case EVAPORATE:
                if (inventory.modePropertyDelegate.get(2) == 1) {
                    yield selectedTab == Modes.EVAPORATE ? EVAPORATE_TAB_SELECTED_TEXTURE : EVAPORATE_TAB_AVAILABLE_TEXTURE;
                } else {
                    yield EVAPORATE_TAB_UNAVAILABLE_TEXTURE;
                }
            case DISSOLVE:
                if (inventory.modePropertyDelegate.get(3) == 1) {
                    yield selectedTab == Modes.DISSOLVE ? DISSOLVE_TAB_SELECTED_TEXTURE : DISSOLVE_TAB_AVAILABLE_TEXTURE;
                } else {
                    yield DISSOLVE_TAB_UNAVAILABLE_TEXTURE;
                }
        };
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            double d = mouseX - this.x;
            double e = mouseY - this.y;

            for (Modes modes : Modes.getModesToDisplay()) {
                if (isClickInTab(modes, d, e)) {
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            double d = mouseX - this.x;
            double e = mouseY - this.y;

            for (Modes modes : Modes.getModesToDisplay()) {
                if (modeIsAvailable(modes)) {
                    if (this.isClickInTab(modes, d, e)) {
                        this.setSelectedTab(modes);
                        return true;
                    }
                }
            }
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    private boolean modeIsAvailable(Modes mode) {
        if (mode == Modes.MENU) {
            return true;
        } else {
            return this.handler.modePropertyDelegate.get(mode.getAvailabilityIndex(mode)) == 1;
        }
    }

    private void setSelectedTab(Modes newMode) {
        Modes currentModes = selectedTab;
        selectedTab = newMode;
        if (selectedTab != currentModes) {
            switch (selectedTab) {
                case MENU: {
                    //ClientPlayNetworking.send(new OpenChemistryScreenPayload(this.handler.blockEntity.getPos(), 0));
                    this.handler.currentScreen = 0;
                }
                case DISTILLATION: {
                    if (this.handler.modePropertyDelegate.get(0) == 1) {
                        //ClientPlayNetworking.send(new OpenChemistryScreenPayload(this.handler.blockEntity.getPos(), 1));
                        this.handler.currentScreen = 1;
                    }

                }
                case FILTER: {
                    if (this.handler.modePropertyDelegate.get(1) == 1) {
                        //ClientPlayNetworking.send(new OpenChemistryScreenPayload(this.handler.blockEntity.getPos(), 2));
                        this.handler.currentScreen = 2;
                    }

                }
                case EVAPORATE: {
                    if (this.handler.modePropertyDelegate.get(2) == 1) {
                        //ClientPlayNetworking.send(new OpenChemistryScreenPayload(this.handler.blockEntity.getPos(), 3));
                        this.handler.currentScreen = 3;
                    }
                }
                case DISSOLVE: {
                    if (this.handler.modePropertyDelegate.get(3) == 1) {
                        //ClientPlayNetworking.send(new OpenChemistryScreenPayload(this.handler.blockEntity.getPos(), 4));
                        this.handler.currentScreen = 4;
                    }
                }
            }
        }
        this.cursorDragSlots.clear();
        this.endTouchDrag();
    }

    private boolean isClickInTab(Modes modes, double mouseX, double mouseY) {
        int i = this.getTabX(modes);
        int j = this.getTabY(modes);
        return mouseX >= i && mouseX <= i + TAB_WIDTH - TAB_OFFSET && mouseY >= j && mouseY <= j + TAB_HEIGHT;
    }

    public int getTabX(Modes modes) {
        return -TAB_WIDTH + TAB_OFFSET;
    }

    public int getTabY(Modes modes) {
        int i = modes.getRow(modes);
        int k = 27 * i;
        return k;
    }
    public boolean renderTabTooltipIfHovered(DrawContext context, Modes mode, int mouseX, int mouseY) {
        int i = this.getTabX(mode);
        int j = this.getTabY(mode);
        if (this.isPointWithinBounds(i + 3, j + 3, 21, 27, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, mode.getDisplayName(), mouseX, mouseY);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        for (Modes modes : Modes.getModesToDisplay()) {
            if (this.renderTabTooltipIfHovered(context, modes, mouseX, mouseY)) {
                break;
            }
        }
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
