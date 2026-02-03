package net.midget807.narchaotics.registry;

import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.util.ModUtil;
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
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Predicate;

import static net.midget807.narchaotics.registry.ModCustomCauldronBehaviours.ETHANOL_CAULDRON_BEHAVIOR;

public class ModCauldronBehaviors {
    public static final Map<Item, Block> FILL_CAULDRON = Map.of(
            ModItems.ETHANOL_BUCKET, ModBlocks.ETHANOL_CAULDRON
    );
    public static final Map<CauldronBehavior.CauldronBehaviorMap, Item> REFILL_BUCKET = Map.of(
            ETHANOL_CAULDRON_BEHAVIOR, ModItems.ETHANOL_BUCKET
    );


    public static void registerModCauldronBehaviors() {
        addCustomFluids();
        NarchaoticsMain.LOGGER.info("Registering Mod Cauldron Behaviors");
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
