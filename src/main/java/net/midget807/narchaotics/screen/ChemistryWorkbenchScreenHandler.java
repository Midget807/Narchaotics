package net.midget807.narchaotics.screen;

import net.midget807.narchaotics.block.entity.ChemistryWorkbenchBlockEntity;
import net.midget807.narchaotics.registry.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class ChemistryWorkbenchScreenHandler extends ScreenHandler {
    public Inventory inventory;
    public final PropertyDelegate timePropertyDelegate;
    public final PropertyDelegate modePropertyDelegate;
    public final ChemistryWorkbenchBlockEntity blockEntity;
    public int currentScreen = 0;

    public ChemistryWorkbenchScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getEntityWorld().getBlockEntity(pos), new ArrayPropertyDelegate(4), new ArrayPropertyDelegate(4));
    }

    public ChemistryWorkbenchScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate timePropertyDelegate, PropertyDelegate modePropertyDelegate) {
        super(ModScreenHandlers.CHEMISTRY_WORKBENCH, syncId);
        //checkSize((Inventory) blockEntity, 32);
        checkDataCount(timePropertyDelegate, 4);
        checkDataCount(modePropertyDelegate, 4);
        this.inventory = (Inventory) blockEntity;
        this.blockEntity = (ChemistryWorkbenchBlockEntity) blockEntity;
        this.timePropertyDelegate = timePropertyDelegate;
        this.modePropertyDelegate = modePropertyDelegate;

        buildSlots();

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(timePropertyDelegate);
        addProperties(modePropertyDelegate);
    }

    public void buildSlots() {
        this.slots.clear();
        //this.trackedStacks.clear();
        switch (currentScreen) {
            case 1 -> buildDistillationSlots();
            case 2 -> buildFilterSlots();
            case 3 -> buildEvaporateSlots();
            case 4 -> buildDissolveSlots();
            default -> buildMenuSlots();
        }
    }

    public void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 26 + l * 18, 140 + i * 18));
            }
        }
    }

    public void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 26 + i * 18, 198));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        this.modePropertyDelegate.set(ChemistryWorkbenchBlockEntity.DISTILLATION_AVAILABLE_DELEGATE_INDEX, this.blockEntity.modePropertyDelegate.get(0));
        this.modePropertyDelegate.set(ChemistryWorkbenchBlockEntity.FILTER_AVAILABLE_DELEGATE_INDEX, this.blockEntity.modePropertyDelegate.get(1));
        this.modePropertyDelegate.set(ChemistryWorkbenchBlockEntity.EVAPORATE_AVAILABLE_DELEGATE_INDEX, this.blockEntity.modePropertyDelegate.get(2));
        this.modePropertyDelegate.set(ChemistryWorkbenchBlockEntity.DISSOLVE_AVAILABLE_DELEGATE_INDEX, this.blockEntity.modePropertyDelegate.get(3));
        super.onContentChanged(inventory);
    }

    public void buildMenuSlots() {
        this.addSlot(new Slot(inventory, 0, 26, 100)); /** Bottom Left */
        this.addSlot(new Slot(inventory, 1, 26, 64));
        this.addSlot(new Slot(inventory, 2, 26, 28)); /** Top Left */
        this.addSlot(new Slot(inventory, 3, 98, 100));
        this.addSlot(new Slot(inventory, 4, 98, 64));
        this.addSlot(new Slot(inventory, 5, 170, 100));
    }

    public void buildDistillationSlots() {

    }

    public void buildFilterSlots() {

    }

    public void buildEvaporateSlots() {
        this.addSlot(new Slot(inventory, ChemistryWorkbenchBlockEntity.EVAPORATE_INPUT_INDICES[0], 62, 100)); /** Bottom Left */
        this.addSlot(new Slot(inventory, ChemistryWorkbenchBlockEntity.EVAPORATE_OUTPUT_INDICES[0], 62, 28));
        this.addSlot(new Slot(inventory, ChemistryWorkbenchBlockEntity.EVAPORATE_OUTPUT_INDICES[1], 134, 64)); /** Middle Right */
    }

    public void buildDissolveSlots() {
    }
}
