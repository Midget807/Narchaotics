package net.midget807.narchaotics.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

public record FluidStack(FluidVariant variant, long amount) {
    public static final FluidStack EMPTY = new FluidStack(FluidVariant.blank(), 0);

    public static final Codec<FluidStack> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    FluidVariant.CODEC.fieldOf("fluid_id").forGetter(FluidStack::variant),
                    Codec.LONG.fieldOf("amount").forGetter(FluidStack::amount)
            ).apply(instance, FluidStack::new)
    );
    public static final PacketCodec<RegistryByteBuf, FluidStack> PACKET_CODEC = PacketCodec.tuple(
            FluidVariant.PACKET_CODEC, FluidStack::variant,
            PacketCodecs.VAR_LONG, FluidStack::amount,
            FluidStack::new
    );

    public FluidStack copy() {
        if (this.variant.isBlank() || this.amount <= 0) {
            return EMPTY;
        } else {
            FluidStack fluidStack = new FluidStack(this.variant(), this.amount());
            return fluidStack;
        }
    }

    public boolean isEmpty() {
        return variant.isBlank() || amount <= 0;
    }
}
