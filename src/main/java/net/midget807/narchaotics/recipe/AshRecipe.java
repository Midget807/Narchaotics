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
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

public class AshRecipe implements Recipe<AshRecipeInput> {
    public final Ingredient input;
    private final int ashTime;
    public final ItemStack output;

    public AshRecipe(Ingredient input, int ashTime, ItemStack output) {
        this.input = input;
        this.ashTime = ashTime;
        this.output = output;
    }

    @Override
    public boolean matches(AshRecipeInput input, World world) {
        return this.input.test(input.input());
    }

    @Override
    public ItemStack craft(AshRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return this.output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.output;
    }

    public int getAshTime() {
        return this.ashTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ASH_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.ASH_TYPE;
    }

    public static class Serializer implements RecipeSerializer<AshRecipe> {
        private static final MapCodec<AshRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("input").forGetter(recipe -> recipe.input),
                        Codecs.NONNEGATIVE_INT.fieldOf("ash_time").forGetter(recipe -> recipe.ashTime),
                        ItemStack.CODEC.fieldOf("output").forGetter(recipe -> recipe.output)
                ).apply(instance, AshRecipe::new)
        );
        public static final PacketCodec<RegistryByteBuf, AshRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                AshRecipe.Serializer::write, AshRecipe.Serializer::read
        );

        @Override
        public MapCodec<AshRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, AshRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static AshRecipe read(RegistryByteBuf buf) {
            Ingredient input = Ingredient.PACKET_CODEC.decode(buf);
            int ashTime = PacketCodecs.INTEGER.decode(buf);
            ItemStack output = ItemStack.PACKET_CODEC.decode(buf);
            return new AshRecipe(input, ashTime, output);
        }

        private static void write(RegistryByteBuf buf, AshRecipe recipe) {
            Ingredient.PACKET_CODEC.encode(buf, recipe.input);
            PacketCodecs.INTEGER.encode(buf, recipe.ashTime);
            ItemStack.PACKET_CODEC.encode(buf, recipe.output);
        }
    }
}
