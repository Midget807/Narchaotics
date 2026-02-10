package net.midget807.narchaotics.mixin;

import net.midget807.narchaotics.util.inject.FlaskStorable;
import net.minecraft.fluid.Fluid;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Fluid.class)
public abstract class FluidMixin implements FlaskStorable {

}
