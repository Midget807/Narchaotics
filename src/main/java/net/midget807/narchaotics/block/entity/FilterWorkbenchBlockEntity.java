package net.midget807.narchaotics.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.midget807.narchaotics.registry.ModBlockEntities;
import net.midget807.narchaotics.screen.DistillationScreenHandler;
import net.midget807.narchaotics.screen.FilterScreenHandler;
import net.midget807.narchaotics.util.ImplementedInventory;
import net.midget807.narchaotics.util.ModFluidUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FilterWorkbenchBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    public static final int PROGRESS_TIME_DELEGATE_INDEX = 0;
    public static final int MAX_PROGRESS_DELEGATE_INDEX = 1;
    public static final int ANIMATION_TIME_DELEGATE_INDEX = 2;
    public static final int REACTANT_FLUID_1_DELEGATE_INDEX = 3;
    public static final int REACTANT_FLUID_2_DELEGATE_INDEX = 4;
    public static final int PRODUCT_FLUID_1_DELEGATE_INDEX = 5;
    public static final int PRODUCT_FLUID_2_DELEGATE_INDEX = 6;
    public static final int[] INPUT_INDICES = {0, 1, 2, 3, 4, 5, 6};
    public static final int[] OUTPUT_INDICES = {7, 8, 9, 10, 11, 12};
    public static final int[] ITEM_INPUT_INDICES = {0, 1};
    public static final int[] FLUID_INPUT_INDICES = {2, 3, 4, 5};
    public static final int FUEL_INPUT_INDEX = 6;
    public static final int[] ITEM_OUTPUT_INDICES = {7, 8};
    public static final int[] FLUID_OUTPUT_INDICES = {9, 10, 11, 12};
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(13, ItemStack.EMPTY);
    private SingleVariantStorage<FluidVariant> reactantFluidStorage1 = ModFluidUtil.createTank(this);
    private SingleVariantStorage<FluidVariant> reactantFluidStorage2 = ModFluidUtil.createTank(this);
    private SingleVariantStorage<FluidVariant> productFluidStorage1 = ModFluidUtil.createTank(this);
    private SingleVariantStorage<FluidVariant> productFluidStorage2 = ModFluidUtil.createTank(this);
    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return (int) switch (index) {
                case 0 -> FilterWorkbenchBlockEntity.this.progressTime;
                case 1 -> FilterWorkbenchBlockEntity.this.maxProgress;
                case 2 -> FilterWorkbenchBlockEntity.this.animationTime;
                case 3 -> FilterWorkbenchBlockEntity.this.reactantFluidStorage1.amount;
                case 4 -> FilterWorkbenchBlockEntity.this.reactantFluidStorage2.amount;
                case 5 -> FilterWorkbenchBlockEntity.this.productFluidStorage1.amount;
                case 6 -> FilterWorkbenchBlockEntity.this.productFluidStorage2.amount;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0: FilterWorkbenchBlockEntity.this.progressTime = value;
                case 1: FilterWorkbenchBlockEntity.this.maxProgress = value;
                case 2: FilterWorkbenchBlockEntity.this.animationTime = value;
                case 3: FilterWorkbenchBlockEntity.this.reactantFluidStorage1.amount = value;
                case 4: FilterWorkbenchBlockEntity.this.reactantFluidStorage2.amount = value;
                case 5: FilterWorkbenchBlockEntity.this.productFluidStorage1.amount = value;
                case 6: FilterWorkbenchBlockEntity.this.productFluidStorage2.amount = value;
            }
        }

        @Override
        public int size() {
            return 7;
        }
    };
    private int progressTime;
    private int maxProgress;
    private int animationTime;


    public FilterWorkbenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FILTER_WORKBENCH, pos, state);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.narchaotics.filter_workbench");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FilterScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.inventory, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, this.inventory, registryLookup);
    }

    public void tick(World world, BlockPos pos, BlockState state) {

    }

}
