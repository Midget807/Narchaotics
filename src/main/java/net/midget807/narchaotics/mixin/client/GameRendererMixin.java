package net.midget807.narchaotics.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.midget807.narchaotics.registry.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    private float fovMultiplier;

    @ModifyExpressionValue(method = "getFov", at = @At(value = "INVOKE", target = "Ljava/lang/Integer;intValue()I"))
    private int narchaotics$lockFovOption(int original, @Local double d) {
        ClientPlayerEntity player = this.client.player;
        if (player != null && player.hasStatusEffect(ModEffects.METH_HIGH)) {
            return 90;
        }
        return original;
    }

    @Inject(method = "updateFovMultiplier", at = @At("TAIL"))
    private void narchaotics$crankDatFov(CallbackInfo ci) {
        ClientPlayerEntity player = this.client.player;
        if (player != null && player.hasStatusEffect(ModEffects.METH_HIGH) && player.getStatusEffect(ModEffects.METH_HIGH) != null) {
            int amplifier = player.getStatusEffect(ModEffects.METH_HIGH).getAmplifier();
            this.fovMultiplier += (float) (amplifier * 0.055);
        }
    }
}
