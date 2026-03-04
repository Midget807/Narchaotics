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

public class FermentRecipe implements Recipe<FermentRecipeInput>, MixedOutputRecipe<FermentRecipeInput> {
    public final FluidStack input;
    public final Ingredient catalyst;
    private final int fermentTime;
    public final FluidStack output;

    public FermentRecipe(FluidStack input, Ingredient catalyst, int fermentTime, FluidStack output) {
        this.input = input;
        this.catalyst = catalyst;
        this.fermentTime = fermentTime;
        this.output = output;
    }

    @Override
    public boolean matches(FermentRecipeInput input, World world) {
        return this.input.variant().getFluid().matchesType(input.input().variant().getFluid());
    }

    @Override
    public ItemStack craft(FermentRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return ItemStack.EMPTY;
    }

    @Override
    public DefaultedList<MixedStack> craftMultiple(FermentRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        DefaultedList<MixedStack> defaultedList = DefaultedList.of();
        defaultedList.add(new FluidStackHolder(this.output.copy()));
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
        defaultedList.add(new FluidStackHolder(this.output));
        return defaultedList;
    }

    public int getFermentTime() {
        return this.fermentTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.FERMENT_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.FERMENT_TYPE;
    }

    public static class Serializer implements RecipeSerializer<FermentRecipe> {
        private static final MapCodec<FermentRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        FluidStack.CODEC.fieldOf("input").forGetter(recipe -> recipe.input),
                        Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("catalyst").forGetter(recipe -> recipe.catalyst),
                        Codecs.NONNEGATIVE_INT.fieldOf("ferment_time").forGetter(recipe -> recipe.fermentTime),
                        FluidStack.CODEC.fieldOf("output").forGetter(recipe -> recipe.output)
                ).apply(instance, FermentRecipe::new)
        );
        public static final PacketCodec<RegistryByteBuf, FermentRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                FermentRecipe.Serializer::write, FermentRecipe.Serializer::read
        );

        @Override
        public MapCodec<FermentRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, FermentRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static FermentRecipe read(RegistryByteBuf buf) {
            FluidStack input = FluidStack.PACKET_CODEC.decode(buf);
            Ingredient catalyst = Ingredient.PACKET_CODEC.decode(buf);
            int fermentTime = PacketCodecs.INTEGER.decode(buf);
            FluidStack output = FluidStack.PACKET_CODEC.decode(buf);
            return new FermentRecipe(input, catalyst, fermentTime, output);
        }

        private static void write(RegistryByteBuf buf, FermentRecipe fermentRecipe) {
            FluidStack.PACKET_CODEC.encode(buf, fermentRecipe.input);
            Ingredient.PACKET_CODEC.encode(buf, fermentRecipe.catalyst);
            PacketCodecs.INTEGER.encode(buf, fermentRecipe.fermentTime);
            FluidStack.PACKET_CODEC.encode(buf, fermentRecipe.output);
        }
    }
}
