package net.midget807.narchaotics.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.midget807.narchaotics.registry.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

public class SeparateRecipe implements Recipe<SeparateRecipeInput>, MixedOutputRecipe<SeparateRecipeInput> {
    public final FluidStack input;
    public final int separateTime;
    public final FluidStack output;
    public final FluidStack remainder;

    public SeparateRecipe(FluidStack input, int filterTime, FluidStack output, FluidStack remainder) {
        this.input = input;
        this.separateTime = filterTime;
        this.output = output;
        this.remainder = remainder;
    }

    @Override
    public boolean matches(SeparateRecipeInput input, World world) {
        return this.input.variant().getFluid().matchesType(input.input().variant().getFluid());
    }

    @Override
    public ItemStack craft(SeparateRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return ItemStack.EMPTY;
    }

    @Override
    public DefaultedList<MixedStack> craftMultiple(SeparateRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        DefaultedList<MixedStack> defaultedList = DefaultedList.of();
        defaultedList.add(new FluidStackHolder(this.output.copy()));
        defaultedList.add(new FluidStackHolder(this.remainder.copy()));
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
        defaultedList.add(new FluidStackHolder(this.remainder));
        return defaultedList;
    }
    
    public int getSeparateTime() {
        return this.separateTime;
    }
    
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.SEPARATE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.SEPARATE_TYPE;
    }
    
    public static class Serializer implements RecipeSerializer<SeparateRecipe> {
        private static final MapCodec<SeparateRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        FluidStack.CODEC.optionalFieldOf("input", FluidStack.EMPTY).forGetter(recipe -> recipe.input),
                        Codecs.NONNEGATIVE_INT.fieldOf("filterTime").forGetter(recipe -> recipe.separateTime),
                        FluidStack.CODEC.optionalFieldOf("filtrate", FluidStack.EMPTY).forGetter(recipe -> recipe.output),
                        FluidStack.CODEC.optionalFieldOf("remainder", FluidStack.EMPTY).forGetter(recipe -> recipe.remainder)
                ).apply(instance, SeparateRecipe::new)
        );
        public static final PacketCodec<RegistryByteBuf, SeparateRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                SeparateRecipe::write, SeparateRecipe::read
        );
        
        @Override
        public MapCodec<SeparateRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, SeparateRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }

    private static SeparateRecipe read(RegistryByteBuf buf) {
        FluidStack input = FluidStack.EMPTY;
        if (buf.readBoolean()) input = FluidStack.PACKET_CODEC.decode(buf);
        
        int separateTime = PacketCodecs.INTEGER.decode(buf);
        
        FluidStack filtrate = FluidStack.EMPTY;
        if (buf.readBoolean()) filtrate = FluidStack.PACKET_CODEC.decode(buf);
        FluidStack remainder = FluidStack.EMPTY;
        if (buf.readBoolean()) remainder = FluidStack.PACKET_CODEC.decode(buf);
        return new SeparateRecipe(input, separateTime, filtrate, remainder);
    }

    private static void write(RegistryByteBuf buf, SeparateRecipe recipe) {
        buf.writeBoolean(!recipe.input.isEmpty());
        if (!recipe.input.isEmpty()) FluidStack.PACKET_CODEC.encode(buf, recipe.input);
        
        PacketCodecs.INTEGER.encode(buf, recipe.separateTime);
        
        buf.writeBoolean(!recipe.output.isEmpty());
        if (!recipe.output.isEmpty()) FluidStack.PACKET_CODEC.encode(buf, recipe.output);
        buf.writeBoolean(!recipe.remainder.isEmpty());
        if (!recipe.remainder.isEmpty()) FluidStack.PACKET_CODEC.encode(buf, recipe.remainder);
    }
}
