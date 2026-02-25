package net.midget807.narchaotics.registry;

import net.midget807.narchaotics.NarchaoticsMain;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import static net.midget807.narchaotics.registry.ModCustomCauldronBehaviours.*;

public class ModCauldronBehaviors {
    public static final Map<Item, Block> FILL_CAULDRON = new HashMap<>();
    public static final Map<CauldronBehavior.CauldronBehaviorMap, Item> REFILL_BUCKET = new HashMap<>();


    public static void registerModCauldronBehaviors() {
        initFillCauldron();
        initRefillBuckets();
        addCustomFluids();
        NarchaoticsMain.LOGGER.info("Registering Mod Cauldron Behaviors");
    }

    private static void initFillCauldron() {
        FILL_CAULDRON.put(ModItems.DIRTY_SOLUTION_BUCKET, ModBlocks.DIRTY_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.ETHANOL_BUCKET, ModBlocks.ETHANOL_CAULDRON);
        FILL_CAULDRON.put(ModItems.SALT_WATER_BUCKET, ModBlocks.SALT_WATER_CAULDRON);
        FILL_CAULDRON.put(ModItems.VOLCANIC_WATER_BUCKET, ModBlocks.VOLCANIC_WATER_CAULDRON);
        FILL_CAULDRON.put(ModItems.NETHERRACK_SOLUTION_BUCKET, ModBlocks.NETHERRACK_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.RED_PHOSPHORUS_SOLUTION_BUCKET, ModBlocks.RED_PHOSPHORUS_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.RED_PHOSPHORUS_BUCKET, ModBlocks.RED_PHOSPHORUS_CAULDRON);
        FILL_CAULDRON.put(ModItems.DIRTY_ASH_SOLUTION_BUCKET, ModBlocks.DIRTY_ASH_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.CLEAN_ASH_SOLUTION_BUCKET, ModBlocks.CLEAN_ASH_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.DIRTY_IODINE_SOLUTION_BUCKET, ModBlocks.DIRTY_IODINE_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.CLEAN_IODINE_SOLUTION_BUCKET, ModBlocks.CLEAN_IODINE_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.IODINE_BUCKET, ModBlocks.IODINE_CAULDRON);
        FILL_CAULDRON.put(ModItems.SALT_SOLUTION_BUCKET, ModBlocks.SALT_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.CRYSTALISED_SALT_SOLUTION_BUCKET, ModBlocks.CRYSTALISED_SALT_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.SODIUM_CARBONATE_SOLUTION_BUCKET, ModBlocks.SODIUM_CARBONATE_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.SODIUM_CARBONATE_BUCKET, ModBlocks.SODIUM_CARBONATE_CAULDRON);
        FILL_CAULDRON.put(ModItems.AMMONIA_SLUDGE_BUCKET, ModBlocks.AMMONIA_SLUDGE_CAULDRON);
        FILL_CAULDRON.put(ModItems.AMMONIA_SOLUTION_BUCKET, ModBlocks.AMMONIA_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.AMMONIA_BUCKET, ModBlocks.AMMONIA_CAULDRON);
        FILL_CAULDRON.put(ModItems.HYDROGEN_PEROXIDE_BUCKET, ModBlocks.HYDROGEN_PEROXIDE_CAULDRON);
        FILL_CAULDRON.put(ModItems.BRINE_BUCKET, ModBlocks.BRINE_CAULDRON);
        FILL_CAULDRON.put(ModItems.CONCENTRATED_VOLCANIC_WATER_BUCKET, ModBlocks.CONCENTRATED_VOLCANIC_WATER_CAULDRON);
        FILL_CAULDRON.put(ModItems.SULPHURIC_ACID_SOLUTION_BUCKET, ModBlocks.SULPHURIC_ACID_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.SULPHURIC_ACID_BUCKET, ModBlocks.SULPHURIC_ACID_CAULDRON);
        FILL_CAULDRON.put(ModItems.HYDRAZINE_SOLUTION_BUCKET, ModBlocks.HYDRAZINE_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.HYDRAZINE_BUCKET, ModBlocks.HYDRAZINE_CAULDRON);
        FILL_CAULDRON.put(ModItems.HYDROIODIC_ACID_BUCKET, ModBlocks.HYDROIODIC_ACID_CAULDRON);
        FILL_CAULDRON.put(ModItems.EPHEDRA_SLUDGE_BUCKET, ModBlocks.EPHEDRA_SLUDGE_CAULDRON);
        FILL_CAULDRON.put(ModItems.DIRTY_EPHEDRA_SOLUTION_BUCKET, ModBlocks.DIRTY_EPHEDRA_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.CLEAN_EPHEDRA_SOLUTION_BUCKET, ModBlocks.CLEAN_EPHEDRA_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.CONCENTRATED_EPHEDRA_SOLUTION_BUCKET, ModBlocks.CONCENTRATED_EPHEDRA_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.ALKALINE_EPHEDRA_SOLUTION_BUCKET, ModBlocks.ALKALINE_EPHEDRA_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.EPHEDRINE_SOLUTION_BUCKET, ModBlocks.EPHEDRINE_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.PSEUDOEPHEDRINE_BUCKET, ModBlocks.PSEUDOEPHEDRINE_CAULDRON);
        FILL_CAULDRON.put(ModItems.METHAMPHETAMINE_ACID_SOLUTION_BUCKET, ModBlocks.METHAMPHETAMINE_ACID_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.METHAMPHETAMINE_SOLUTION_BUCKET, ModBlocks.METHAMPHETAMINE_SOLUTION_CAULDRON);
        FILL_CAULDRON.put(ModItems.METHAMPHETAMINE_BUCKET, ModBlocks.METHAMPHETAMINE_CAULDRON);
    }

    private static void initRefillBuckets() {
        REFILL_BUCKET.put(DIRTY_SOLUTION_CAULDRON_BEHAVIOR, ModItems.DIRTY_SOLUTION_BUCKET);
        REFILL_BUCKET.put(ETHANOL_CAULDRON_BEHAVIOR, ModItems.ETHANOL_BUCKET);
        REFILL_BUCKET.put(SALT_WATER_CAULDRON_BEHAVIOR, ModItems.SALT_WATER_BUCKET);
        REFILL_BUCKET.put(VOLCANIC_WATER_CAULDRON_BEHAVIOR, ModItems.VOLCANIC_WATER_BUCKET);
        REFILL_BUCKET.put(NETHERRACK_SOLUTION_CAULDRON_BEHAVIOR, ModItems.NETHERRACK_SOLUTION_BUCKET);
        REFILL_BUCKET.put(RED_PHOSPHORUS_SOLUTION_CAULDRON_BEHAVIOR, ModItems.RED_PHOSPHORUS_SOLUTION_BUCKET);
        REFILL_BUCKET.put(RED_PHOSPHORUS_CAULDRON_BEHAVIOR, ModItems.RED_PHOSPHORUS_BUCKET);
        REFILL_BUCKET.put(DIRTY_ASH_SOLUTION_CAULDRON_BEHAVIOR, ModItems.DIRTY_ASH_SOLUTION_BUCKET);
        REFILL_BUCKET.put(CLEAN_ASH_SOLUTION_CAULDRON_BEHAVIOR, ModItems.CLEAN_ASH_SOLUTION_BUCKET);
        REFILL_BUCKET.put(DIRTY_IODINE_SOLUTION_CAULDRON_BEHAVIOR, ModItems.DIRTY_IODINE_SOLUTION_BUCKET);
        REFILL_BUCKET.put(CLEAN_IODINE_SOLUTION_CAULDRON_BEHAVIOR, ModItems.CLEAN_IODINE_SOLUTION_BUCKET);
        REFILL_BUCKET.put(IODINE_CAULDRON_BEHAVIOR, ModItems.IODINE_BUCKET);
        REFILL_BUCKET.put(SALT_SOLUTION_CAULDRON_BEHAVIOR, ModItems.SALT_SOLUTION_BUCKET);
        REFILL_BUCKET.put(CRYSTALISED_SALT_SOLUTION_CAULDRON_BEHAVIOR, ModItems.CRYSTALISED_SALT_SOLUTION_BUCKET);
        REFILL_BUCKET.put(SODIUM_CARBONATE_SOLUTION_CAULDRON_BEHAVIOR, ModItems.SODIUM_CARBONATE_SOLUTION_BUCKET);
        REFILL_BUCKET.put(SODIUM_CARBONATE_CAULDRON_BEHAVIOR, ModItems.SODIUM_CARBONATE_BUCKET);
        REFILL_BUCKET.put(AMMONIA_SLUDGE_CAULDRON_BEHAVIOR, ModItems.AMMONIA_SLUDGE_BUCKET);
        REFILL_BUCKET.put(AMMONIA_SOLUTION_CAULDRON_BEHAVIOR, ModItems.AMMONIA_SOLUTION_BUCKET);
        REFILL_BUCKET.put(AMMONIA_CAULDRON_BEHAVIOR, ModItems.AMMONIA_BUCKET);
        REFILL_BUCKET.put(HYDROGEN_PEROXIDE_CAULDRON_BEHAVIOR, ModItems.HYDROGEN_PEROXIDE_BUCKET);
        REFILL_BUCKET.put(BRINE_CAULDRON_BEHAVIOR, ModItems.BRINE_BUCKET);
        REFILL_BUCKET.put(CONCENTRATED_VOLCANIC_WATER_CAULDRON_BEHAVIOR, ModItems.CONCENTRATED_VOLCANIC_WATER_BUCKET);
        REFILL_BUCKET.put(SULPHURIC_ACID_SOLUTION_CAULDRON_BEHAVIOR, ModItems.SULPHURIC_ACID_SOLUTION_BUCKET);
        REFILL_BUCKET.put(SULPHURIC_ACID_CAULDRON_BEHAVIOR, ModItems.SULPHURIC_ACID_BUCKET);
        REFILL_BUCKET.put(HYDRAZINE_SOLUTION_CAULDRON_BEHAVIOR, ModItems.HYDRAZINE_SOLUTION_BUCKET);
        REFILL_BUCKET.put(HYDRAZINE_CAULDRON_BEHAVIOR, ModItems.HYDRAZINE_BUCKET);
        REFILL_BUCKET.put(HYDROIODIC_ACID_CAULDRON_BEHAVIOR, ModItems.HYDROIODIC_ACID_BUCKET);
        REFILL_BUCKET.put(EPHEDRA_SLUDGE_CAULDRON_BEHAVIOR, ModItems.EPHEDRA_SLUDGE_BUCKET);
        REFILL_BUCKET.put(DIRTY_EPHEDRA_SOLUTION_CAULDRON_BEHAVIOR, ModItems.DIRTY_EPHEDRA_SOLUTION_BUCKET);
        REFILL_BUCKET.put(CLEAN_EPHEDRA_SOLUTION_CAULDRON_BEHAVIOR, ModItems.CLEAN_EPHEDRA_SOLUTION_BUCKET);
        REFILL_BUCKET.put(CONCENTRATED_EPHEDRA_SOLUTION_CAULDRON_BEHAVIOR, ModItems.CONCENTRATED_EPHEDRA_SOLUTION_BUCKET);
        REFILL_BUCKET.put(ALKALINE_EPHEDRA_SOLUTION_CAULDRON_BEHAVIOR, ModItems.ALKALINE_EPHEDRA_SOLUTION_BUCKET);
        REFILL_BUCKET.put(EPHEDRINE_SOLUTION_CAULDRON_BEHAVIOR, ModItems.EPHEDRINE_SOLUTION_BUCKET);
        REFILL_BUCKET.put(PSEUDOEPHEDRINE_CAULDRON_BEHAVIOR, ModItems.PSEUDOEPHEDRINE_BUCKET);
        REFILL_BUCKET.put(METHAMPHETAMINE_ACID_SOLUTION_CAULDRON_BEHAVIOR, ModItems.METHAMPHETAMINE_ACID_SOLUTION_BUCKET);
        REFILL_BUCKET.put(METHAMPHETAMINE_SOLUTION_CAULDRON_BEHAVIOR, ModItems.METHAMPHETAMINE_SOLUTION_BUCKET);
        REFILL_BUCKET.put(METHAMPHETAMINE_CAULDRON_BEHAVIOR, ModItems.METHAMPHETAMINE_BUCKET);
    }

    private static void addCustomFluids() {
        Map<Item, CauldronBehavior> map = CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map();
        ModCauldronBehaviors.FILL_CAULDRON.forEach((item, block) -> {
            map.put(
                    item,
                    (state, world, pos, player, hand, stack) -> {
                        if (!world.isClient) {
                            Item stackItem = stack.getItem();
                            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.BUCKET)));
                            player.incrementStat(Stats.FILL_CAULDRON);
                            player.incrementStat(Stats.USED.getOrCreateStat(stackItem));
                            world.setBlockState(pos, block.getDefaultState());
                            world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                        }
                        return ItemActionResult.success(world.isClient);
                    }
            );
        });
        ModCauldronBehaviors.REFILL_BUCKET.forEach((cauldronBehaviorMap, item) -> {
            cauldronBehaviorMap.map().put(
                    Items.BUCKET,
                    (state, world, pos, player, hand, stack) -> emptyCauldron(
                            state, world, pos, player, hand, stack, new ItemStack(item), statex -> true, SoundEvents.ITEM_BUCKET_FILL
                    )
            );
        });
    }

    private static void emptyCauldron(World world, BlockPos pos, SoundEvent soundEvent) {
        world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
        world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }
    private static ItemActionResult emptyCauldron(
            BlockState state,
            World world,
            BlockPos pos,
            PlayerEntity player,
            Hand hand,
            ItemStack stack,
            ItemStack output,
            Predicate<BlockState> fullPredicate,
            SoundEvent soundEvent
    ) {
        if (!fullPredicate.test(state)) {
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        } else {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, output));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
            }

            return ItemActionResult.success(world.isClient);
        }
    }
}
