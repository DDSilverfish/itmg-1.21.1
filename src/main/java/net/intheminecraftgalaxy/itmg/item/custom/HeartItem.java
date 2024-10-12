package net.intheminecraftgalaxy.itmg.item.custom;

import net.intheminecraftgalaxy.itmg.InTheMinecraftGalaxyConfig;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HeartItem extends Item {
    public HeartItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        // Only increase health on the server side to avoid desync issues
        EntityAttributeInstance playerHealthAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (!world.isClient && playerHealthAttribute != null && playerHealthAttribute.getBaseValue() < 20) {

            // Get the player's current maximum health
            double currentMaxHealthPlayer = playerHealthAttribute.getBaseValue();

            // Set the new max health
            playerHealthAttribute.setBaseValue(Math.min(currentMaxHealthPlayer + InTheMinecraftGalaxyConfig.heartDamage, InTheMinecraftGalaxyConfig.maxHeart));

            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0F, 1.0F);


            // Consume the heart item after use
            if (!player.getAbilities().creativeMode) {
                player.getStackInHand(hand).decrement(1);
            }
        }

        // Return success and update the item stack
        return TypedActionResult.success(player.getStackInHand(hand), world.isClient());
    }
}
