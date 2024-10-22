package net.intheminecraftgalaxy.itmg.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ThunderBladeItem extends Item {
    public ThunderBladeItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        if (!world.isClient && player != null) {

            if (!player.getItemCooldownManager().isCoolingDown(this.getDefaultStack())) {
                // Summon lightning on right-click
                BlockPos pos = context.getBlockPos().up();
                LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                lightning.refreshPositionAfterTeleport(pos.getX(), pos.getY(), pos.getZ());
                world.spawnEntity(lightning);

                //set cooldown 100 ticks
                player.getItemCooldownManager().set(this.getDefaultStack(), 100);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        World world = entity.getWorld();
        if (!world.isClient && user != null) {

            if (!user.getItemCooldownManager().isCoolingDown(this.getDefaultStack())) {
                // Summon lightning on right-click
                BlockPos pos = entity.getBlockPos();
                LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                lightning.refreshPositionAfterTeleport(pos.getX(), pos.getY(), pos.getZ());
                world.spawnEntity(lightning);

                //set cooldown 100 ticks
                user.getItemCooldownManager().set(this.getDefaultStack(), 100);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.tutorialmod.thunderblade.shift_down"));
        }else {
            tooltip.add(Text.translatable("tooltip.tutorialmod.thunderblade"));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
