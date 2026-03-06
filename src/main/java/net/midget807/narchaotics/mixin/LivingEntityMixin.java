package net.midget807.narchaotics.mixin;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyReceiver(method = "spawnConsumptionEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V", ordinal = 0))
    private LivingEntity narchaotics$playMethEatSound(LivingEntity instance, SoundEvent soundEvent, float volume, float pitch) {
        instance.playSound(soundEvent, 0.5f, pitch * 20.0f);
        return instance;
    }
}
