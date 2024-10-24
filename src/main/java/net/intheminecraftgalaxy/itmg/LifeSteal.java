package net.intheminecraftgalaxy.itmg;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

import java.util.Collection;


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
                    EntityAttributeInstance killedHealthAttribute = killedPlayer.getAttributeInstance(EntityAttributes.MAX_HEALTH);
                    EntityAttributeInstance killerHealthAttribute = killer.getAttributeInstance(EntityAttributes.MAX_HEALTH);


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

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, test) -> {
            registerGiveHeartCommand(dispatcher);
        });
    }
    private static void registerGiveHeartCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("giveHeart")
                .then(CommandManager.argument("playerName", StringArgumentType.string())
                        .suggests(SUGGEST_PLAYERS)
                        .executes(context -> {
                            String playerName = StringArgumentType.getString(context, "playerName");
                            ServerCommandSource source = context.getSource();
                            ServerPlayerEntity giver = source.getPlayer();
                            ServerPlayerEntity receiver = source.getServer().getPlayerManager().getPlayer(playerName);

                            if (giver == null || receiver == null) {
                                source.sendFeedback(() -> Text.literal("Player not found!"), false);
                                return 1;
                            }

                            EntityAttributeInstance giverHealthAttribute = giver.getAttributeInstance(EntityAttributes.MAX_HEALTH);
                            EntityAttributeInstance receiverHealthAttribute= receiver.getAttributeInstance(EntityAttributes.MAX_HEALTH);

                            assert giverHealthAttribute != null;
                            assert receiverHealthAttribute != null;
                            double giverCurrentMaxHealth = giverHealthAttribute.getBaseValue();
                            double receiverCurrentMaxHealth = receiverHealthAttribute.getBaseValue();

                            if(giverCurrentMaxHealth > InTheMinecraftGalaxyConfig.maxHeartItemGen && receiverCurrentMaxHealth < InTheMinecraftGalaxyConfig.maxHeart && receiver.getName() != giver.getName()) {
                                giverHealthAttribute.setBaseValue(receiverCurrentMaxHealth - 2);
                                receiverHealthAttribute.setBaseValue(receiverCurrentMaxHealth + 2);

                                giver.sendMessage(Text.literal("You gave 1 heart to " + receiver.getName().getString() + "!"), true);
                                receiver.sendMessage(Text.literal("You received 1 heart from " + giver.getName().getString() + "!"), true);

                            } else if (receiver.getName() != giver.getName()) {
                                giver.sendMessage(Text.literal("You can only send a heart to another player!"), false);
                            } else if (giverCurrentMaxHealth <= InTheMinecraftGalaxyConfig.maxHeartItemGen) {
                                giver.sendMessage(Text.literal("You don't have enough health to give a heart!"), false);
                            }
                            else
                            {
                                giver.sendMessage(Text.literal(receiver.getName().getString() + " already has the maximum amount of hearts!!"), false);
                            }

                            return 1;
                        })));
    }

    // Suggestion provider for players
    private static final SuggestionProvider<ServerCommandSource> SUGGEST_PLAYERS = (context, builder) -> {
        Collection<ServerPlayerEntity> players = context.getSource().getServer().getPlayerManager().getPlayerList();
        return CommandSource.suggestMatching(players.stream().map(player -> player.getGameProfile().getName()), builder);
    };

}
