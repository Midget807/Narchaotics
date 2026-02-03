package net.midget807.narchaotics.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.midget807.narchaotics.datagen.ModItemTagProvider;
import net.midget807.narchaotics.registry.ModBlockEntities;
import net.midget807.narchaotics.registry.ModItems;
import net.midget807.narchaotics.registry.ModScreenHandlers;
import net.midget807.narchaotics.screen.ChemistryWorkbenchScreenHandler;
import net.midget807.narchaotics.util.ImplementedInventory;
import net.midget807.narchaotics.util.ModFluidUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
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

public class ChemistryWorkbenchBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory  {
    public static final String DISTILLATION_TIME_KEY = "DistillationTime";
    public static final String FILTER_TIME_KEY = "FilterTime";
    public static final String EVAPORATE_TIME_KEY = "EvaporateTime";
    public static final String DISSOLVE_TIME_KEY = "DissolveTime";
    public static final String DISTILLATION_FLUID_1_KEY = "DistillationFluidVariant1";
    public static final String DISTILLATION_FLUID_2_KEY = "DistillationFluidVariant2";
    public static final String DISTILLATION_FLUID_1_AMOUNT_KEY = "DistillationFluidVariant1Amount";
    public static final String DISTILLATION_FLUID_2_AMOUNT_KEY = "DistillationFluidVariant2Amount";
    public static final int DISTILLATION_TIME_DELEGATE_INDEX = 0;
    public static final int FILTER_TIME_DELEGATE_INDEX = 1;
    public static final int EVAPORATE_TIME_DELEGATE_INDEX = 2;
    public static final int DISSOLVE_TIME_DELEGATE_INDEX = 3;
    public static final int DISTILLATION_AVAILABLE_DELEGATE_INDEX = 0;
    public static final int FILTER_AVAILABLE_DELEGATE_INDEX = 1;
    public static final int EVAPORATE_AVAILABLE_DELEGATE_INDEX = 2;
    public static final int DISSOLVE_AVAILABLE_DELEGATE_INDEX = 3;
    public static final int[] MENU_SLOT_INDICES = {0, 1, 2, 3, 4, 5}; /** Bottom left -> up -> right */
    public static final int[] DISTILLATION_INPUT_INDICES = {6, 7, 8, 9, 12, 13}; /** {6, 7} -> Item Input   |   {8, 9} -> Reactant Fluid Input   |   {12, 13} -> Product Fluid Input */
    public static final int[] DISTILLATION_OUTPUT_INDICES = {10, 11, 14, 15, 16, 17}; /** {10, 11} -> Reactant Fluid Output   |   {14, 15} -> Item Output   | {16, 17} -> Product Fluid Output */
    public static final int[] FILTER_INPUT_INDICES = {18, 21}; /** 18 -> Reactant Fluid Input   |   21 -> Product Fluid Input*/
    public static final int[] FILTER_OUTPUT_INDICES = {19, 20, 22}; /** 19 -> Reactant Fluid Output   |   20 -> Residue Output   |   21 -> Product Fluid Output */
    public static final int[] EVAPORATE_INPUT_INDICES = {23}; /** Reactant Fluid Input */
    public static final int[] EVAPORATE_OUTPUT_INDICES = {24, 25}; /** 24 -> Reactant Fluid Output   |   25 -> Item Output */
    public static final int[] DISSOLVE_INPUT_INDICES = {26, 27, 28, 29, 32}; /** {26, 27} -> Item Input   |   {28, 29} -> Reactant Fluid Input   |   32 -> Product Fluid Input */
    public static final int[] DISSOLVE_OUTPUT_INDICES = {30, 31, 33}; /** {30, 31} -> Reactant Fluid Output   |   33 -> Product Fluid Output */
    public static final int[] BURNER_INPUT_INDICES = {34};
    private final DefaultedList<ItemStack> wholeItemInventory = DefaultedList.ofSize(35, ItemStack.EMPTY);
    private SingleVariantStorage<FluidVariant> distillationInputFluidStorage1 = ModFluidUtil.createTank(this);
    private SingleVariantStorage<FluidVariant> distillationInputFluidStorage2 = ModFluidUtil.createTank(this);
    private SingleVariantStorage<FluidVariant> distillationOutputFluidStorage1 = ModFluidUtil.createTank(this);
    private SingleVariantStorage<FluidVariant> distillationOutputFluidStorage2 = ModFluidUtil.createTank(this);
    private SingleVariantStorage<FluidVariant> filterInputFluidStorage = ModFluidUtil.createTank(this);
    private SingleVariantStorage<FluidVariant> filterOutputFluidStorage = ModFluidUtil.createTank(this);
    private SingleVariantStorage<FluidVariant> evaporateInputFluidStorage = ModFluidUtil.createTank(this);
    private SingleVariantStorage<FluidVariant> dissolveInputFluidStorage1 = ModFluidUtil.createTank(this);
    private SingleVariantStorage<FluidVariant> dissolveInputFluidStorage2 = ModFluidUtil.createTank(this);
    private SingleVariantStorage<FluidVariant> dissolveOutputFluidStorage = ModFluidUtil.createTank(this);

    int distillationTime;
    int filterTime;
    int evaporateTime;
    int dissolveTime;

    private Item distillationItemInput1 = Items.AIR;
    private Item distillationItemInput2 = Items.AIR;
    private FluidVariant distillationFluidInput1 = FluidVariant.blank();
    private FluidVariant distillationFluidInput2 = FluidVariant.blank();
    private int distillationFluidInput1Amount = 0;
    private int distillationFluidInput2Amount = 0;

    public int distillationAvailable;
    public int filterAvailable;
    public int evaporateAvailable;
    public int dissolveAvailable;

    public final PropertyDelegate timePropertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case DISTILLATION_TIME_DELEGATE_INDEX -> ChemistryWorkbenchBlockEntity.this.distillationTime;
                case FILTER_TIME_DELEGATE_INDEX -> ChemistryWorkbenchBlockEntity.this.filterTime;
                case EVAPORATE_TIME_DELEGATE_INDEX -> ChemistryWorkbenchBlockEntity.this.evaporateTime;
                case DISSOLVE_TIME_DELEGATE_INDEX -> ChemistryWorkbenchBlockEntity.this.dissolveTime;
                default -> DISSOLVE_TIME_DELEGATE_INDEX;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case DISTILLATION_TIME_DELEGATE_INDEX:
                    ChemistryWorkbenchBlockEntity.this.distillationTime = value;
                    break;
                case FILTER_TIME_DELEGATE_INDEX:
                    ChemistryWorkbenchBlockEntity.this.filterTime = value;
                    break;
                case EVAPORATE_TIME_DELEGATE_INDEX:
                    ChemistryWorkbenchBlockEntity.this.evaporateTime = value;
                    break;
                case DISSOLVE_TIME_DELEGATE_INDEX:
                    ChemistryWorkbenchBlockEntity.this.dissolveTime = value;
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };
    public final PropertyDelegate modePropertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case DISTILLATION_AVAILABLE_DELEGATE_INDEX -> ChemistryWorkbenchBlockEntity.this.distillationAvailable;
                case FILTER_AVAILABLE_DELEGATE_INDEX -> ChemistryWorkbenchBlockEntity.this.filterAvailable;
                case EVAPORATE_AVAILABLE_DELEGATE_INDEX -> ChemistryWorkbenchBlockEntity.this.evaporateAvailable;
                case DISSOLVE_AVAILABLE_DELEGATE_INDEX -> ChemistryWorkbenchBlockEntity.this.dissolveAvailable;
                default -> DISSOLVE_AVAILABLE_DELEGATE_INDEX;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case DISTILLATION_AVAILABLE_DELEGATE_INDEX:
                    ChemistryWorkbenchBlockEntity.this.distillationAvailable = value;
                    break;
                case FILTER_AVAILABLE_DELEGATE_INDEX:
                    ChemistryWorkbenchBlockEntity.this.filterAvailable = value;
                    break;
                case EVAPORATE_AVAILABLE_DELEGATE_INDEX:
                    ChemistryWorkbenchBlockEntity.this.evaporateAvailable = value;
                    break;
                case DISSOLVE_AVAILABLE_DELEGATE_INDEX:
                    ChemistryWorkbenchBlockEntity.this.dissolveAvailable = value;
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };

    public ChemistryWorkbenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHEMISTRY_WORKBENCH, pos, state);
    }

    @Override
    public int size() {
        return this.wholeItemInventory.size();
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.wholeItemInventory;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.wholeItemInventory.set(slot, stack);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (checkForDistillation()) {
            this.distillationAvailable = 1;
        } else {
            this.distillationAvailable = 0;
        }
        if (checkForFilter()) {
            this.filterAvailable = 1;
        } else {
            this.filterAvailable = 0;
        }
        if (checkForEvaporate()) {
            this.evaporateAvailable = 1;
        } else {
            this.evaporateAvailable = 0;
        }
        if (checkForDissolve()) {
            this.dissolveAvailable = 1;
        } else {
            this.dissolveAvailable = 0;
        }
        markDirty(world, pos, state);

        fillUpOnFluid();
    }

    public boolean checkForDistillation() {
        return this.wholeItemInventory.get(MENU_SLOT_INDICES[0]).isOf(ModItems.BURNER)
                && this.wholeItemInventory.get(MENU_SLOT_INDICES[1]).isOf(ModItems.CONICAL_FLASK)
                && this.wholeItemInventory.get(MENU_SLOT_INDICES[3]).isOf(ModItems.STAND_AND_CLAMP)
                && this.wholeItemInventory.get(MENU_SLOT_INDICES[4]).isOf(ModItems.CONDENSER)
                && this.wholeItemInventory.get(MENU_SLOT_INDICES[5]).isIn(ModItemTagProvider.DISTILLATION_OUTPUT);
    }
    public boolean checkForFilter() {
        return this.wholeItemInventory.get(MENU_SLOT_INDICES[0]).isOf(ModItems.BURNER)
                && this.wholeItemInventory.get(MENU_SLOT_INDICES[1]).isOf(ModItems.CONICAL_FLASK)
                && this.wholeItemInventory.get(MENU_SLOT_INDICES[2]).isOf(ModItems.FILTER_FUNNEL);
    }
    public boolean checkForEvaporate() {
        return this.wholeItemInventory.get(MENU_SLOT_INDICES[0]).isOf(ModItems.BURNER)
                && this.wholeItemInventory.get(MENU_SLOT_INDICES[1]).isOf(ModItems.BEAKER);
    }
    public boolean checkForDissolve() {
        return this.wholeItemInventory.get(MENU_SLOT_INDICES[0]).isOf(ModItems.BURNER)
                && this.wholeItemInventory.get(MENU_SLOT_INDICES[1]).isOf(ModItems.CONICAL_FLASK);
    }

    private void fillUpOnFluid() {
        if (hasFluidSourceItemInInputSlot()) {
            transferItemFluidToTank();
        }
    }

    private static void transferItemFluidToTank() {
        //todo
    }

    private static boolean hasFluidSourceItemInInputSlot() {
        return false;//todo
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        //this.wholeItemInventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.wholeItemInventory, registryLookup);
        this.distillationTime = nbt.getShort(DISTILLATION_TIME_KEY);
        this.filterTime = nbt.getShort(FILTER_TIME_KEY);
        this.evaporateTime = nbt.getShort(EVAPORATE_TIME_KEY);
        this.dissolveTime = nbt.getShort(DISSOLVE_TIME_KEY);

        this.distillationInputFluidStorage1.variant = nbt.contains(DISTILLATION_FLUID_1_KEY) ? FluidVariant.CODEC.parse(NbtOps.INSTANCE, nbt.get(DISTILLATION_FLUID_1_KEY)).getOrThrow() : FluidVariant.blank();
        this.distillationInputFluidStorage2.variant = nbt.contains(DISTILLATION_FLUID_2_KEY) ? FluidVariant.CODEC.parse(NbtOps.INSTANCE, nbt.get(DISTILLATION_FLUID_2_KEY)).getOrThrow() : FluidVariant.blank();

        if (this.distillationTime > 0) {
            this.distillationItemInput1 = this.wholeItemInventory.get(DISTILLATION_INPUT_INDICES[0]).getItem();
            this.distillationItemInput2 = this.wholeItemInventory.get(DISTILLATION_INPUT_INDICES[1]).getItem();
            this.distillationFluidInput1 = this.distillationInputFluidStorage1.variant;
            this.distillationFluidInput2 = this.distillationInputFluidStorage2.variant;
        }
        super.readNbt(nbt, registryLookup);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.wholeItemInventory, registryLookup);
        nbt.putShort(DISTILLATION_TIME_KEY, (short) this.distillationTime);
        nbt.putShort(FILTER_TIME_KEY, (short) this.filterTime);
        nbt.putShort(EVAPORATE_TIME_KEY, (short) this.evaporateTime);
        nbt.putShort(DISSOLVE_TIME_KEY, (short) this.dissolveTime);

        nbt.put(DISTILLATION_FLUID_1_KEY, FluidVariant.CODEC.encodeStart(NbtOps.INSTANCE, this.distillationFluidInput1).getOrThrow());
        nbt.put(DISTILLATION_FLUID_2_KEY, FluidVariant.CODEC.encodeStart(NbtOps.INSTANCE, this.distillationFluidInput2).getOrThrow());
        nbt.putLong(DISTILLATION_FLUID_1_AMOUNT_KEY, this.distillationFluidInput1Amount);
        nbt.putLong(DISTILLATION_FLUID_2_AMOUNT_KEY, this.distillationFluidInput2Amount);
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
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.narchaotics.chemistry_workbench");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ChemistryWorkbenchScreenHandler(syncId, playerInventory, this, this.timePropertyDelegate, modePropertyDelegate);
    }
}
