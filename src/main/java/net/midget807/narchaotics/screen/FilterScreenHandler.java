package net.midget807.narchaotics.screen;

import net.midget807.narchaotics.block.entity.FilterWorkbenchBlockEntity;
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

import static net.midget807.narchaotics.block.entity.DistillationWorkbenchBlockEntity.MAX_PROGRESS_DELEGATE_INDEX;
import static net.midget807.narchaotics.block.entity.DistillationWorkbenchBlockEntity.PROGRESS_TIME_DELEGATE_INDEX;
import static net.midget807.narchaotics.block.entity.FilterWorkbenchBlockEntity.*;
import static net.midget807.narchaotics.util.ModScreenUtil.*;

public class FilterScreenHandler extends ScreenHandler {
    public Inventory inventory;
    public final PropertyDelegate propertyDelegate;
    public final FilterWorkbenchBlockEntity blockEntity;

    public FilterScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getEntityWorld().getBlockEntity(pos), new ArrayPropertyDelegate(7));
    }

    public FilterScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.FILTER_WORKBENCH, syncId);
        checkDataCount(propertyDelegate, 5);
        this.inventory = (Inventory) blockEntity;
        this.blockEntity = (FilterWorkbenchBlockEntity) blockEntity;
        this.propertyDelegate = propertyDelegate;

        this.addSlot(new FluidInputSlot(inventory, FLUID_INPUT_INDICES[0], 35, 53));
        this.addSlot(new FluidInputSlot(inventory, FLUID_INPUT_INDICES[1], 161, 53));

        this.addSlot(new OutputSlot(inventory, ITEM_OUTPUT_INDICES[0], 98, 100));

        this.addSlot(new FluidOutputSlot(inventory, FLUID_OUTPUT_INDICES[0], 35, 75));
        this.addSlot(new FluidOutputSlot(inventory, FLUID_OUTPUT_INDICES[1], 161, 75));

        this.addPlayerInventory(playerInventory);
        this.addPlayerHotbar(playerInventory);

        this.addProperties(propertyDelegate);
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

    public int getScaledArrowProgressH() {
        int progress = this.propertyDelegate.get(PROGRESS_TIME_DELEGATE_INDEX);
        int maxProgress = this.propertyDelegate.get(MAX_PROGRESS_DELEGATE_INDEX);
        int arrowPixelSize = 38;

        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }
    public int getScaledArrowProgressV() {
        int progress = this.propertyDelegate.get(PROGRESS_TIME_DELEGATE_INDEX);
        int maxProgress = this.propertyDelegate.get(MAX_PROGRESS_DELEGATE_INDEX);
        int arrowPixelSize = 33;

        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }

    public boolean isCooking() {
        return this.propertyDelegate.get(PROGRESS_TIME_DELEGATE_INDEX) > 0;
    }
}
