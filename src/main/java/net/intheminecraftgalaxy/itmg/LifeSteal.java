package net.intheminecraftgalaxy.itmg;


import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;


public class LifeSteal {
    public static void register() {
        AttackEntityCallback.EVENT.register((PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) -> {
            // Check if the attacked entity is a player
            //if (entity instanceof PlayerEntity) {
                // Send a message when a player attacks another player
                //if (!world.isClient) {  // Server-side check
                    //player.sendMessage(Text.literal("You attacked " + entity.getName().getString() + "!"), false);
                //}
            //}
            return ActionResult.PASS;
        });

        // Event for killing an entity
        ServerLivingEntityEvents.AFTER_DEATH.register((LivingEntity entity, DamageSource source) -> {
            // Check if the dead entity is a player and the killer is a player
            if (entity instanceof PlayerEntity killedPlayer && source.getAttacker() instanceof PlayerEntity killer) {

                // Server-side check to prevent client-side execution
                if (!entity.getWorld().isClient) {
                    // Send a message to the player when they kill another player
                    //killer.sendMessage(Text.literal("You killed " + killedPlayer.getName().getString() + "!"), false);

                    // Add a heart (increase max health) to the killer
                    EntityAttributeInstance killedHealthAttribute = killedPlayer.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
                    EntityAttributeInstance killerHealthAttribute = killer.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);


                    if (killerHealthAttribute != null && killedHealthAttribute != null && (killedHealthAttribute.getBaseValue() != InTheMinecraftGalaxyConfig.minHeart)) {
                        double currentMaxHealthKiller = killerHealthAttribute.getBaseValue();
                        killerHealthAttribute.setBaseValue(Math.min(currentMaxHealthKiller + InTheMinecraftGalaxyConfig.heartDamage, InTheMinecraftGalaxyConfig.maxHeart));  // Add one heart (2 health points) until max 30
                    }


                    // Remove a heart (decrease max health) from the killed player

                    if (killedHealthAttribute != null) {
                        double currentMaxHealthKilled = killedHealthAttribute.getBaseValue();
                        killedHealthAttribute.setBaseValue(Math.max(currentMaxHealthKilled - InTheMinecraftGalaxyConfig.heartDamage, InTheMinecraftGalaxyConfig.minHeart));  // Remove one heart, min is 2
                    }



                }
            }
        });
    }
}
