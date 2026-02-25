package net.midget807.narchaotics.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.midget807.narchaotics.registry.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

public class DistillationRecipe implements Recipe<DistillationRecipeInput>, MixedOutputRecipe<DistillationRecipeInput> {
    public final Ingredient item1;
    public final Ingredient item2;
    public final FluidStack fluid1;
    public final FluidStack fluid2;
    public final Ingredient fuelType;
    private final int cookingTime;
    public final ItemStack result1;
    public final ItemStack result2;
    public final FluidStack product1;
    public final FluidStack product2;

    public DistillationRecipe(Ingredient item1, Ingredient item2, FluidStack fluid1, FluidStack fluid2, Ingredient fuelType, int cookingTime, ItemStack result1, ItemStack result2, FluidStack product1, FluidStack product2) {
        this.item1 = item1;
        this.item2 = item2;
        this.fluid1 = fluid1;
        this.fluid2 = fluid2;
        this.fuelType = fuelType;
        this.cookingTime = cookingTime;
        this.result1 = result1;
        this.result2 = result2;
        this.product1 = product1;
        this.product2 = product2;
    }

    @Override
    public boolean matches(DistillationRecipeInput input, World world) {
        return this.item1.test(input.item1())
                && this.item2.test(input.item2())
                && this.fluid1.variant().getFluid().matchesType(input.fluid1().variant().getFluid())
                && this.fluid2.variant().getFluid().matchesType(input.fluid2().variant().getFluid())
                && this.fuelType.test(input.fuelType());
    }

    @Override
    public ItemStack craft(DistillationRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return ItemStack.EMPTY;
    }

    @Override
    public DefaultedList<MixedStack> craftMultiple(DistillationRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        DefaultedList<MixedStack> defaultedList = DefaultedList.of();
        defaultedList.add(new ItemStackHolder(this.result1.copy()));
        defaultedList.add(new ItemStackHolder(this.result2.copy()));
        defaultedList.add(new FluidStackHolder(this.product1.copy()));
        defaultedList.add(new FluidStackHolder(this.product2.copy()));
        return defaultedList;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return ItemStack.EMPTY;
    }

    @Override
    public DefaultedList<MixedStack> getMultipleResults(RegistryWrapper.WrapperLookup lookup) {
        DefaultedList<MixedStack> defaultedList = DefaultedList.of();
        defaultedList.add(new ItemStackHolder(this.result1));
        defaultedList.add(new ItemStackHolder(this.result2));
        defaultedList.add(new FluidStackHolder(this.product1));
        defaultedList.add(new FluidStackHolder(this.product2));
        return defaultedList;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.DISTILLATION_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.DISTILLATION_TYPE;
    }

    public static class Serializer implements RecipeSerializer<DistillationRecipe> {
        private static final MapCodec<DistillationRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("item1", Ingredient.EMPTY).forGetter(recipe -> recipe.item1),
                        Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("item2", Ingredient.EMPTY).forGetter(recipe -> recipe.item2),
                        FluidStack.CODEC.optionalFieldOf("fluid1", new FluidStack(FluidVariant.blank(), 0)).forGetter(recipe -> recipe.fluid1),
                        FluidStack.CODEC.optionalFieldOf("fluid2", new FluidStack(FluidVariant.blank(), 0)).forGetter(recipe -> recipe.fluid2),
                        Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("fuel_type", Ingredient.EMPTY).forGetter(recipe -> recipe.fuelType),
                        Codecs.NONNEGATIVE_INT.fieldOf("cooking_time").forGetter(recipe -> recipe.cookingTime),
                        ItemStack.CODEC.optionalFieldOf("result1", ItemStack.EMPTY).forGetter(recipe -> recipe.result1),
                        ItemStack.CODEC.optionalFieldOf("result2", ItemStack.EMPTY).forGetter(recipe -> recipe.result2),
                        FluidStack.CODEC.optionalFieldOf("product1", new FluidStack(FluidVariant.blank(), 0)).forGetter(recipe -> recipe.product1),
                        FluidStack.CODEC.optionalFieldOf("product2", new FluidStack(FluidVariant.blank(), 0)).forGetter(recipe -> recipe.product2)
                ).apply(instance, DistillationRecipe::new)
        );
        public static final PacketCodec<RegistryByteBuf, DistillationRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                DistillationRecipe.Serializer::write, DistillationRecipe.Serializer::read
        );

        @Override
        public MapCodec<DistillationRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, DistillationRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static DistillationRecipe read(RegistryByteBuf buf) {
            Ingredient item1 = Ingredient.EMPTY;
            if (buf.readBoolean()) item1 = Ingredient.PACKET_CODEC.decode(buf);
            Ingredient item2 = Ingredient.EMPTY;
            if (buf.readBoolean()) item2 = Ingredient.PACKET_CODEC.decode(buf);
            FluidStack fluid1 = FluidStack.EMPTY;
            if (buf.readBoolean()) fluid1 = FluidStack.PACKET_CODEC.decode(buf);
            FluidStack fluid2 = FluidStack.EMPTY;
            if (buf.readBoolean()) fluid2 = FluidStack.PACKET_CODEC.decode(buf);
            Ingredient fuelType = Ingredient.EMPTY;
            if (buf.readBoolean()) fuelType = Ingredient.PACKET_CODEC.decode(buf);

            int cookingTime = PacketCodecs.INTEGER.decode(buf);

            ItemStack result1 = ItemStack.EMPTY;
            if (buf.readBoolean()) result1 = ItemStack.PACKET_CODEC.decode(buf);
            ItemStack result2 = ItemStack.EMPTY;
            if (buf.readBoolean()) result2 = ItemStack.PACKET_CODEC.decode(buf);
            FluidStack product1 = FluidStack.EMPTY;
            if (buf.readBoolean()) product1 = FluidStack.PACKET_CODEC.decode(buf);
            FluidStack product2 = FluidStack.EMPTY;
            if (buf.readBoolean()) product2 = FluidStack.PACKET_CODEC.decode(buf);
            return new DistillationRecipe(item1, item2, fluid1, fluid2, fuelType, cookingTime, result1, result2, product1, product2);
        }

        private static void write(RegistryByteBuf buf, DistillationRecipe recipe) {
            buf.writeBoolean(!recipe.item1.isEmpty());
            if (!recipe.item1.isEmpty()) Ingredient.PACKET_CODEC.encode(buf, recipe.item1);
            buf.writeBoolean(!recipe.item2.isEmpty());
            if (!recipe.item2.isEmpty()) Ingredient.PACKET_CODEC.encode(buf, recipe.item2);
            buf.writeBoolean(!recipe.fluid1.isEmpty());
            if (!recipe.fluid1.isEmpty()) FluidStack.PACKET_CODEC.encode(buf, recipe.fluid1);
            buf.writeBoolean(!recipe.fluid2.isEmpty());
            if (!recipe.fluid2.isEmpty()) FluidStack.PACKET_CODEC.encode(buf, recipe.fluid2);
            buf.writeBoolean(!recipe.fuelType.isEmpty());
            if (!recipe.fuelType.isEmpty()) Ingredient.PACKET_CODEC.encode(buf, recipe.fuelType);

            PacketCodecs.INTEGER.encode(buf, recipe.cookingTime);

            buf.writeBoolean(!recipe.result1.isEmpty());
            if (!recipe.result1.isEmpty()) ItemStack.PACKET_CODEC.encode(buf, recipe.result1);
            buf.writeBoolean(!recipe.result2.isEmpty());
            if (!recipe.result2.isEmpty()) ItemStack.PACKET_CODEC.encode(buf, recipe.result2);
            buf.writeBoolean(!recipe.product1.isEmpty());
            if (!recipe.product1.isEmpty()) FluidStack.PACKET_CODEC.encode(buf, recipe.product1);
            buf.writeBoolean(!recipe.product2.isEmpty());
            if (!recipe.product2.isEmpty()) FluidStack.PACKET_CODEC.encode(buf, recipe.product2);
        }
    }
}
