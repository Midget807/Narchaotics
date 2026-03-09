package net.midget807.narchaotics.block.entity;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.midget807.narchaotics.datagen.ModItemTagProvider;
import net.midget807.narchaotics.item.FlaskItem;
import net.midget807.narchaotics.recipe.FluidStack;
import net.midget807.narchaotics.recipe.SeparateRecipe;
import net.midget807.narchaotics.recipe.SeparateRecipeInput;
import net.midget807.narchaotics.registry.ModBlockEntities;
import net.midget807.narchaotics.registry.ModItems;
import net.midget807.narchaotics.registry.ModRecipes;
import net.midget807.narchaotics.screen.SeparateScreenHandler;
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
import static net.midget807.narchaotics.util.ModBlockUtil.PRODUCT_FLUID_AMOUNT_1_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.PRODUCT_FLUID_AMOUNT_2_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.PRODUCT_FLUID_VARIANT_1_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.PRODUCT_FLUID_VARIANT_2_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.PROGRESS_TIME_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.REACTANT_FLUID_AMOUNT_1_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.REACTANT_FLUID_VARIANT_1_KEY;
import static net.midget807.narchaotics.util.ModBlockUtil.RECIPES_USED_KEY;

public class SeparateWorkbenchBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory, RecipeUnlocker, RecipeInputProvider {
    public static final int PROGRESS_TIME_DELEGATE_INDEX = 0;
    public static final int MAX_PROGRESS_DELEGATE_INDEX = 1;
    public static final int ANIMATION_TIME_DELEGATE_INDEX = 2;
    public static final int REACTANT_FLUID_1_DELEGATE_INDEX = 3;
    public static final int PRODUCT_FLUID_1_DELEGATE_INDEX = 4;
    public static final int PRODUCT_FLUID_2_DELEGATE_INDEX = 5;
    public static final int[] INPUT_INDICES = {0, 1, 2};
    public static final int[] OUTPUT_INDICES = {3, 4, 5};
    public static final int[] FLUID_INPUT_INDICES = {0, 1, 2};
    public static final int[] FLUID_OUTPUT_INDICES = {3, 4, 5};
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);
    public SingleVariantStorage<FluidVariant> reactantFluidStorage1 = ModFluidUtil.createTank(this);
    public SingleVariantStorage<FluidVariant> productFluidStorage1 = ModFluidUtil.createTank(this);
    public SingleVariantStorage<FluidVariant> productFluidStorage2 = ModFluidUtil.createTank(this);
    private Item reactantItem1;
    private Item reactantItem2;
    private Item productItem1;
    private Item productItem2;
    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return (int) switch (index) {
                case 0 -> SeparateWorkbenchBlockEntity.this.progressTime;
                case 1 -> SeparateWorkbenchBlockEntity.this.maxProgress;
                case 2 -> SeparateWorkbenchBlockEntity.this.animationTime;
                case 3 -> SeparateWorkbenchBlockEntity.this.reactantFluidStorage1.amount;
                case 4 -> SeparateWorkbenchBlockEntity.this.productFluidStorage1.amount;
                case 5 -> SeparateWorkbenchBlockEntity.this.productFluidStorage2.amount;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0: SeparateWorkbenchBlockEntity.this.progressTime = value;
                case 1: SeparateWorkbenchBlockEntity.this.maxProgress = value;
                case 2: SeparateWorkbenchBlockEntity.this.animationTime = value;
                case 3: SeparateWorkbenchBlockEntity.this.reactantFluidStorage1.amount = value;
                case 4: SeparateWorkbenchBlockEntity.this.productFluidStorage1.amount = value;
                case 5: SeparateWorkbenchBlockEntity.this.productFluidStorage2.amount = value;
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
    private final Object2IntOpenHashMap<Identifier> recipeUsed = new Object2IntOpenHashMap<>();
    private final RecipeManager.MatchGetter<SeparateRecipeInput, SeparateRecipe> matchGetter = RecipeManager.createCachedMatchGetter(ModRecipes.SEPARATE_TYPE);

    public SeparateWorkbenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SEPARATE_WORKBENCH, pos, state);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
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

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.narchaotics.separate_workbench");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new SeparateScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
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
        if (!productFluidStorage2.isResourceBlank()) {
            FluidVariant.CODEC.encodeStart(NbtOps.INSTANCE, productFluidStorage2.variant).result().ifPresent(nbtElement -> nbt.put(PRODUCT_FLUID_VARIANT_2_KEY, nbtElement));
            nbt.putLong(PRODUCT_FLUID_AMOUNT_2_KEY, productFluidStorage2.amount);
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
        this.productFluidStorage1.variant = FluidVariant.CODEC.parse(NbtOps.INSTANCE, nbt.get(PRODUCT_FLUID_VARIANT_1_KEY)).result().orElse(FluidVariant.blank());
        this.productFluidStorage1.amount = nbt.getLong(PRODUCT_FLUID_AMOUNT_1_KEY);
        this.productFluidStorage2.variant = FluidVariant.CODEC.parse(NbtOps.INSTANCE, nbt.get(PRODUCT_FLUID_VARIANT_2_KEY)).result().orElse(FluidVariant.blank());
        this.productFluidStorage2.amount = nbt.getLong(PRODUCT_FLUID_AMOUNT_2_KEY);


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
            RecipeEntry<SeparateRecipe> recipeEntry = this.matchGetter.getFirstMatch(
                    new SeparateRecipeInput(
                            new FluidStack(this.reactantFluidStorage1.variant, this.reactantFluidStorage1.amount)
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

    private boolean craftRecipe(RecipeEntry<SeparateRecipe> recipe, DefaultedList<ItemStack> inventory) {
        if (recipe != null && canAcceptRecipeOutput(recipe)) {

            SingleVariantStorage<FluidVariant> inputSlotFluid1 = this.reactantFluidStorage1;
            FluidStack recipeProduct = recipe.value().output;
            FluidStack recipeRemainder = recipe.value().remainder;
            FluidStack recipeInput = recipe.value().input;
            SingleVariantStorage<FluidVariant> outputSlotFluid1 = this.productFluidStorage1;
            SingleVariantStorage<FluidVariant> outputSlotFluid2 = this.productFluidStorage2;
            boolean shouldDecrementInput = false;
            if (!recipeProduct.isEmpty()) {
                if (outputSlotFluid1.isResourceBlank()) {
                    outputSlotFluid1.variant = recipeProduct.variant();
                    outputSlotFluid1.amount = recipeProduct.amount();
                } else if (outputSlotFluid1.variant.equals(recipeProduct.variant())) {
                    outputSlotFluid1.amount += (long) (recipeProduct.amount());
                }

                if (!shouldDecrementInput) shouldDecrementInput = true;
            }

            if (!recipeRemainder.isEmpty()) {
                if (outputSlotFluid2.isResourceBlank()) {
                    outputSlotFluid2.variant = recipeRemainder.variant();
                    outputSlotFluid2.amount = recipeRemainder.amount();
                } else if (outputSlotFluid2.variant.equals(recipeRemainder.variant())) {
                    outputSlotFluid2.amount += (long) (recipeRemainder.amount());
                }

                if (!shouldDecrementInput) shouldDecrementInput = true;
            }
            if (shouldDecrementInput) {
                try (Transaction transaction = Transaction.openOuter()) {
                    this.reactantFluidStorage1.extract(this.reactantFluidStorage1.variant, recipeInput.amount(), transaction);
                    transaction.commit();
                }
            }

            markDirty();
            if (this.getWorld() != null) this.getWorld().updateListeners(pos, this.getCachedState(), this.getCachedState(), Block.NOTIFY_LISTENERS);
            return true;
        } else {
            return false;
        }
    }

    public boolean canAcceptRecipeOutput(RecipeEntry<SeparateRecipe> recipeEntry) {
        if (recipeEntry == null) return false;
        SeparateRecipe recipe = recipeEntry.value();
        boolean canOutputFluid1 = canOutputFluid(recipe.output, this.productFluidStorage1, recipe.input, this.reactantFluidStorage1);
        boolean canOutputFluid2 = canOutputFluid(recipe.remainder, this.productFluidStorage2, recipe.input, this.reactantFluidStorage1);

        return canOutputFluid1 && canOutputFluid2;
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
        return space >= (long) (recipeOutput.amount() / 2);
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
                case 2: {
                    Item outputFluidItem = null;
                    if (this.productFluidStorage2.amount <= 0 || this.productFluidStorage2.variant.isBlank()) return;
                    if (this.getStack(FLUID_OUTPUT_INDICES[2]).getCount() >= this.getStack(FLUID_OUTPUT_INDICES[2]).getItem().getMaxCount()) return;
                    if (stack.isOf(Items.BUCKET)) {
                        if (this.productFluidStorage2.amount < 1000) return;
                        outputFluidItem = this.productFluidStorage2.variant.getFluid().getBucketItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[2]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[2]).isOf(outputFluidItem)) return;
                        this.productFluidStorage2.extract(this.productFluidStorage2.variant, BUCKET / 81, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    } else if (stack.isOf(ModItems.CONICAL_FLASK)) {
                        if (this.productFluidStorage2.amount < ((FlaskItem) stack.getItem()).capacity) return;
                        outputFluidItem = ((FlaskStorable) this.productFluidStorage2.variant.getFluid()).narchaotics$getConicalFlaskItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[2]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[2]).isOf(outputFluidItem)) return;
                        this.productFluidStorage2.extract(this.productFluidStorage2.variant, 250, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    } else if (stack.isOf(ModItems.ROUND_FLASK)) {
                        if (this.productFluidStorage2.amount < ((FlaskItem) stack.getItem()).capacity) return;
                        outputFluidItem = ((FlaskStorable) this.productFluidStorage2.variant.getFluid()).narchaotics$getRoundFlaskItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[2]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[2]).isOf(outputFluidItem)) return;
                        this.productFluidStorage2.extract(this.productFluidStorage2.variant, 250, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    } else if (stack.isOf(ModItems.BEAKER)) {
                        if (this.productFluidStorage2.amount < ((FlaskItem) stack.getItem()).capacity) return;
                        outputFluidItem = ((FlaskStorable) this.productFluidStorage2.variant.getFluid()).narchaotics$getBeakerItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[2]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[2]).isOf(outputFluidItem)) return;
                        this.productFluidStorage2.extract(this.productFluidStorage2.variant, 250, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    } else if (stack.isOf(ModItems.TEST_TUBE)) {
                        if (this.productFluidStorage2.amount < ((FlaskItem) stack.getItem()).capacity) return;
                        outputFluidItem = ((FlaskStorable) this.productFluidStorage2.variant.getFluid()).narchaotics$getTestTubeItem();
                        if (!this.getStack(FLUID_OUTPUT_INDICES[2]).isEmpty() && !this.getStack(FLUID_OUTPUT_INDICES[2]).isOf(outputFluidItem)) return;
                        this.productFluidStorage2.extract(this.productFluidStorage2.variant, 50, transaction);
                        this.playFluidExtractSound(this.getWorld());
                        transaction.commit();
                    }
                    if (outputFluidItem != null && this.insertStack(FLUID_OUTPUT_INDICES[2], outputFluidItem.getDefaultStack())) {
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
                case 2: {
                    ItemStack remainderStack = null;
                    if (this.getStack(FLUID_OUTPUT_INDICES[2]).getCount() >= this.getStack(FLUID_OUTPUT_INDICES[2]).getMaxCount()) return;
                    if (stack.getItem() instanceof BucketItem) {
                        if (this.productFluidStorage2.amount > 0) return;
                        this.productFluidStorage2.insert(FluidVariant.of(fluid), BUCKET / 81, transaction);
                        playFluidInsertSound(this.getWorld());
                        transaction.commit();
                        remainderStack = new ItemStack(Items.BUCKET);
                    } else if (stack.getItem() instanceof FlaskItem flaskItem) {
                        if (this.productFluidStorage2.amount > 1000 - flaskItem.capacity) return;
                        this.productFluidStorage2.insert(FluidVariant.of(fluid), flaskItem.capacity, transaction);
                        playFluidInsertSound(this.getWorld());
                        transaction.commit();
                        remainderStack = flaskItem.getRemainderStack();
                    }
                    if (remainderStack != null && this.insertStack(FLUID_OUTPUT_INDICES[2], remainderStack)) {
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

    private static int getCookTime(World world, SeparateWorkbenchBlockEntity blockEntity) {
        SeparateRecipeInput separateRecipeInput = new SeparateRecipeInput(
                new FluidStack(blockEntity.reactantFluidStorage1.variant, blockEntity.reactantFluidStorage1.amount)
        );
        return (Integer) blockEntity.matchGetter
                .getFirstMatch(separateRecipeInput, world)
                .map(recipe -> recipe.value().getSeparateTime())
                .orElse(120);
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
        return side == Direction.DOWN && (slot == FLUID_OUTPUT_INDICES[1] || slot == FLUID_OUTPUT_INDICES[1]);
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return this.isValid(slot, stack);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.DOWN) {
            return new int[]{FLUID_INPUT_INDICES[1], FLUID_OUTPUT_INDICES[1]};
        } else {
            return FLUID_INPUT_INDICES;
        }
    }
}
