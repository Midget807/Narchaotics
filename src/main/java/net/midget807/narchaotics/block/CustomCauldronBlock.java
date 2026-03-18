package net.midget807.narchaotics.block;

import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public abstract class CustomCauldronBlock extends AbstractCauldronBlock {
    /**
     * Constructs a cauldron block.
     *
     * <p>The behavior map must match {@link CauldronBehavior#createMap} by providing
     * a nonnull value for <em>all</em> items.
     *
     * @param settings
     * @param behaviorMap
     */

    public CustomCauldronBlock(Settings settings, CauldronBehavior.CauldronBehaviorMap behaviorMap) {
        super(settings, behaviorMap);
    }


    @Override
    public boolean isFull(BlockState state) {
        return true;
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return 3;
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(Items.CAULDRON);
    }
}
