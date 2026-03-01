package net.midget807.narchaotics.block;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.entity.ChemistryWorkbenchBlockEntity;
import net.midget807.narchaotics.block.entity.DistillationWorkbenchBlockEntity;
import net.midget807.narchaotics.registry.ModBlockEntities;
import net.midget807.narchaotics.screen.DistillationScreenHandler;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.DoubleInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DistillationWorkbenchBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final MapCodec<DistillationWorkbenchBlock> CODEC = createCodec(DistillationWorkbenchBlock::new);
    public static final EnumProperty<Part> PART = EnumProperty.of("part", Part.class);
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final VoxelShape BASE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);
    public static final VoxelShape BLOCK = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    private static final DoubleBlockProperties.PropertyRetriever<DistillationWorkbenchBlockEntity, Optional<Inventory>> INVENTORY_RETRIEVER = new DoubleBlockProperties.PropertyRetriever<DistillationWorkbenchBlockEntity, Optional<Inventory>>() {
        @Override
        public Optional<Inventory> getFromBoth(DistillationWorkbenchBlockEntity first, DistillationWorkbenchBlockEntity second) {
            return Optional.of(new DoubleInventory(first, second));
        }

        @Override
        public Optional<Inventory> getFrom(DistillationWorkbenchBlockEntity single) {
            return Optional.of(single);
        }

        @Override
        public Optional<Inventory> getFallback() {
            return Optional.empty();
        }
    };


    public DistillationWorkbenchBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return state.get(PART) == Part.MAIN ? BlockRenderType.MODEL : BlockRenderType.INVISIBLE;
    }

    @Override
    protected boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    protected float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0f;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return state.get(PART) == Part.MAIN ? new DistillationWorkbenchBlockEntity(pos, state) : null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
        builder.add(PART);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockPos mainPos = state.get(PART) == Part.MAIN
                ? pos
                : pos.offset(state.get(FACING).rotateYCounterclockwise());

        if (!world.isClient) {
            NamedScreenHandlerFactory namedScreenHandlerFactory = (DistillationWorkbenchBlockEntity) world.getBlockEntity(mainPos);
            if (namedScreenHandlerFactory != null) {
                player.openHandledScreen(namedScreenHandlerFactory);
            }
        }
        return ItemActionResult.SUCCESS;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        ItemScatterer.onStateReplaced(state, newState, world, pos);
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    protected boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getHorizontalPlayerFacing();
        BlockPos otherPos = ctx.getBlockPos().offset(direction.rotateYClockwise());
        World world = ctx.getWorld();
        return world.getBlockState(otherPos).canReplace(ctx) && world.getWorldBorder().contains(otherPos) ? this.getDefaultState().with(FACING, direction).with(PART, Part.MAIN) : null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        Direction direction = placer.getHorizontalFacing();
        BlockPos otherPos = pos.offset(direction.rotateYClockwise());
        world.setBlockState(otherPos, this.getDefaultState().with(FACING, direction).with(PART, Part.OTHER));
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        Part part = state.get(PART);
        Direction direction = state.get(FACING);
        BlockPos otherPos = part == Part.MAIN
                ? pos.offset(direction.rotateYClockwise())
                : pos.offset(direction.rotateYCounterclockwise());
        world.breakBlock(otherPos, false);
        super.onBreak(world, pos, state, player);
        return state;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient) {
            return null;
        }
        return validateTicker(type, ModBlockEntities.DISTILLATION_WORKBENCH, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    public static enum Part implements StringIdentifiable {
        MAIN,
        OTHER;

        @Override
        public String asString() {
            return name().toLowerCase();
        }
    }
}
