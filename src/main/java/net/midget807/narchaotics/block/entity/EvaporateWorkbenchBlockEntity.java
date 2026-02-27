package net.midget807.narchaotics.block.entity;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.midget807.narchaotics.datagen.ModItemTagProvider;
import net.midget807.narchaotics.item.FlaskItem;
import net.midget807.narchaotics.recipe.EvaporateRecipe;
import net.midget807.narchaotics.recipe.EvaporateRecipeInput;
import net.midget807.narchaotics.recipe.EvaporateRecipe;
import net.midget807.narchaotics.recipe.FluidStack;
import net.midget807.narchaotics.registry.ModBlockEntities;
import net.midget807.narchaotics.registry.ModItems;
import net.midget807.narchaotics.registry.ModRecipes;
import net.midget807.narchaotics.screen.EvaporateScreenHandler;
import net.midget807.narchaotics.util.ImplementedInventory;
import net.midget807.narchaotics.util.ModFluidUtil;
import net.midget807.narchaotics.util.inject.FlaskStorable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants.BUCKET;
import static net.midget807.narchaotics.util.ModBlockUtil.ANIMATION_TIME_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.MAX_PROGRESS_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.PROGRESS_TIME_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.REACTANT_FLUID_AMOUNT_1_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.REACTANT_FLUID_VARIANT_1_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.RECIPES_USED_KEY;

public class EvaporateWorkbenchBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory, RecipeUnlocker, RecipeInputProvider {
    public static final int PROGRESS_TIME_DELEGATE_INDEX = 0;
    public static final int MAX_PROGRESS_DELEGATE_INDEX = 1;
    public static final int ANIMATION_TIME_DELEGATE_INDEX = 2;
    public static final int REACTANT_FLUID_1_DELEGATE_INDEX = 3;
    public static final int[] INPUT_INDICES = {0, 2};
    public static final int[] OUTPUT_INDICES = {1, 3};
    public static final int[] ITEM_INPUT_INDICES = {2};
    public static final int[] FLUID_INPUT_INDICES = {0};
    public static final int FUEL_INPUT_INDEX = 2;
    public static final int[] ITEM_OUTPUT_INDICES = {3};
    public static final int[] FLUID_OUTPUT_INDICES = {1};
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(13, ItemStack.EMPTY);
    private SingleVariantStorage<FluidVariant> reactantFluidStorage1 = ModFluidUtil.createTank(this);
    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return (int) switch (index) {
                case 0 -> EvaporateWorkbenchBlockEntity.this.progressTime;
                case 1 -> EvaporateWorkbenchBlockEntity.this.maxProgress;
                case 2 -> EvaporateWorkbenchBlockEntity.this.animationTime;
                case 3 -> EvaporateWorkbenchBlockEntity.this.reactantFluidStorage1.amount;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0: EvaporateWorkbenchBlockEntity.this.progressTime = value;
                case 1: EvaporateWorkbenchBlockEntity.this.maxProgress = value;
                case 2: EvaporateWorkbenchBlockEntity.this.animationTime = value;
                case 3: EvaporateWorkbenchBlockEntity.this.reactantFluidStorage1.amount = value;
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };
    private int progressTime;
    private int maxProgress;
    private int animationTime;
    private final Object2IntOpenHashMap<Identifier> recipeUsed = new Object2IntOpenHashMap<>();
    private final RecipeManager.MatchGetter<EvaporateRecipeInput, EvaporateRecipe> matchGetter = RecipeManager.createCachedMatchGetter(ModRecipes.EVAPORATE_TYPE);


    public EvaporateWorkbenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EVAPORATE_WORKBENCH, pos, state);
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
        for (int i : FLUID_INPUT_INDICES) {
            if (slot == i && !inputSameAsSlot) {
                this.maxProgress = getCookTime(this.world, this);
                this.progressTime = 0;
                this.markDirty();
            }
        }
        for (int i : ITEM_INPUT_INDICES) {
            if (slot == i && !inputSameAsSlot) {
                this.maxProgress = getCookTime(this.world, this);
                this.progressTime = 0;
                this.markDirty();
            }
        }
    }

    public boolean insertStack(int slot, ItemStack stack) {
        ItemStack stackInSlot = this.inventory.get(slot);
        if (!stackInSlot.isEmpty() && stack.isOf(stackInSlot.getItem())) {
            stack.increment(stackInSlot.getCount());
        }
        this.setStack(slot, stack);
        return stack.isOf(stackInSlot.getItem()) || stackInSlot.isEmpty();
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.narchaotics.evaporate_workbench");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new EvaporateScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
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

        NbtCompound nbtCompound = nbt.getCompound(RECIPES_USED_KEY);
        for (String string : nbtCompound.getKeys()) {
            this.recipeUsed.put(Identifier.of(string), nbtCompound.getInt(string));
        }
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        fillUpOnFluid();
        removeFluid();
        boolean shouldMarkDirty = false;
        if (!inputsEmpty()) {
            RecipeEntry<EvaporateRecipe> recipeEntry = this.matchGetter.getFirstMatch(
                    new EvaporateRecipeInput(
                            new FluidStack(this.reactantFluidStorage1.variant, this.reactantFluidStorage1.amount),
                            this.getStack(FUEL_INPUT_INDEX)
                    ),
                    world
            ).orElse(null);

            if (canAcceptRecipeOutput(recipeEntry)) {
                if (this.progressTime == this.maxProgress) {
                    this.progressTime = 0;
                    this.maxProgress = getCookTime(world, this);
                    if (craftRecipe(recipeEntry, this.inventory)) {
                        this.setLastRecipe(recipeEntry);
                    }
                    shouldMarkDirty = true;
                }
                this.progressTime++;
            } else {
                this.progressTime = 0;
            }
        } else if (this.progressTime > 0) {
            this.progressTime = MathHelper.clamp(this.progressTime - 2, 0, this.maxProgress);
        }

        if (shouldMarkDirty) {
            markDirty(world, pos, state);
        }
    }

    private boolean inputsEmpty() {
        return this.reactantFluidStorage1.isResourceBlank();
    }

    private void removeFluid() {
        for (int slot : FLUID_INPUT_INDICES) {
            if (inventory.get(slot).isIn(ModItemTagProvider.FLUID_REMOVE_ITEMS)) {
                transferFluidTankToItem(slot);
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
                    ItemStack remainderStack = null;
                    if (this.getStack(FLUID_OUTPUT_INDICES[0]).getCount() >= this.getStack(FLUID_OUTPUT_INDICES[0]).getMaxCount()) return;
                    if (stack.getItem() instanceof BucketItem) {
                        if (this.reactantFluidStorage1.amount > 0) return;
                        this.reactantFluidStorage1.insert(FluidVariant.of(fluid), BUCKET / 81, transaction);
                        playFluidInsertSound(this.getWorld());
                        transaction.commit();
                        remainderStack = new ItemStack(Items.BUCKET);
                    } else if (stack.getItem() instanceof FlaskItem flaskItem) {
                        if (this.reactantFluidStorage1.amount > 1000 - flaskItem.capacity) return;
                        this.reactantFluidStorage1.insert(FluidVariant.of(fluid), flaskItem.capacity, transaction);
                        playFluidInsertSound(this.getWorld());
                        transaction.commit();
                        remainderStack = flaskItem.getRemainderStack();
                    }
                    if (remainderStack != null && this.insertStack(FLUID_OUTPUT_INDICES[0], remainderStack)) {
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

    private static int getCookTime(World world, EvaporateWorkbenchBlockEntity blockEntity) {
        EvaporateRecipeInput filterRecipeInput = new EvaporateRecipeInput(
                new FluidStack(blockEntity.reactantFluidStorage1.variant, blockEntity.reactantFluidStorage1.amount),
                blockEntity.getStack(FUEL_INPUT_INDEX)
        );
        return (Integer) blockEntity.matchGetter
                .getFirstMatch(filterRecipeInput, world)
                .map(recipe -> recipe.value().getEvaporateTime())
                .orElse(120);
    }

    private boolean craftRecipe(RecipeEntry<EvaporateRecipe> recipe, DefaultedList<ItemStack> inventory) {
        if (recipe != null && canAcceptRecipeOutput(recipe)) {
            ItemStack recipeResult1 = recipe.value().output;
            ItemStack outputSlotItem1 = inventory.get(ITEM_OUTPUT_INDICES[0]);
            if (!recipeResult1.isEmpty()) {
                if (outputSlotItem1.isEmpty()) {
                    inventory.set(ITEM_OUTPUT_INDICES[0], recipeResult1.copy());
                } else if (ItemStack.areItemsAndComponentsEqual(outputSlotItem1, recipeResult1)) {
                    outputSlotItem1.increment(recipeResult1.getCount());
                }
            }

            markDirty();
            if (this.getWorld() != null) this.getWorld().updateListeners(pos, this.getCachedState(), this.getCachedState(), Block.NOTIFY_LISTENERS);
            return true;
        } else {
            return false;
        }
    }

    public boolean canAcceptRecipeOutput(RecipeEntry<EvaporateRecipe> recipeEntry) {
        if (recipeEntry == null) return false;
        EvaporateRecipe recipe = recipeEntry.value();
        boolean canOutputItem1 = canOutputItem(recipe.output, this.inventory.get(ITEM_OUTPUT_INDICES[0]), recipe.input, this.reactantFluidStorage1);
        return canOutputItem1;
    }

    private static boolean canOutputItem(ItemStack recipeOutput, ItemStack slotStack,  FluidStack recipeInput, SingleVariantStorage<FluidVariant> inputTank) {
        if (!recipeInput.isEmpty()) {
            if (!inputTank.getResource().equals(recipeInput.variant())) {
                return false;
            }

            if (inputTank.getAmount() < recipeInput.amount()) {
                return false;
            }
        }
        if (recipeOutput.isEmpty()) return true;

        if (slotStack.isEmpty()) return true;

        if (!ItemStack.areItemsAndComponentsEqual(slotStack, recipeOutput)) return false;

        return slotStack.getCount() + recipeOutput.getCount() <= Math.min(recipeOutput.getMaxCount(), slotStack.getMaxCount());
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
        return slot == FLUID_INPUT_INDICES[0];
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return side == Direction.DOWN && (slot == ITEM_OUTPUT_INDICES[0]);
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return this.isValid(slot, stack);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.DOWN) {
            return ITEM_OUTPUT_INDICES;
        } else {
            return new int[FLUID_INPUT_INDICES[0]];
        }
    }

}
