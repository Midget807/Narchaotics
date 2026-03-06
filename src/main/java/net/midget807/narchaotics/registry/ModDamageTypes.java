package net.midget807.narchaotics.registry;

import net.midget807.narchaotics.NarchaoticsMain;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> OVERDOSE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, NarchaoticsMain.id("overdose"));

    public static DamageSource overdose(World world) {
        return new DamageSource(
                world.getRegistryManager()
                        .get(RegistryKeys.DAMAGE_TYPE)
                        .entryOf(OVERDOSE)
        );
    }
    public static DamageSource overdose(LivingEntity user) {
        return new DamageSource(
                user.getWorld().getRegistryManager()
                        .get(RegistryKeys.DAMAGE_TYPE)
                        .entryOf(OVERDOSE)
        );
    }

    public static void registerModDamageTypes() {
        NarchaoticsMain.LOGGER.warn("Registering Mod Damage Types");
    }
}
