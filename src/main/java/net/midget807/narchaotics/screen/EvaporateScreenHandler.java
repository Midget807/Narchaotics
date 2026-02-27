package net.midget807.narchaotics.screen;

import net.midget807.narchaotics.block.entity.EvaporateWorkbenchBlockEntity;
import net.midget807.narchaotics.registry.ModScreenHandlers;
import net.midget807.narchaotics.util.ModScreenUtil;
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

import static net.midget807.narchaotics.block.entity.EvaporateWorkbenchBlockEntity.*;
import static net.midget807.narchaotics.block.entity.FilterWorkbenchBlockEntity.FLUID_OUTPUT_INDICES;
import static net.midget807.narchaotics.util.ModScreenUtil.*;

public class EvaporateScreenHandler extends ScreenHandler {
    public Inventory inventory;
    public final PropertyDelegate propertyDelegate;
    public final EvaporateWorkbenchBlockEntity blockEntity;

    public EvaporateScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getEntityWorld().getBlockEntity(pos), new ArrayPropertyDelegate(7));
    }

    public EvaporateScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.EVAPORATE_WORKBENCH, syncId);
        checkDataCount(propertyDelegate, 4);
        this.inventory = (Inventory) blockEntity;
        this.blockEntity = (EvaporateWorkbenchBlockEntity) blockEntity;
        this.propertyDelegate = propertyDelegate;

        this.addSlot(new Slot(inventory, FLUID_INPUT_INDICES[0], 35, 53));
        this.addSlot(new Slot(inventory, FUEL_INPUT_INDEX, 98, 100));

        this.addSlot(new OutputSlot(inventory, FLUID_OUTPUT_INDICES[0], 139, 91));

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
}
