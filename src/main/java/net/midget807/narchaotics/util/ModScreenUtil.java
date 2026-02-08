package net.midget807.narchaotics.util;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.midget807.narchaotics.datagen.ModItemTagProvider;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class ModScreenUtil {
    public static int getHeightForVolume(SingleVariantStorage<FluidVariant> tank, int maxHeight) {
        return (int) (Math.floor((double) tank.amount / tank.getCapacity()) * maxHeight);
    }

    public static class FluidInputSlot extends Slot {
        public FluidInputSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return stack.isIn(ModItemTagProvider.FLUID_INPUT_ITEMS) || stack.isIn(ModItemTagProvider.FLUID_REMOVE_ITEMS);
        }
    }

    public static class FluidOutputSlot extends Slot {
        public FluidOutputSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return false;
        }
    }

    public static class OutputSlot extends Slot {
        public OutputSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return false;
        }
    }
}
