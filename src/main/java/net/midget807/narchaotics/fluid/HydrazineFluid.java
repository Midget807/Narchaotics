package net.midget807.narchaotics.fluid;

import net.midget807.narchaotics.registry.ModBlocks;
import net.midget807.narchaotics.registry.ModFluids;
import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;

public abstract class HydrazineFluid extends AbstractModFluid {
    @Override
    public Fluid getStill() {
        return ModFluids.HYDRAZINE;
    }

    @Override
    public Fluid getFlowing() {
        return ModFluids.HYDRAZINE_FLOWING;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.HYDRAZINE_BUCKET;
    }

    @Override
    public Item narchaotics$getConicalFlaskItem() {
        return ModItems.HYDRAZINE_CONICAL_FLASK;
    }

    @Override
    public Item narchaotics$getRoundFlaskItem() {
        return ModItems.HYDRAZINE_ROUND_FLASK;
    }

    @Override
    public Item narchaotics$getBeakerItem() {
        return ModItems.HYDRAZINE_BEAKER;
    }

    @Override
    public Item narchaotics$getTestTubeItem() {
        return ModItems.HYDRAZINE_TEST_TUBE;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.HYDRAZINE.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
    }

    public static class Flowing extends HydrazineFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }
    }

    public static class Still extends HydrazineFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
