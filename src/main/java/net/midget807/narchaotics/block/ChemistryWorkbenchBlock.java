package net.midget807.narchaotics.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.entity.ChemistryWorkbenchBlockEntity;
import net.midget807.narchaotics.registry.ModBlockEntities;
import net.midget807.narchaotics.util.ModProperties;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChemistryWorkbenchBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final MapCodec<ChemistryWorkbenchBlock> CODEC = createCodec(ChemistryWorkbenchBlock::new);
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final EnumProperty<ChemistryWorkbenchType> BENCH_TYPE = ModProperties.CHEMISTRY_WORKBENCH_TYPE;
    public static final BooleanProperty[] COMPONENT_PROPERTIES = new BooleanProperty[]{ModProperties.HAS_BURNER, ModProperties.HAS_FLASK, ModProperties.HAS_BEAKER, ModProperties.HAS_FILTER, ModProperties.HAS_STAND, ModProperties.HAS_CONDENSER, ModProperties.HAS_CONDENSER_OUTPUT};
    public static final VoxelShape BASE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);
    public static final VoxelShape BURNER = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 4.0, 11.0);
    public static final VoxelShape CONICAL_FLASK = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 8.0, 11.0);
    public static final VoxelShape BEAKER = Block.createCuboidShape(5.5, 0.0, 5.5, 10.5, 6.0, 10.5);
    public static final VoxelShape REFLUX = Block.createCuboidShape(6.5, 0.0, 6.5, 9.5, 16.0, 9.5);
    public static final VoxelShape CONDENSER = Block.createCuboidShape(0.0, 6.5, 6.5, 16.0, 9.5, 9.5);

    private final ImmutableMap<BlockState, VoxelShape> stateToShapeMap;

    @Override
    public MapCodec<ChemistryWorkbenchBlock> getCodec() {
        return CODEC;
    }

    public ChemistryWorkbenchBlock(Settings settings) {
        super(settings);
        this.stateToShapeMap = getStateToShapeMap(this.getStateManager());
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BASE;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ChemistryWorkbenchBlockEntity(pos, state);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory namedScreenHandlerFactory = (ChemistryWorkbenchBlockEntity) world.getBlockEntity(pos);
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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(
                COMPONENT_PROPERTIES[0],
                COMPONENT_PROPERTIES[1],
                COMPONENT_PROPERTIES[2],
                COMPONENT_PROPERTIES[3],
                COMPONENT_PROPERTIES[4],
                COMPONENT_PROPERTIES[5],
                COMPONENT_PROPERTIES[6]
        );
        builder.add(FACING);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    private ImmutableMap<BlockState, VoxelShape> getStateToShapeMap(StateManager<Block, BlockState> stateManager) {
        Map<BlockState, VoxelShape> map = stateManager.getStates().stream()
                .collect(Collectors.toMap(Function.identity(), ChemistryWorkbenchBlock::getShapeForState));
        return ImmutableMap.copyOf(map);
    }

    private static VoxelShape getShapeForState(BlockState state) {
        //Direction direction = state.get(FACING);
        VoxelShape base = VoxelShapes.empty();
        VoxelShape components = VoxelShapes.empty();
        return VoxelShapes.union(base, components);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient) {
            return null;
        }
        return validateTicker(type, ModBlockEntities.CHEMISTRY_WORKBENCH, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    public static enum Modes {
        MENU,
        DISTILLATION,
        FILTER,
        EVAPORATE,
        DISSOLVE;

        public static List<Modes> getModesToDisplay() {
            return List.of(Modes.MENU, Modes.DISTILLATION, Modes.FILTER, Modes.EVAPORATE, Modes.DISSOLVE);
        }
        public int getAvailabilityIndex(Modes mode) {
            return switch (mode) {
                case MENU -> -1;
                case DISTILLATION -> 0;
                case FILTER -> 1;
                case EVAPORATE -> 2;
                case DISSOLVE -> 3;
            };
        }

        public int getRow(Modes modes) {
            return switch (modes) {
                case MENU -> 0;
                case DISTILLATION -> 1;
                case FILTER -> 2;
                case EVAPORATE -> 3;
                case DISSOLVE -> 4;
            };
        }

        public Text getDisplayName() {
            return switch (this) {
                case MENU -> Text.translatable("container.narchaotics.chemistry_workbench.menu_tooltip");
                case DISTILLATION -> Text.translatable("container.narchaotics.chemistry_workbench.distillation_tooltip");
                case FILTER -> Text.translatable("container.narchaotics.chemistry_workbench.filter_tooltip");
                case EVAPORATE -> Text.translatable("container.narchaotics.chemistry_workbench.evaporate_tooltip");
                case DISSOLVE -> Text.translatable("container.narchaotics.chemistry_workbench.dissolve_tooltip");
            };
        }
    }
}
