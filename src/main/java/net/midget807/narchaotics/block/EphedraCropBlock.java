package net.midget807.narchaotics.block;

import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class EphedraCropBlock extends CropBlock {
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 4.0, 10.0),
            Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 11.0, 13.0),
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 13.0, 14.0),
            Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0),
            Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0),
            Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0),
            Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)
    };

    public EphedraCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.EPHEDRA_SEEDS;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[getAge(state)];
    }
}
