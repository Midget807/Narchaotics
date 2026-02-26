package net.midget807.narchaotics.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

public class DissolveRecipe implements Recipe<DissolveRecipeInput>, MixedOutputRecipe<DissolveRecipeInput> {
    public final Ingredient item1;
    public final Ingredient item2;
    public final FluidStack fluid1;
    public final FluidStack fluid2;
    public final Ingredient fuelType;
    private final int dissolveTime;
    public final FluidStack product;

    public DissolveRecipe(Ingredient item1, Ingredient item2, FluidStack fluid1, FluidStack fluid2, Ingredient fuelType, int dissolveTime, FluidStack product) {
        this.item1 = item1;
        this.item2 = item2;
        this.fluid1 = fluid1;
        this.fluid2 = fluid2;
        this.fuelType = fuelType;
        this.dissolveTime = dissolveTime;
        this.product = product;
    }

    @Override
    public boolean matches(DissolveRecipeInput input, World world) {
        return this.item1.test(input.item1())
                && this.item2.test(input.item2())
                && this.fluid1.variant().getFluid().matchesType(input.fluid1().variant().getFluid())
                && this.fluid2.variant().getFluid().matchesType(input.fluid2().variant().getFluid())
                && this.fuelType.test(input.fuelType());
    }

    @Override
    public ItemStack craft(DissolveRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return ItemStack.EMPTY;
    }

    @Override
    public DefaultedList<MixedStack> craftMultiple(DissolveRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        DefaultedList<MixedStack> defaultedList = DefaultedList.of();
        defaultedList.add(new FluidStackHolder(this.product.copy()));
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
        defaultedList.add(new FluidStackHolder(this.product));
        return defaultedList;
    }

    public int getDissolveTime() {
        return this.dissolveTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.DISSOLVE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.DISSOLVE_TYPE;
    }

    public static class Serializer implements RecipeSerializer<DissolveRecipe> {
        private static final MapCodec<DissolveRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("item1", Ingredient.EMPTY).forGetter(recipe -> recipe.item1),
                        Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("item2", Ingredient.EMPTY).forGetter(recipe -> recipe.item2),
                        FluidStack.CODEC.optionalFieldOf("fluid1", FluidStack.EMPTY).forGetter(recipe -> recipe.fluid1),
                        FluidStack.CODEC.optionalFieldOf("fluid2", FluidStack.EMPTY).forGetter(recipe -> recipe.fluid2),
                        Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("fuel_type", Ingredient.EMPTY).forGetter(recipe -> recipe.fuelType),
                        Codecs.NONNEGATIVE_INT.fieldOf("cooking_time").forGetter(recipe -> recipe.dissolveTime),
                        FluidStack.CODEC.fieldOf("product").forGetter(recipe -> recipe.product)
                ).apply(instance, DissolveRecipe::new)
        );
        public static final PacketCodec<RegistryByteBuf, DissolveRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                DissolveRecipe::write, DissolveRecipe::read
        );


        @Override
        public MapCodec<DissolveRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, DissolveRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }

    private static DissolveRecipe read(RegistryByteBuf buf) {
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

        int dissolveTime = PacketCodecs.INTEGER.decode(buf);

        FluidStack product = FluidStack.PACKET_CODEC.decode(buf);
        return new DissolveRecipe(item1, item2, fluid1, fluid2, fuelType, dissolveTime, product);
    }

    private static void write(RegistryByteBuf buf, DissolveRecipe recipe) {
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

        PacketCodecs.INTEGER.encode(buf, recipe.dissolveTime);

        FluidStack.PACKET_CODEC.encode(buf, recipe.product);
    }
}
