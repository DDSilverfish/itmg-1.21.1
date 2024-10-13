package net.intheminecraftgalaxy.itmg.item.custom;

import com.llamalad7.mixinextras.lib.apache.commons.ObjectUtils;
import com.mojang.datafixers.util.Either;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.intheminecraftgalaxy.itmg.component.ModDataComponentTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;

public class TimberAxeItem extends AxeItem {
    public TimberAxeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);

        PlayerBlockBreakEvents.BEFORE.register(this::onBlockBreak);
    }

    // Event handler for block breaking
    private boolean onBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity entity) {
        // Check if the item in the player's main hand is this TimberAxeItem
        ItemStack mainHandStack = player.getMainHandStack();
        if (mainHandStack.getItem() instanceof TimberAxeItem) {
            // Check if the block being broken is a log
            if (isLog(state)) {
                // Send a message to the player
                player.sendMessage(Text.literal("You mined a log!"), true);

                // Start breaking neighboring logs
                breakNeighboringLogs(world, player, pos);
            }
        }
        return true; // Continue with the default behavior
    }

    // Method to break neighboring logs
    private void breakNeighboringLogs(World world, PlayerEntity player, BlockPos pos) {
        Set<BlockPos> logsToBreak = new HashSet<>();
        breakLogRecursively(world, player, pos, logsToBreak);

        // Now break all the found logs
        for (BlockPos logPos : logsToBreak) {
            BlockState logState = world.getBlockState(logPos);
            if (isLog(logState)) {
                world.breakBlock(logPos, true, player); // Break the log

                // Damage the axe after breaking each log
                if (!world.isClient) { // Ensure this only happens on the server side
                    ItemStack mainHandStack = player.getMainHandStack();
                    mainHandStack.damage(1, player, EquipmentSlot.MAINHAND); // Damage the axe by 1 durability
                    // Check if the axe is broken and stop further processing
                    if (mainHandStack.isEmpty()) {
                        break; // Exit the loop if the axe is broken
                    }
                }
            }
        }
    }

    // Recursive method to find neighboring logs
    private void breakLogRecursively(World world, PlayerEntity player, BlockPos pos, Set<BlockPos> logsToBreak) {
        if (!isLog(world.getBlockState(pos)) || logsToBreak.contains(pos)) {
            return; // Base case: Not a log or already processed
        }

        logsToBreak.add(pos); // Add the current log to the set

        // Check all 6 neighboring positions (up, down, left, right, front, back)
        for (BlockPos neighbor : getNeighboringPositions(pos)) {
            breakLogRecursively(world, player, neighbor, logsToBreak); // Recursive call
        }
    }

    // Method to get neighboring positions
    private BlockPos[] getNeighboringPositions(BlockPos pos) {
        return new BlockPos[]{
                pos.up(), pos.down(), pos.north(), pos.south(), pos.east(), pos.west()
        };
    }



    private boolean isLog(BlockState blockState) {
        return blockState.isOf(Blocks.OAK_LOG) ||
                blockState.isOf(Blocks.BIRCH_LOG) ||
                blockState.isOf(Blocks.SPRUCE_LOG) ||
                blockState.isOf(Blocks.JUNGLE_LOG) ||
                blockState.isOf(Blocks.ACACIA_LOG) ||
                blockState.isOf(Blocks.STRIPPED_OAK_LOG) ||
                blockState.isOf(Blocks.STRIPPED_BIRCH_LOG) ||
                blockState.isOf(Blocks.STRIPPED_SPRUCE_LOG) ||
                blockState.isOf(Blocks.STRIPPED_JUNGLE_LOG) ||
                blockState.isOf(Blocks.STRIPPED_ACACIA_LOG) ||
                blockState.isOf(Blocks.STRIPPED_DARK_OAK_LOG) ||
                blockState.isOf(Blocks.DARK_OAK_LOG);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.tutorialmod.timber_axe.shift_down"));
        }else {
            tooltip.add(Text.translatable("tooltip.tutorialmod.timber_axe"));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
