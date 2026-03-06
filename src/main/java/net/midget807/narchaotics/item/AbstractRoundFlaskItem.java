package net.midget807.narchaotics.item;

import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;

public class AbstractRoundFlaskItem extends FlaskItem {
    public AbstractRoundFlaskItem(Fluid fluid, Item remainder, Settings settings) {
        super(fluid, remainder, 250, settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
        if (itemStack.isOf(ModItems.ROUND_FLASK)) {
            if (blockHitResult.getType() == HitResult.Type.MISS) {
                return TypedActionResult.pass(itemStack);
            } else {
                if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                    BlockPos blockPos = blockHitResult.getBlockPos();
                    if (!world.canPlayerModifyAt(user, blockPos)) {
                        return TypedActionResult.pass(itemStack);
                    }

                    if (world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
                        world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        world.emitGameEvent(user, GameEvent.FLUID_PICKUP, blockPos);
                        return TypedActionResult.success(this.fill(itemStack, user, blockPos, world));
                    }
                }
                return TypedActionResult.pass(itemStack);
            }
        } else  {
            return TypedActionResult.pass(itemStack);
        }
    }

    private ItemStack fill(ItemStack itemStack, PlayerEntity user, BlockPos blockPos, World world) {
        RegistryEntry<Biome> biome = world.getBiome(blockPos);
        if (biome.isIn(BiomeTags.IS_MOUNTAIN)) {
            ItemStack result = new ItemStack(ModItems.VOLCANIC_WATER_ROUND_FLASK);
            return ItemUsage.exchangeStack(itemStack, user, result);
        } else if (biome.isIn(BiomeTags.IS_BEACH) || biome.isIn(BiomeTags.IS_OCEAN)) {
            ItemStack result = new ItemStack(ModItems.SALT_WATER_ROUND_FLASK);
            return ItemUsage.exchangeStack(itemStack, user, result);
        } else {
            ItemStack result = new ItemStack(ModItems.WATER_ROUND_FLASK);
            return ItemUsage.exchangeStack(itemStack, user, result);
        }
    }
}
