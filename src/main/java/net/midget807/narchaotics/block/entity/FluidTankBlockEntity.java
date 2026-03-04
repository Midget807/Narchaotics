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
import net.midget807.narchaotics.recipe.FluidStack;
import net.midget807.narchaotics.registry.ModBlockEntities;
import net.midget807.narchaotics.registry.ModItems;
import net.midget807.narchaotics.registry.ModRecipes;
import net.midget807.narchaotics.screen.FluidTankScreenHandler;
import net.midget807.narchaotics.util.ImplementedInventory;
import net.midget807.narchaotics.util.ModFluidUtil;
import net.midget807.narchaotics.util.inject.FlaskStorable;
import net.minecraft.block.Block;
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
import net.minecraft.recipe.Ingredient;
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
import net.minecraft.util.math.MathHelper;
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

public class FluidTankBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory, RecipeUnlocker, RecipeInputProvider {
    public static final int PROGRESS_TIME_DELEGATE_INDEX = 0;
    public static final int MAX_PROGRESS_DELEGATE_INDEX = 1;
    public static final int ANIMATION_TIME_DELEGATE_INDEX = 2;
    public static final int REACTANT_FLUID_1_DELEGATE_INDEX = 3;
    public static final int PRODUCT_FLUID_1_DELEGATE_INDEX = 4;
    public static final int HAS_SUNLIGHT_DELEGATE_INDEX = 5;
    public static final int[] INPUT_INDICES = {0, 1, 4};
    public static final int[] OUTPUT_INDICES = {2, 3, 5};
    public static final int[] FLUID_INPUT_INDICES = {0, 1};
    public static final int[] ITEM_INPUT_INDICES = {4};
    public static final int[] ITEM_OUTPUT_INDICES = {5};
    public static final int[] FLUID_OUTPUT_INDICES = {2, 3};
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);
    public SingleVariantStorage<FluidVariant> reactantFluidStorage1 = ModFluidUtil.createTank(4, this);
    public SingleVariantStorage<FluidVariant> productFluidStorage1 = ModFluidUtil.createTank(this);
    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return (int) switch (index) {
                case 0 -> FluidTankBlockEntity.this.progressTime;
                case 1 -> FluidTankBlockEntity.this.maxProgress;
                case 2 -> FluidTankBlockEntity.this.animationTime;
                case 3 -> FluidTankBlockEntity.this.reactantFluidStorage1.amount;
                case 4 -> FluidTankBlockEntity.this.productFluidStorage1.amount;
                case 5 -> FluidTankBlockEntity.this.hasSunlight;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0: FluidTankBlockEntity.this.progressTime = value;
                case 1: FluidTankBlockEntity.this.maxProgress = value;
                case 2: FluidTankBlockEntity.this.animationTime = value;
                case 3: FluidTankBlockEntity.this.reactantFluidStorage1.amount = value;
                case 4: FluidTankBlockEntity.this.productFluidStorage1.amount = value;
                case 5: FluidTankBlockEntity.this.hasSunlight = value;
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


    public FluidTankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLUID_TANK, pos, state);
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
                assert this.world != null;
                this.maxProgress = getCookTime(this.world, this);
                this.progressTime = 0;
                this.markDirty();
            }
        }
        for (int i : ITEM_INPUT_INDICES) {
            if (slot == i && !inputSameAsSlot) {
                assert this.world != null;
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
        if (this.getWorld() != null) {
            BlockState underState = this.getWorld().getBlockState(this.getPos().offset(Direction.DOWN));
            if (underState.isOf(Blocks.MAGMA_BLOCK)) {
                return Text.translatable("container.narchaotics.fluid_tank.fermenting");
            } else if (underState.isIn(BlockTags.CAMPFIRES)) {
                return Text.translatable("container.narchaotics.fluid_tank.ashing");
            }
        }
        return Text.translatable("container.narchaotics.fluid_tank.store");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FluidTankScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
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
        boolean shouldMarkDirty = false;
        boolean isFerment = world.getBlockState(pos.offset(Direction.DOWN)).isOf(Blocks.MAGMA_BLOCK);
        boolean isAsh = world.getBlockState(pos.offset(Direction.DOWN)).isIn(BlockTags.CAMPFIRES);
        if (!inputsEmpty()) {
            if (isFerment) {
                RecipeEntry<FermentRecipe> recipeEntry = this.fermentMatchGetter.getFirstMatch(
                        new FermentRecipeInput(
                                new FluidStack(this.reactantFluidStorage1.variant, this.reactantFluidStorage1.amount)
                        ),
                        world
                ).orElse(null);

                if (canAcceptRecipeOutputFerment(recipeEntry)) {
                    if (this.progressTime == this.maxProgress) {
                        this.progressTime = 0;
                        this.maxProgress = getCookTime(world, this);
                        if (craftRecipeFerment(recipeEntry, this.inventory)) {
                            this.setLastRecipe(recipeEntry);
                        }
                        shouldMarkDirty = true;
                    }
                    this.progressTime++;
                } else {
                    this.progressTime = 0;
                }
            } else if (isAsh) {
                RecipeEntry<AshRecipe> recipeEntry = this.ashMatchGetter.getFirstMatch(
                        new AshRecipeInput(
                                this.getStack(ITEM_INPUT_INDICES[0])
                        ),
                        world
                ).orElse(null);

                if (canAcceptRecipeOutputAsh(recipeEntry)) {
                    if (this.progressTime == this.maxProgress) {
                        this.progressTime = 0;
                        this.maxProgress = getCookTime(world, this);
                        if (craftRecipeAsh(recipeEntry, this.inventory)) {
                            this.setLastRecipe(recipeEntry);
                        }
                        shouldMarkDirty = true;
                    }
                    this.progressTime++;
                } else {
                    this.progressTime = 0;
                }
            }
        } else if (this.progressTime > 0) {
            this.progressTime = MathHelper.clamp(this.progressTime - 2, 0, this.maxProgress);
        }

        if (shouldMarkDirty) {
            markDirty(world, pos, state);
        }

    }

    private boolean inputsEmpty() {
        return this.reactantFluidStorage1.isResourceBlank() && this.getStack(ITEM_INPUT_INDICES[0]).isEmpty();
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
                case 1: {
                    Item outputFluidItem = null;
                    if (this.productFluidStorage1.amount <= 0 || this.productFluidStorage1.variant.isBlank()) return;
                    if (this.getStack(FLUID_OUTPUT_INDICES[1]).getCount() >= this.getStack(FLUID_OUTPUT_INDICES[1]).getItem().getMaxCount()) return;
                    if (stack.isOf(Items.BUCKET)) {
                        if (this.productFluidStorage1.amount < 1000) return;
                        outputFluidItem = this.productFluidStorage1.variant.getFluid().getBucketItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[1]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[1]).isOf(outputFluidItem)) return;
                        this.productFluidStorage1.extract(this.productFluidStorage1.variant, BUCKET / 81, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    } else if (stack.isOf(ModItems.CONICAL_FLASK)) {
                        if (this.productFluidStorage1.amount < ((FlaskItem) stack.getItem()).capacity) return;
                        outputFluidItem = ((FlaskStorable) this.productFluidStorage1.variant.getFluid()).narchaotics$getConicalFlaskItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[1]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[1]).isOf(outputFluidItem)) return;
                        this.productFluidStorage1.extract(this.productFluidStorage1.variant, 250, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    } else if (stack.isOf(ModItems.ROUND_FLASK)) {
                        if (this.productFluidStorage1.amount < ((FlaskItem) stack.getItem()).capacity) return;
                        outputFluidItem = ((FlaskStorable) this.productFluidStorage1.variant.getFluid()).narchaotics$getRoundFlaskItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[1]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[1]).isOf(outputFluidItem)) return;
                        this.productFluidStorage1.extract(this.productFluidStorage1.variant, 250, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    } else if (stack.isOf(ModItems.BEAKER)) {
                        if (this.productFluidStorage1.amount < ((FlaskItem) stack.getItem()).capacity) return;
                        outputFluidItem = ((FlaskStorable) this.productFluidStorage1.variant.getFluid()).narchaotics$getBeakerItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[1]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[1]).isOf(outputFluidItem)) return;
                        this.productFluidStorage1.extract(this.productFluidStorage1.variant, 250, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    } else if (stack.isOf(ModItems.TEST_TUBE)) {
                        if (this.productFluidStorage1.amount < ((FlaskItem) stack.getItem()).capacity) return;
                        outputFluidItem = ((FlaskStorable) this.productFluidStorage1.variant.getFluid()).narchaotics$getTestTubeItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[1]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[1]).isOf(outputFluidItem)) return;
                        this.productFluidStorage1.extract(this.productFluidStorage1.variant, 50, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    }
                    if (outputFluidItem != null && this.insertStack(FLUID_OUTPUT_INDICES[1], outputFluidItem.getDefaultStack())) {
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
                        if (this.reactantFluidStorage1.amount > 3000) return;
                        this.reactantFluidStorage1.insert(FluidVariant.of(fluid), BUCKET / 81, transaction);
                        playFluidInsertSound(this.getWorld());
                        transaction.commit();
                        remainderStack = new ItemStack(Items.BUCKET);
                    } else if (stack.getItem() instanceof FlaskItem flaskItem) {
                        if (this.reactantFluidStorage1.amount > 4000 - flaskItem.capacity) return;
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
                case 1: {
                    ItemStack remainderStack = null;
                    if (this.getStack(FLUID_OUTPUT_INDICES[1]).getCount() >= this.getStack(FLUID_OUTPUT_INDICES[1]).getMaxCount()) return;
                    if (stack.getItem() instanceof BucketItem) {
                        if (this.productFluidStorage1.amount > 0) return;
                        this.productFluidStorage1.insert(FluidVariant.of(fluid), BUCKET / 81, transaction);
                        playFluidInsertSound(this.getWorld());
                        transaction.commit();
                        remainderStack = new ItemStack(Items.BUCKET);
                    } else if (stack.getItem() instanceof FlaskItem flaskItem) {
                        if (this.productFluidStorage1.amount > 1000 - flaskItem.capacity) return;
                        this.productFluidStorage1.insert(FluidVariant.of(fluid), flaskItem.capacity, transaction);
                        playFluidInsertSound(this.getWorld());
                        transaction.commit();
                        remainderStack = flaskItem.getRemainderStack();
                    }
                    if (remainderStack != null && this.insertStack(FLUID_OUTPUT_INDICES[1], remainderStack)) {
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

    private static int getCookTime(World world, FluidTankBlockEntity blockEntity) {
        FermentRecipeInput fermentRecipeInput = new FermentRecipeInput(
                new FluidStack(blockEntity.reactantFluidStorage1.variant, blockEntity.reactantFluidStorage1.amount)
        );
        AshRecipeInput ashRecipeInput = new AshRecipeInput(
                blockEntity.getStack(ITEM_INPUT_INDICES[0])
        );

        boolean isFerment = world.getBlockState(blockEntity.pos.offset(Direction.DOWN)).isOf(Blocks.MAGMA_BLOCK);
        boolean isAsh = world.getBlockState(blockEntity.pos.offset(Direction.DOWN)).isIn(BlockTags.CAMPFIRES);
        if (isFerment) {
            return (Integer) blockEntity.fermentMatchGetter
                    .getFirstMatch(fermentRecipeInput, world)
                    .map(recipe -> recipe.value().getFermentTime())
                    .orElse(120);
        } else if (isAsh) {
            return (Integer) blockEntity.ashMatchGetter
                    .getFirstMatch(ashRecipeInput, world)
                    .map(recipe -> recipe.value().getAshTime())
                    .orElse(120);
        } else {
            return 0;
        }
    }

    private boolean craftRecipeFerment(RecipeEntry<FermentRecipe> recipe, DefaultedList<ItemStack> inventory) {
        if (recipe != null && canAcceptRecipeOutputFerment(recipe)) {
            SingleVariantStorage<FluidVariant> inputSlotFluid = this.reactantFluidStorage1;
            FluidStack recipeOutput = recipe.value().output;
            SingleVariantStorage<FluidVariant> outputSlotFluid = this.productFluidStorage1;
            if (!recipeOutput.isEmpty()) {
                if (outputSlotFluid.isResourceBlank()) {
                    outputSlotFluid.variant = recipeOutput.variant();
                    outputSlotFluid.amount = recipeOutput.amount();
                } else if (outputSlotFluid.variant.equals(recipeOutput.variant())) {
                    outputSlotFluid.amount += recipeOutput.amount();
                }
                if (inputSlotFluid.amount > recipeOutput.amount()) {
                    inputSlotFluid.amount -= recipeOutput.amount();
                }
            }

            Ingredient recipeCatalyst = recipe.value().catalyst;
            ItemStack catalystSlotItem = inventory.get(ITEM_INPUT_INDICES[0]);
            if (!recipeCatalyst.isEmpty()) {
                if (!catalystSlotItem.isEmpty()) {
                    catalystSlotItem.decrement(1);
                }
            }

            markDirty();
            if (this.getWorld() != null) this.getWorld().updateListeners(pos, this.getCachedState(), this.getCachedState(), Block.NOTIFY_LISTENERS);
            return true;
        } else {
            return false;
        }
    }

    private boolean craftRecipeAsh(RecipeEntry<AshRecipe> recipe, DefaultedList<ItemStack> inventory) {
        if (recipe != null && canAcceptRecipeOutputAsh(recipe)) {
            ItemStack inputSlotItem = inventory.get(ITEM_INPUT_INDICES[0]);
            ItemStack recipeResult = recipe.value().output;
            ItemStack outputSlotItem = inventory.get(ITEM_OUTPUT_INDICES[0]);
            if (!recipeResult.isEmpty()) {
                if (outputSlotItem.isEmpty()) {
                    inventory.set(ITEM_OUTPUT_INDICES[0], recipeResult.copy());
                } else if (ItemStack.areItemsAndComponentsEqual(outputSlotItem, recipeResult)) {
                    outputSlotItem.increment(recipeResult.getCount());
                }
                if (!inputSlotItem.isEmpty()) {
                    inputSlotItem.decrement(1);
                }
            }

            markDirty();
            if (this.getWorld() != null) this.getWorld().updateListeners(pos, this.getCachedState(), this.getCachedState(), Block.NOTIFY_LISTENERS);
            return true;
        } else {
            return false;
        }
    }


    public boolean canAcceptRecipeOutputFerment(RecipeEntry<FermentRecipe> recipeEntry) {
        if (recipeEntry == null) return false;
        FermentRecipe recipe = recipeEntry.value();
        boolean canOutputFluid = canOutputFluid(recipe.output, this.productFluidStorage1, recipe.input, this.reactantFluidStorage1);
        boolean acceptableCatalyst = recipe.catalyst.test(inventory.get(ITEM_INPUT_INDICES[0]));
        return canOutputFluid && acceptableCatalyst;
    }
    public boolean canAcceptRecipeOutputAsh(RecipeEntry<AshRecipe> recipeEntry) {
        if (recipeEntry == null) return false;
        AshRecipe recipe = recipeEntry.value();
        boolean canOutputItem = canOutputItem(recipe.output, inventory.get(ITEM_OUTPUT_INDICES[0]), inventory.get(ITEM_OUTPUT_INDICES[0]).getMaxCount());
        return canOutputItem;
    }

    private static boolean canOutputItem(ItemStack recipeOutput, ItemStack slotStack, int maxCount) {
        if (recipeOutput.isEmpty()) return true;

        if (slotStack.isEmpty()) return true;

        if (!ItemStack.areItemsAndComponentsEqual(slotStack, recipeOutput)) return false;

        return slotStack.getCount() + recipeOutput.getCount() <= Math.min(maxCount, slotStack.getMaxCount());
    }

    private static boolean canOutputFluid(FluidStack recipeOutput, SingleVariantStorage<FluidVariant> outputTank, FluidStack recipeInput, SingleVariantStorage<FluidVariant> inputTank) {
        if (!recipeInput.isEmpty()) {
            if (!inputTank.getResource().equals(recipeInput.variant())) {
                return false;
            }

            if (inputTank.getAmount() < recipeInput.amount()) {
                return false;
            }
        }

        if (recipeOutput.isEmpty()) return true;

        if (!outputTank.getResource().equals(recipeOutput.variant()) && outputTank.getAmount() > 0) {
            return false;
        }

        long space = outputTank.getCapacity() - outputTank.getAmount();
        return space >= recipeOutput.amount();
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
