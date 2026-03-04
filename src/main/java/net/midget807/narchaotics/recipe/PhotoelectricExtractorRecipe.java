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

public class PhotoelectricExtractorRecipe implements Recipe<PhotoelectricExtractorRecipeInput>, MixedOutputRecipe<PhotoelectricExtractorRecipeInput> {
    public final FluidStack input;
    public final Ingredient catalyst;
    private final int extractTime;
    public final FluidStack output;
    public final ItemStack itemOutput;

    public PhotoelectricExtractorRecipe(FluidStack input, Ingredient catalyst, int extractTime, FluidStack output, ItemStack itemOutput) {
        this.input = input;
        this.catalyst = catalyst;
        this.extractTime = extractTime;
        this.output = output;
        this.itemOutput = itemOutput;
    }

    @Override
    public boolean matches(PhotoelectricExtractorRecipeInput input, World world) {
        return this.input.variant().getFluid().matchesType(input.input().variant().getFluid())
                && this.catalyst.test(input.catalyst());
    }

    @Override
    public ItemStack craft(PhotoelectricExtractorRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return ItemStack.EMPTY;
    }

    @Override
    public DefaultedList<MixedStack> craftMultiple(PhotoelectricExtractorRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        DefaultedList<MixedStack> defaultedList = DefaultedList.of();
        defaultedList.add(new FluidStackHolder(this.output.copy()));
        defaultedList.add(new ItemStackHolder(this.itemOutput.copy()));
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
        defaultedList.add(new ItemStackHolder(this.itemOutput));
        return defaultedList;
    }

    public int getExtractTime() {
        return this.extractTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.PHOTOELECTRIC_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.PHOTOELECTRIC_TYPE;
    }

    public static class Serializer implements RecipeSerializer<PhotoelectricExtractorRecipe> {
        private static final MapCodec<PhotoelectricExtractorRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        FluidStack.CODEC.fieldOf("input").forGetter(recipe -> recipe.input),
                        Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("catalyst").forGetter(recipe -> recipe.catalyst),
                        Codecs.NONNEGATIVE_INT.fieldOf("extractTime").forGetter(recipe -> recipe.extractTime),
                        FluidStack.CODEC.optionalFieldOf("output", FluidStack.EMPTY).forGetter(recipe -> recipe.output),
                        ItemStack.CODEC.optionalFieldOf("itemOutput", ItemStack.EMPTY).forGetter(recipe -> recipe.itemOutput)
                ).apply(instance, PhotoelectricExtractorRecipe::new)
        );
        public static final PacketCodec<RegistryByteBuf, PhotoelectricExtractorRecipe> PACKET_CODEC =PacketCodec.ofStatic(
                PhotoelectricExtractorRecipe.Serializer::write, PhotoelectricExtractorRecipe.Serializer::read
        );

        @Override
        public MapCodec<PhotoelectricExtractorRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, PhotoelectricExtractorRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static PhotoelectricExtractorRecipe read(RegistryByteBuf buf) {
            FluidStack input = FluidStack.PACKET_CODEC.decode(buf);
            Ingredient catalyst = Ingredient.PACKET_CODEC.decode(buf);
            int extractTime = PacketCodecs.INTEGER.decode(buf);

            FluidStack output = FluidStack.EMPTY;
            if (buf.readBoolean()) output = FluidStack.PACKET_CODEC.decode(buf);
            ItemStack itemOutput = ItemStack.EMPTY;
            if (buf.readBoolean()) itemOutput = ItemStack.PACKET_CODEC.decode(buf);
            return new PhotoelectricExtractorRecipe(input, catalyst, extractTime, output, itemOutput);
        }

        private static void write(RegistryByteBuf buf, PhotoelectricExtractorRecipe recipe) {
            FluidStack.PACKET_CODEC.encode(buf, recipe.input);
            Ingredient.PACKET_CODEC.encode(buf, recipe.catalyst);
            PacketCodecs.INTEGER.encode(buf, recipe.extractTime);

            buf.writeBoolean(!recipe.output.isEmpty());
            if (!recipe.output.isEmpty()) FluidStack.PACKET_CODEC.encode(buf, recipe.output);

            buf.writeBoolean(!recipe.itemOutput.isEmpty());
            if (!recipe.itemOutput.isEmpty()) ItemStack.PACKET_CODEC.encode(buf, recipe.itemOutput);
        }

    }
}
