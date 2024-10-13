package net.intheminecraftgalaxy.itmg.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class SnowSwordItem extends SwordItem {

    public SnowSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Send a message to the attacker
        if (attacker instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) attacker;
            //player.sendMessage(Text.literal("You attacked " + target.getName().getString() + "!"), true);
        }

        // Apply a freeze effect for 5 seconds (100 ticks = 5 seconds)
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 2, false, true));

        // Return true to indicate that the hit was successful
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.tutorialmod.snow_sword.shift_down"));
        }else {
            tooltip.add(Text.translatable("tooltip.tutorialmod.snow_sword"));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
