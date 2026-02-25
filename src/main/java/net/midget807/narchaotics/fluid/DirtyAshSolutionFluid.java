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

public abstract class DirtyAshSolutionFluid extends AbstractModFluid {
    @Override
    public Fluid getStill() {
        return ModFluids.DIRTY_ASH_SOLUTION;
    }

    @Override
    public Fluid getFlowing() {
        return ModFluids.DIRTY_ASH_SOLUTION_FLOWING;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.DIRTY_ASH_SOLUTION_BUCKET;
    }

    @Override
    public Item narchaotics$getConicalFlaskItem() {
        return ModItems.DIRTY_ASH_SOLUTION_CONICAL_FLASK;
    }

    @Override
    public Item narchaotics$getRoundFlaskItem() {
        return ModItems.DIRTY_ASH_SOLUTION_ROUND_FLASK;
    }

    @Override
    public Item narchaotics$getBeakerItem() {
        return ModItems.DIRTY_ASH_SOLUTION_BEAKER;
    }

    @Override
    public Item narchaotics$getTestTubeItem() {
        return ModItems.DIRTY_ASH_SOLUTION_TEST_TUBE;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.DIRTY_ASH_SOLUTION.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
    }

    public static class Flowing extends DirtyAshSolutionFluid {
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

    public static class Still extends DirtyAshSolutionFluid {
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
