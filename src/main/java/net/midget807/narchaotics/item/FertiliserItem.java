package net.midget807.narchaotics.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

public class FertiliserItem extends BoneMealItem {
    public FertiliserItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockPos blockPos2 = blockPos.offset(context.getSide());
        if (useOnFertilizable(context.getStack(), world, blockPos)) {
            if (!world.isClient) {
                context.getPlayer().emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
                world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, blockPos, 15);
            }

            return ActionResult.success(world.isClient);
        } else {
            BlockState blockState = world.getBlockState(blockPos);
            boolean bl = blockState.isSideSolidFullSquare(world, blockPos, context.getSide());
            if (bl && useOnGround(context.getStack(), world, blockPos2, context.getSide())) {
                if (!world.isClient) {
                    context.getPlayer().emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
                    world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, blockPos2, 15);
                }

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        }
    }

    public static boolean useOnFertilizable(ItemStack stack, World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.getBlock() instanceof Fertilizable fertilizable && fertilizable.isFertilizable(world, pos, blockState)) {
            if (world instanceof ServerWorld) {
                if (blockState.getBlock() instanceof CropBlock cropBlock) {
                    int stop = 50;
                    while (!cropBlock.isMature(blockState) && stop-- > 0) {
                        if (!cropBlock.canGrow(world, world.random, pos, blockState)) break;

                        cropBlock.grow((ServerWorld) world, world.random, pos, blockState);
                        blockState = world.getBlockState(pos); // refresh state
                    }

                    stack.decrement(1);
                } else if (blockState.getBlock() instanceof SaplingBlock saplingBlock) {
                    saplingBlock.generate((ServerWorld) world, pos, blockState, world.random);
                    stack.decrement(1);
                } else if (blockState.getBlock() instanceof PlantBlock) {
                    if (blockState.hasRandomTicks()) {
                        for (int i = 0; i < 300; i++) {
                            blockState.randomTick((ServerWorld) world, pos, world.random);
                            blockState = world.getBlockState(pos);
                        }
                        stack.decrement(1);
                    }
                } else if (blockState.getBlock() instanceof Fertilizable) {
                    if (blockState.hasRandomTicks()) {
                        for (int i = 0; i < 300; i++) {
                            blockState.randomTick((ServerWorld) world, pos, world.random);
                            blockState = world.getBlockState(pos);
                        }
                        stack.decrement(1);
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
