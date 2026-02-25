package net.midget807.narchaotics.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleEffect;

public class PublicStatusEffect extends StatusEffect {
    public PublicStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public PublicStatusEffect(StatusEffectCategory category, int color, ParticleEffect particleEffect) {
        super(category, color, particleEffect);
    }
}
