package net.midget807.narchaotics.mixin;

import net.midget807.narchaotics.datagen.ModItemTagProvider;
import net.midget807.narchaotics.registry.ModDamageTypes;
import net.midget807.narchaotics.registry.ModEffects;
import net.midget807.narchaotics.registry.ModItems;
import net.midget807.narchaotics.util.inject.MethHigh;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements MethHigh {
    @Shadow
    public abstract boolean isSpectator();

    @Shadow
    public abstract boolean damage(DamageSource source, float amount);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void narchaotics$speedHappyGhast(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!this.isSpectator()) {
            ItemStack itemStack = this.getStackInHand(hand);
            if (Registries.ENTITY_TYPE.getId(entity.getType()).equals(Identifier.ofVanilla("happy_ghast"))) {
                if (itemStack.isOf(ModItems.METHAMPHETAMINE)) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    if (livingEntity.hasStatusEffect(ModEffects.METH_HIGH) && livingEntity.getStatusEffect(ModEffects.METH_HIGH) != null) {
                        if (livingEntity.getStatusEffect(ModEffects.METH_HIGH).getAmplifier() < 7) {
                            livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.METH_HIGH, 12000, livingEntity.getStatusEffect(ModEffects.METH_HIGH).getAmplifier() + 1));
                            if (!this.getWorld().isClient) {
                                int level = livingEntity.hasStatusEffect(ModEffects.METH_HIGH) ? livingEntity.getStatusEffect(ModEffects.METH_HIGH).getAmplifier() + 1 : 0;
                                Text text = Text.literal("Effect Level: ").formatted(Formatting.GRAY).append(Text.literal("" + level).formatted(Formatting.WHITE)).append(Text.literal("   [Base = 0.05] Fly Speed: ").formatted(Formatting.GRAY)).append(Text.literal(String.format("%.3f", livingEntity.getAttributeValue(EntityAttributes.GENERIC_FLYING_SPEED))).formatted(Formatting.WHITE));
                                ((PlayerEntity) ((Object) this)).sendMessage(text, true);
                            }
                            cir.setReturnValue(ActionResult.SUCCESS);
                        }
                    } else {
                        livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.METH_HIGH, 12000, 0));
                        if (!this.getWorld().isClient) {
                            int level = livingEntity.hasStatusEffect(ModEffects.METH_HIGH) ? livingEntity.getStatusEffect(ModEffects.METH_HIGH).getAmplifier() + 1 : 0;
                            Text text = Text.literal("Effect Level: ").formatted(Formatting.GRAY).append(Text.literal("" + level).formatted(Formatting.WHITE)).append(Text.literal("   [Base = 0.05] Fly Speed: ").formatted(Formatting.GRAY)).append(Text.literal(String.format("%.3f", livingEntity.getAttributeValue(EntityAttributes.GENERIC_FLYING_SPEED))).formatted(Formatting.WHITE));
                            ((PlayerEntity) ((Object) this)).sendMessage(text, true);
                        }
                        cir.setReturnValue(ActionResult.SUCCESS);
                    }
                } else if (itemStack.isIn(ModItemTagProvider.METH_REDUCER)) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    if (livingEntity.hasStatusEffect(ModEffects.METH_HIGH) && livingEntity.getStatusEffect(ModEffects.METH_HIGH) != null) {
                        if (itemStack.isOf(Items.SNOW_BLOCK)) {
                            if (itemStack.getCount() >= 8) {
                                if (livingEntity.getStatusEffect(ModEffects.METH_HIGH).getAmplifier() > 0) {
                                    int amp = livingEntity.getStatusEffect(ModEffects.METH_HIGH).getAmplifier();
                                    livingEntity.removeStatusEffect(ModEffects.METH_HIGH);
                                    livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.METH_HIGH, 12000, amp - 1));
                                } else {
                                    livingEntity.removeStatusEffect(ModEffects.METH_HIGH);
                                }
                                int level = livingEntity.hasStatusEffect(ModEffects.METH_HIGH) ? livingEntity.getStatusEffect(ModEffects.METH_HIGH).getAmplifier() + 1 : 0;
                                Text text = Text.literal("Effect Level: ").formatted(Formatting.GRAY).append(Text.literal("" + level).formatted(Formatting.WHITE)).append(Text.literal("   [Base = 0.05] Fly Speed: ").formatted(Formatting.GRAY)).append(Text.literal(String.format("%.3f", livingEntity.getAttributeValue(EntityAttributes.GENERIC_FLYING_SPEED))).formatted(Formatting.WHITE));
                                ((PlayerEntity) ((Object) this)).sendMessage(text, true);
                                itemStack.decrement(8);
                                cir.setReturnValue(ActionResult.SUCCESS);
                            }
                        } else if (itemStack.isOf(Items.ICE)) {
                            if (itemStack.getCount() >= 1) {
                                if (livingEntity.getStatusEffect(ModEffects.METH_HIGH).getAmplifier() > 0) {
                                    int amp = livingEntity.getStatusEffect(ModEffects.METH_HIGH).getAmplifier();
                                    livingEntity.removeStatusEffect(ModEffects.METH_HIGH);
                                    livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.METH_HIGH, 12000, amp - 1));
                                } else {
                                    livingEntity.removeStatusEffect(ModEffects.METH_HIGH);
                                }
                                int level = livingEntity.hasStatusEffect(ModEffects.METH_HIGH) ? livingEntity.getStatusEffect(ModEffects.METH_HIGH).getAmplifier() + 1 : 0;
                                Text text = Text.literal("Effect Level: ").formatted(Formatting.GRAY).append(Text.literal("" + level).formatted(Formatting.WHITE)).append(Text.literal("   [Base = 0.05] Fly Speed: ").formatted(Formatting.GRAY)).append(Text.literal(String.format("%.3f", livingEntity.getAttributeValue(EntityAttributes.GENERIC_FLYING_SPEED))).formatted(Formatting.WHITE));
                                ((PlayerEntity) ((Object) this)).sendMessage(text, true);
                                itemStack.decrement(1);
                                cir.setReturnValue(ActionResult.SUCCESS);
                            }
                        } else if (itemStack.isOf(Items.PACKED_ICE)) {
                            if (itemStack.getCount() >= 1) {
                                if (livingEntity.getStatusEffect(ModEffects.METH_HIGH).getAmplifier() > 3) {
                                    int amp = livingEntity.getStatusEffect(ModEffects.METH_HIGH).getAmplifier();
                                    livingEntity.removeStatusEffect(ModEffects.METH_HIGH);
                                    livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.METH_HIGH, 12000, amp - 4));
                                } else {
                                    livingEntity.removeStatusEffect(ModEffects.METH_HIGH);
                                }
                                int level = livingEntity.hasStatusEffect(ModEffects.METH_HIGH) ? livingEntity.getStatusEffect(ModEffects.METH_HIGH).getAmplifier() + 1 : 0;
                                Text text = Text.literal("Effect Level: ").formatted(Formatting.GRAY).append(Text.literal("" + level).formatted(Formatting.WHITE)).append(Text.literal("   [Base = 0.05] Fly Speed: ").formatted(Formatting.GRAY)).append(Text.literal(String.format("%.3f", livingEntity.getAttributeValue(EntityAttributes.GENERIC_FLYING_SPEED))).formatted(Formatting.WHITE));
                                ((PlayerEntity) ((Object) this)).sendMessage(text, true);
                                itemStack.decrement(1);
                                cir.setReturnValue(ActionResult.SUCCESS);
                            }
                        } else if (itemStack.isOf(Items.BLUE_ICE)) {
                            if (itemStack.getCount() >= 1) {
                                livingEntity.removeStatusEffect(ModEffects.METH_HIGH);
                                int level = livingEntity.hasStatusEffect(ModEffects.METH_HIGH) ? livingEntity.getStatusEffect(ModEffects.METH_HIGH).getAmplifier() + 1 : 0;
                                Text text = Text.literal("Effect Level: ").formatted(Formatting.GRAY).append(Text.literal("" + level).formatted(Formatting.WHITE)).append(Text.literal("   [Base = 0.05] Fly Speed: ").formatted(Formatting.GRAY)).append(Text.literal(String.format("%.3f", livingEntity.getAttributeValue(EntityAttributes.GENERIC_FLYING_SPEED))).formatted(Formatting.WHITE));
                                ((PlayerEntity) ((Object) this)).sendMessage(text, true);
                                itemStack.decrement(1);
                                cir.setReturnValue(ActionResult.SUCCESS);
                            }
                        }
                    }
                }
            }

        }
    }

    @Override
    public ItemStack narchaotics$eatMeth(World world, ItemStack stack, int currentAmplifier) {
        world.playSound(
                null,
                this.getX(),
                this.getY(),
                this.getZ(),
                this.getDrinkSound(stack),
                SoundCategory.NEUTRAL,
                0.6F,
                6.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F
        );
        if (currentAmplifier < -1) currentAmplifier = -1;
        currentAmplifier++;
        if (currentAmplifier >= 10) {
            this.damage(ModDamageTypes.overdose(this.getWorld()), Integer.MAX_VALUE);
        }
        currentAmplifier = Math.min(currentAmplifier, 9);
        this.addStatusEffect(new StatusEffectInstance(ModEffects.METH_HIGH, 12000, currentAmplifier));
        stack.decrementUnlessCreative(1, ((PlayerEntity)((Object)this)));
        return stack;
    }
}
