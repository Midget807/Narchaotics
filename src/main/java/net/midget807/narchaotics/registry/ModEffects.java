package net.midget807.narchaotics.registry;

import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.effect.PublicStatusEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> METH_HIGH = register("meth_high", new PublicStatusEffect(StatusEffectCategory.NEUTRAL, 0x03bde5)
            .addAttributeModifier(
                    EntityAttributes.GENERIC_FLYING_SPEED, NarchaoticsMain.id("effect.meth_high"), 0.5f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ).addAttributeModifier(
                    EntityAttributes.GENERIC_MOVEMENT_SPEED, NarchaoticsMain.id("effect.meth_high"), 0.5f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ).addAttributeModifier(
                    EntityAttributes.PLAYER_SNEAKING_SPEED, NarchaoticsMain.id("effect.meth_high"), 0.5f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ).addAttributeModifier(
                    EntityAttributes.PLAYER_MINING_EFFICIENCY, NarchaoticsMain.id("effect.meth_high"), 0.5f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ).addAttributeModifier(
                    EntityAttributes.PLAYER_SUBMERGED_MINING_SPEED, NarchaoticsMain.id("effect.meth_high"), 0.5f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ).addAttributeModifier(
                    EntityAttributes.PLAYER_BLOCK_BREAK_SPEED, NarchaoticsMain.id("effect.meth_high"), 0.5f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
            )
    );

    private static RegistryEntry<StatusEffect> register(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, NarchaoticsMain.id(name), statusEffect);
    }

    public static void registerModEffects() {
        NarchaoticsMain.LOGGER.info("Registering Mod Effects");
    }
}
