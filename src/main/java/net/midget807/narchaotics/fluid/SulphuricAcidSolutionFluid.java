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

public abstract class SulphuricAcidSolutionFluid extends AbstractModFluid {
    @Override
    public Fluid getStill() {
        return ModFluids.SULPHURIC_ACID_SOLUTION;
    }

    @Override
    public Fluid getFlowing() {
        return ModFluids.SULPHURIC_ACID_SOLUTION_FLOWING;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.SULPHURIC_ACID_SOLUTION_BUCKET;
    }

    @Override
    public Item narchaotics$getConicalFlaskItem() {
        return ModItems.SULPHURIC_ACID_SOLUTION_CONICAL_FLASK;
    }

    @Override
    public Item narchaotics$getRoundFlaskItem() {
        return ModItems.SULPHURIC_ACID_SOLUTION_ROUND_FLASK;
    }

    @Override
    public Item narchaotics$getBeakerItem() {
        return ModItems.SULPHURIC_ACID_SOLUTION_BEAKER;
    }

    @Override
    public Item narchaotics$getTestTubeItem() {
        return ModItems.SULPHURIC_ACID_SOLUTION_TEST_TUBE;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.SULPHURIC_ACID_SOLUTION.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
    }

    public static class Flowing extends SulphuricAcidSolutionFluid {
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

    public static class Still extends SulphuricAcidSolutionFluid {
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
