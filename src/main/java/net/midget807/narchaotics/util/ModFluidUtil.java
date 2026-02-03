package net.midget807.narchaotics.util;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.entity.BlockEntity;

public class ModFluidUtil {
    public static SingleVariantStorage<FluidVariant> createTank (BlockEntity blockEntity) {
        return createTank(1, blockEntity);
    }
    public static SingleVariantStorage<FluidVariant> createTank(int volume, BlockEntity blockEntity) {
        return new SingleVariantStorage<FluidVariant>() {
            @Override
            protected FluidVariant getBlankVariant() {
                return FluidVariant.blank();
            }

            @Override
            protected long getCapacity(FluidVariant variant) {
                return (FluidConstants.BUCKET / 81) * volume;
            }

            @Override
            protected void onFinalCommit() {
                blockEntity.markDirty();
                blockEntity.getWorld().updateListeners(blockEntity.getPos(), blockEntity.getCachedState(), blockEntity.getCachedState(), 3);
            }
        };
    }
}
