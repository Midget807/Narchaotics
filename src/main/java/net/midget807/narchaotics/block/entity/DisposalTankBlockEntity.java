package net.midget807.narchaotics.block.entity;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.midget807.narchaotics.datagen.ModItemTagProvider;
import net.midget807.narchaotics.item.FlaskItem;
import net.midget807.narchaotics.recipe.AshRecipe;
import net.midget807.narchaotics.recipe.AshRecipeInput;
import net.midget807.narchaotics.recipe.FermentRecipe;
import net.midget807.narchaotics.recipe.FermentRecipeInput;
import net.midget807.narchaotics.registry.ModBlockEntities;
import net.midget807.narchaotics.registry.ModItems;
import net.midget807.narchaotics.registry.ModRecipes;
import net.midget807.narchaotics.screen.DisposalTankScreenHandler;
import net.midget807.narchaotics.util.ImplementedInventory;
import net.midget807.narchaotics.util.ModFluidUtil;
import net.midget807.narchaotics.util.inject.FlaskStorable;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.RecipeUnlocker;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants.BUCKET;
import static net.midget807.narchaotics.util.ModBlockUtil.ANIMATION_TIME_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.HAS_SUNLIGHT_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.MAX_PROGRESS_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.PRODUCT_FLUID_AMOUNT_1_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.PRODUCT_FLUID_VARIANT_1_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.PROGRESS_TIME_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.REACTANT_FLUID_AMOUNT_1_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.REACTANT_FLUID_VARIANT_1_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.RECIPES_USED_KEY;

public class DisposalTankBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory, RecipeUnlocker, RecipeInputProvider {
    public static final int PROGRESS_TIME_DELEGATE_INDEX = 0;
    public static final int MAX_PROGRESS_DELEGATE_INDEX = 1;
    public static final int ANIMATION_TIME_DELEGATE_INDEX = 2;
    public static final int REACTANT_FLUID_1_DELEGATE_INDEX = 3;
    public static final int PRODUCT_FLUID_1_DELEGATE_INDEX = 4;
    public static final int HAS_SUNLIGHT_DELEGATE_INDEX = 5;
    public static final int[] INPUT_INDICES = {0};
    public static final int[] OUTPUT_INDICES = {1};
    public static final int[] FLUID_INPUT_INDICES = {0};
    public static final int[] ITEM_INPUT_INDICES = {};
    public static final int[] ITEM_OUTPUT_INDICES = {};
    public static final int[] FLUID_OUTPUT_INDICES = {1};
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);
    public SingleVariantStorage<FluidVariant> reactantFluidStorage1 = ModFluidUtil.createTank(4, this);
    public SingleVariantStorage<FluidVariant> productFluidStorage1 = ModFluidUtil.createTank(this);
    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return (int) switch (index) {
                case 0 -> DisposalTankBlockEntity.this.progressTime;
                case 1 -> DisposalTankBlockEntity.this.maxProgress;
                case 2 -> DisposalTankBlockEntity.this.animationTime;
                case 3 -> DisposalTankBlockEntity.this.reactantFluidStorage1.amount;
                case 4 -> DisposalTankBlockEntity.this.productFluidStorage1.amount;
                case 5 -> DisposalTankBlockEntity.this.hasSunlight;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0: DisposalTankBlockEntity.this.progressTime = value;
                case 1: DisposalTankBlockEntity.this.maxProgress = value;
                case 2: DisposalTankBlockEntity.this.animationTime = value;
                case 3: DisposalTankBlockEntity.this.reactantFluidStorage1.amount = value;
                case 4: DisposalTankBlockEntity.this.productFluidStorage1.amount = value;
                case 5: DisposalTankBlockEntity.this.hasSunlight = value;
            }
        }

        @Override
        public int size() {
            return 6;
        }
    };
    private int progressTime;
    private int maxProgress;
    private int animationTime;
    private int hasSunlight;
    private final Object2IntOpenHashMap<Identifier> recipeUsed = new Object2IntOpenHashMap<>();
    private final RecipeManager.MatchGetter<FermentRecipeInput, FermentRecipe> fermentMatchGetter = RecipeManager.createCachedMatchGetter(ModRecipes.FERMENT_TYPE);
    private final RecipeManager.MatchGetter<AshRecipeInput, AshRecipe> ashMatchGetter = RecipeManager.createCachedMatchGetter(ModRecipes.ASH_TYPE);


    public DisposalTankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DISPOSAL_TANK, pos, state);
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
    public int size() {
        return this.inventory.size();
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        ItemStack itemStack = this.inventory.get(slot);
        boolean inputSameAsSlot = !stack.isEmpty() && ItemStack.areItemsAndComponentsEqual(itemStack, stack);
        this.inventory.set(slot, stack);
    }

    public boolean insertStack(int slot, ItemStack stack) {
        ItemStack stackInSlot = this.inventory.get(slot);
        if (!stackInSlot.isEmpty()) {
            if (stack.isOf(stackInSlot.getItem())) {
                stack.increment(stackInSlot.getCount());
            } else {
                return false;
            }
        }
        this.setStack(slot, stack);
        return stack.isOf(stackInSlot.getItem()) || stackInSlot.isEmpty();
    }
    public boolean insertStack(int slot, ItemStack stack, SingleVariantStorage<FluidVariant> storage, Fluid input, ItemStack inputStack) {
        if (!storage.isResourceBlank() && !storage.variant.getFluid().matchesType(input)) return false;
        if (inputStack.getItem() instanceof BucketItem) {
            if (storage.amount > 0) return false;
        } else if (inputStack.getItem() instanceof FlaskItem flaskItem) {
            if (storage.getCapacity() - storage.amount < flaskItem.capacity) return false;
        }
        ItemStack stackInSlot = this.inventory.get(slot);
        if (!stackInSlot.isEmpty()) {
            if (stack.isOf(stackInSlot.getItem())) {
                stack.increment(stackInSlot.getCount());
            } else {
                return false;
            }
        }
        this.setStack(slot, stack);
        return stack.isOf(stackInSlot.getItem()) || stackInSlot.isEmpty();
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.narchaotics.disposal_tank");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new DisposalTankScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
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
        nbt.putInt(PROGRESS_TIME_KEY, this.progressTime);
        nbt.putInt(MAX_PROGRESS_KEY, this.maxProgress);
        nbt.putInt(ANIMATION_TIME_KEY, this.animationTime);
        if (!reactantFluidStorage1.isResourceBlank()) {
            FluidVariant.CODEC.encodeStart(NbtOps.INSTANCE, reactantFluidStorage1.variant).result().ifPresent(nbtElement -> nbt.put(REACTANT_FLUID_VARIANT_1_KEY, nbtElement));
            nbt.putLong(REACTANT_FLUID_AMOUNT_1_KEY, reactantFluidStorage1.amount);
        }
        if (!productFluidStorage1.isResourceBlank()) {
            FluidVariant.CODEC.encodeStart(NbtOps.INSTANCE, productFluidStorage1.variant).result().ifPresent(nbtElement -> nbt.put(PRODUCT_FLUID_VARIANT_1_KEY, nbtElement));
            nbt.putLong(PRODUCT_FLUID_AMOUNT_1_KEY, productFluidStorage1.amount);
        }
        nbt.putInt(HAS_SUNLIGHT_KEY, this.hasSunlight);

        NbtCompound nbtCompound = new NbtCompound();
        this.recipeUsed.forEach((identifier, count) -> nbtCompound.putInt(identifier.toString(), count));
        nbt.put(RECIPES_USED_KEY, nbtCompound);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, this.inventory, registryLookup);
        this.progressTime = nbt.getInt(PROGRESS_TIME_KEY);
        this.maxProgress = nbt.getInt(MAX_PROGRESS_KEY);
        this.animationTime = nbt.getInt(ANIMATION_TIME_KEY);
        this.reactantFluidStorage1.variant = FluidVariant.CODEC.parse(NbtOps.INSTANCE, nbt.get(REACTANT_FLUID_VARIANT_1_KEY)).result().orElse(FluidVariant.blank());
        this.reactantFluidStorage1.amount = nbt.getLong(REACTANT_FLUID_AMOUNT_1_KEY);
        this.productFluidStorage1.variant = FluidVariant.CODEC.parse(NbtOps.INSTANCE, nbt.get(PRODUCT_FLUID_VARIANT_1_KEY)).result().orElse(FluidVariant.blank());
        this.productFluidStorage1.amount = nbt.getLong(PRODUCT_FLUID_AMOUNT_1_KEY);
        this.hasSunlight = nbt.getInt(HAS_SUNLIGHT_KEY);

        NbtCompound nbtCompound = nbt.getCompound(RECIPES_USED_KEY);
        for (String string : nbtCompound.getKeys()) {
            this.recipeUsed.put(Identifier.of(string), nbtCompound.getInt(string));
        }
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        fillUpOnFluid();
        removeFluid();
    }

    private boolean inputsEmpty() {
        return this.reactantFluidStorage1.isResourceBlank() && this.getStack(ITEM_INPUT_INDICES[0]).isEmpty();
    }

    private void removeFluid() {
        try (Transaction transaction = Transaction.openOuter()) {
            if (!this.reactantFluidStorage1.isResourceBlank()) {
                this.reactantFluidStorage1.extract(this.reactantFluidStorage1.variant, 50, transaction);
                transaction.commit();
            }
        }
    }

    private void transferFluidTankToItem(int slot) {
        try (Transaction transaction = Transaction.openOuter()) {
            ItemStack stack = this.getStack(slot);
            switch (slot) {
                case 0: {
                    Item outputFluidItem = null;
                    if (this.reactantFluidStorage1.amount <= 0 || this.reactantFluidStorage1.variant.isBlank()) return;
                    if (this.getStack(FLUID_OUTPUT_INDICES[0]).getCount() >= this.getStack(FLUID_OUTPUT_INDICES[0]).getItem().getMaxCount()) return;
                    if (stack.isOf(Items.BUCKET)) {
                        if (this.reactantFluidStorage1.amount < 1000) return;
                        outputFluidItem = this.reactantFluidStorage1.variant.getFluid().getBucketItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[0]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[0]).isOf(outputFluidItem)) return;
                        this.reactantFluidStorage1.extract(this.reactantFluidStorage1.variant, BUCKET / 81, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    } else if (stack.isOf(ModItems.CONICAL_FLASK)) {
                        if (this.reactantFluidStorage1.amount < ((FlaskItem) stack.getItem()).capacity) return;
                        outputFluidItem = ((FlaskStorable) this.reactantFluidStorage1.variant.getFluid()).narchaotics$getConicalFlaskItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[0]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[0]).isOf(outputFluidItem)) return;
                        this.reactantFluidStorage1.extract(this.reactantFluidStorage1.variant, 250, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    } else if (stack.isOf(ModItems.ROUND_FLASK)) {
                        if (this.reactantFluidStorage1.amount < ((FlaskItem) stack.getItem()).capacity) return;
                        outputFluidItem = ((FlaskStorable) this.reactantFluidStorage1.variant.getFluid()).narchaotics$getRoundFlaskItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[0]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[0]).isOf(outputFluidItem)) return;
                        this.reactantFluidStorage1.extract(this.reactantFluidStorage1.variant, 250, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    } else if (stack.isOf(ModItems.BEAKER)) {
                        if (this.reactantFluidStorage1.amount < ((FlaskItem) stack.getItem()).capacity) return;
                        outputFluidItem = ((FlaskStorable) this.reactantFluidStorage1.variant.getFluid()).narchaotics$getBeakerItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[0]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[0]).isOf(outputFluidItem)) return;
                        this.reactantFluidStorage1.extract(this.reactantFluidStorage1.variant, 250, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    } else if (stack.isOf(ModItems.TEST_TUBE)) {
                        if (this.reactantFluidStorage1.amount < ((FlaskItem) stack.getItem()).capacity) return;
                        outputFluidItem = ((FlaskStorable) this.reactantFluidStorage1.variant.getFluid()).narchaotics$getTestTubeItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[0]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[0]).isOf(outputFluidItem)) return;
                        this.reactantFluidStorage1.extract(this.reactantFluidStorage1.variant, 50, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    }
                    if (outputFluidItem != null && this.insertStack(FLUID_OUTPUT_INDICES[0], outputFluidItem.getDefaultStack())) {
                        stack.decrement(1);
                        this.setStack(slot, stack);
                        this.markDirty();
                    }
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + slot);
            }
        }
    }

    private void fillUpOnFluid() {
        for (int slot : FLUID_INPUT_INDICES) {
            if (hasFluidSourceItemInFluidInputSlots(slot)) {
                transferItemToFluidTank(slot);
            }
        }
    }

    private void transferItemToFluidTank(int slot) {
        try (Transaction transaction = Transaction.openOuter()) {
            Fluid fluid = null;
            ItemStack stack = this.getStack(slot);
            if (fluid == null && stack.getItem() instanceof BucketItem bucketItem) fluid = bucketItem.fluid;
            if (fluid == null && stack.getItem() instanceof FlaskItem flaskItem) fluid = flaskItem.fluid;
            if (fluid == null) return;
            if (fluid == Fluids.EMPTY) return;
            switch (slot) {
                case 0: {
                    ItemStack remainderStack;
                    if (this.getStack(FLUID_OUTPUT_INDICES[0]).getCount() >= this.getStack(FLUID_OUTPUT_INDICES[0]).getMaxCount()) return;
                    if (stack.getItem() instanceof BucketItem) {
                        remainderStack = new ItemStack(Items.BUCKET);
                        if (this.reactantFluidStorage1.amount > 0) return;
                        if (this.insertStack(FLUID_OUTPUT_INDICES[0], remainderStack, reactantFluidStorage1, fluid, stack)) {
                            stack.decrement(1);
                            this.setStack(slot, stack);
                            this.reactantFluidStorage1.insert(FluidVariant.of(fluid), BUCKET / 81, transaction);
                            playFluidInsertSound(this.getWorld());
                            transaction.commit();
                            this.markDirty();
                        }
                    } else if (stack.getItem() instanceof FlaskItem flaskItem) {
                        remainderStack = flaskItem.getRemainderStack();
                        if (this.reactantFluidStorage1.amount > 1000 - flaskItem.capacity) return;
                        if (remainderStack != null && this.insertStack(FLUID_OUTPUT_INDICES[0], remainderStack, reactantFluidStorage1, fluid, stack)) {
                            stack.decrement(1);
                            this.setStack(slot, stack);
                            this.reactantFluidStorage1.insert(FluidVariant.of(fluid), flaskItem.capacity, transaction);
                            playFluidInsertSound(this.getWorld());
                            transaction.commit();
                            this.markDirty();
                        }
                    }
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + slot);
            }
        }
    }

    private boolean hasFluidSourceItemInFluidInputSlots(int slot) {
        return this.getStack(slot).isIn(ModItemTagProvider.FLUID_INPUT_ITEMS);
    }

    private void playFluidInsertSound(World world) {
        if (world == null) return;
        if (world.isClient) return;
        world.playSound(null, this.getPos(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }
    private void playFluidExtractSound(World world) {
        if (world == null) return;
        if (world.isClient) return;
        world.playSound(null, this.getPos(), SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }


    @Override
    public void provideRecipeInputs(RecipeMatcher finder) {
        for (ItemStack itemStack : this.inventory) {
            finder.addInput(itemStack);
        }
    }

    @Override
    public void setLastRecipe(@Nullable RecipeEntry<?> recipe) {
        if (recipe != null) {
            Identifier identifier = recipe.id();
            this.recipeUsed.addTo(identifier, 1);
        }
    }

    @Override
    public @Nullable RecipeEntry<?> getLastRecipe() {
        return null;
    }

    @Override
    public void unlockLastRecipe(PlayerEntity player, List<ItemStack> ingredients) {
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return slot == FLUID_INPUT_INDICES[0] || slot == ITEM_INPUT_INDICES[0];
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return side == Direction.DOWN && (slot == FLUID_OUTPUT_INDICES[1] || slot == ITEM_OUTPUT_INDICES[0]);
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return this.isValid(slot, stack);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.DOWN) {
            return new int[] {ITEM_OUTPUT_INDICES[0], FLUID_OUTPUT_INDICES[1]};
        } else {
            return new int[] {FLUID_INPUT_INDICES[0], ITEM_INPUT_INDICES[0]};
        }
    }

}
