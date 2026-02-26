package net.midget807.narchaotics.recipe;

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

public class EvaporateRecipe implements Recipe<EvaporateRecipeInput>, MixedOutputRecipe<EvaporateRecipeInput> {
    public final FluidStack input;
    public final Ingredient fuelType;
    private final int evaporateTime;
    public final ItemStack output;

    public EvaporateRecipe(FluidStack input, Ingredient fuelType, int evaporateTime, ItemStack output) {
        this.input = input;
        this.fuelType = fuelType;
        this.evaporateTime = evaporateTime;
        this.output = output;
    }

    @Override
    public boolean matches(EvaporateRecipeInput input, World world) {
        return this.input.variant().getFluid().matchesType(input.input().variant().getFluid())
                && this.fuelType.test(input.fuelType());
    }

    @Override
    public ItemStack craft(EvaporateRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public DefaultedList<MixedStack> craftMultiple(EvaporateRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return DefaultedList.of();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.output;
    }

    @Override
    public DefaultedList<MixedStack> getMultipleResults(RegistryWrapper.WrapperLookup lookup) {
        return DefaultedList.of();
    }

    public int getEvaporateTime() {
        return this.evaporateTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.EVAPORATE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.EVAPORATE_TYPE;
    }

    public static class Serializer implements RecipeSerializer<EvaporateRecipe> {
        private static final MapCodec<EvaporateRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        FluidStack.CODEC.fieldOf("input").forGetter(recipe -> recipe.input),
                        Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("fuel_type", Ingredient.EMPTY).forGetter(recipe -> recipe.fuelType),
                        Codecs.NONNEGATIVE_INT.fieldOf("evaporateTime").forGetter(recipe -> recipe.evaporateTime),
                        ItemStack.CODEC.optionalFieldOf("output", ItemStack.EMPTY).forGetter(recipe -> recipe.output)
                ).apply(instance, EvaporateRecipe::new)
        );
        public static final PacketCodec<RegistryByteBuf, EvaporateRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                EvaporateRecipe::write, EvaporateRecipe::read
        );

        @Override
        public MapCodec<EvaporateRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, EvaporateRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }

    private static EvaporateRecipe read(RegistryByteBuf buf) {
        FluidStack input = FluidStack.PACKET_CODEC.decode(buf);

        Ingredient fuelType = Ingredient.EMPTY;
        if (buf.readBoolean()) fuelType = Ingredient.PACKET_CODEC.decode(buf);

        int evaporateTime = PacketCodecs.INTEGER.decode(buf);

        ItemStack output = ItemStack.PACKET_CODEC.decode(buf);
        return new EvaporateRecipe(input, fuelType, evaporateTime, output);
    }

    private static void write(RegistryByteBuf buf, EvaporateRecipe recipe) {
        FluidStack.PACKET_CODEC.encode(buf, recipe.input);

        buf.writeBoolean(!recipe.fuelType.isEmpty());
        if (!recipe.fuelType.isEmpty()) Ingredient.PACKET_CODEC.encode(buf, recipe.fuelType);

        PacketCodecs.INTEGER.encode(buf, recipe.evaporateTime);

        ItemStack.PACKET_CODEC.encode(buf, recipe.output);
    }
}
