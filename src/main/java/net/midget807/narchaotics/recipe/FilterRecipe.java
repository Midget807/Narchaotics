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

public class FilterRecipe implements Recipe<FilterRecipeInput>, MixedOutputRecipe<FilterRecipeInput> {
    public final FluidStack input;
    public final int filterTime;
    public final FluidStack filtrate;
    public final ItemStack residue;

    public FilterRecipe(FluidStack input, int filterTime, FluidStack filtrate, ItemStack residue) {
        this.input = input;
        this.filterTime = filterTime;
        this.filtrate = filtrate;
        this.residue = residue;
    }

    @Override
    public boolean matches(FilterRecipeInput input, World world) {
        return this.input.variant().getFluid().matchesType(input.input().variant().getFluid());
    }

    @Override
    public ItemStack craft(FilterRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return ItemStack.EMPTY;
    }

    @Override
    public DefaultedList<MixedStack> craftMultiple(FilterRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        DefaultedList<MixedStack> defaultedList = DefaultedList.of();
        defaultedList.add(new FluidStackHolder(this.filtrate.copy()));
        defaultedList.add(new ItemStackHolder(this.residue.copy()));
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
        defaultedList.add(new FluidStackHolder(this.filtrate));
        defaultedList.add(new ItemStackHolder(this.residue));
        return defaultedList;
    }

    public int getFilterTime() {
        return this.filterTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.FILTER_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.FILTER_TYPE;
    }

    public static class Serializer implements RecipeSerializer<FilterRecipe> {
        private static final MapCodec<FilterRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        FluidStack.CODEC.optionalFieldOf("input", FluidStack.EMPTY).forGetter(recipe -> recipe.input),
                        Codecs.NONNEGATIVE_INT.fieldOf("filterTime").forGetter(recipe -> recipe.filterTime),
                        FluidStack.CODEC.optionalFieldOf("filtrate", FluidStack.EMPTY).forGetter(recipe -> recipe.filtrate),
                        ItemStack.CODEC.optionalFieldOf("remainder", ItemStack.EMPTY).forGetter(recipe -> recipe.residue)
                ).apply(instance, FilterRecipe::new)
        );
        public static final PacketCodec<RegistryByteBuf, FilterRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                FilterRecipe::write, FilterRecipe::read
        );

        @Override
        public MapCodec<FilterRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, FilterRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }

    private static FilterRecipe read(RegistryByteBuf buf) {
        FluidStack input = FluidStack.EMPTY;
        if (buf.readBoolean()) input = FluidStack.PACKET_CODEC.decode(buf);

        int filterTime = PacketCodecs.INTEGER.decode(buf);

        FluidStack filtrate = FluidStack.EMPTY;
        if (buf.readBoolean()) filtrate = FluidStack.PACKET_CODEC.decode(buf);
        ItemStack residue = ItemStack.EMPTY;
        if (buf.readBoolean()) residue = ItemStack.PACKET_CODEC.decode(buf);
        return new FilterRecipe(input, filterTime, filtrate, residue);
    }

    private static void write(RegistryByteBuf buf, FilterRecipe recipe) {
        buf.writeBoolean(!recipe.input.isEmpty());
        if (!recipe.input.isEmpty()) FluidStack.PACKET_CODEC.encode(buf, recipe.input);

        PacketCodecs.INTEGER.encode(buf, recipe.filterTime);

        buf.writeBoolean(!recipe.filtrate.isEmpty());
        if (!recipe.filtrate.isEmpty()) FluidStack.PACKET_CODEC.encode(buf, recipe.filtrate);
        buf.writeBoolean(!recipe.residue.isEmpty());
        if (!recipe.residue.isEmpty()) ItemStack.PACKET_CODEC.encode(buf, recipe.residue);
    }
}
