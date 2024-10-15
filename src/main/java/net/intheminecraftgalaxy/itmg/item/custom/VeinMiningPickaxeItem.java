package net.intheminecraftgalaxy.itmg.item.custom;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VeinMiningPickaxeItem extends PickaxeItem {
    public VeinMiningPickaxeItem(ToolMaterial material, Settings settings) {
        super(material, settings);

        PlayerBlockBreakEvents.BEFORE.register(this::onBlockBreak);
    }

    private boolean onBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity entity) {
        // Check if the item in the player's main hand is this TimberAxeItem
        ItemStack mainHandStack = player.getMainHandStack();
        if (mainHandStack.getItem() instanceof VeinMiningPickaxeItem) {
            // Check if the block being broken is a log
            if (isOre(state)) {
                // Send a message to the player
                //player.sendMessage(Text.literal("You mined a log!"), true);

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
            if (isOre(logState)) {
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
        if (!isOre(world.getBlockState(pos)) || logsToBreak.contains(pos)) {
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

    private boolean isOre(BlockState blockState) {
        return blockState.isOf(Blocks.COAL_ORE) ||
                blockState.isOf(Blocks.DEEPSLATE_COAL_ORE) ||
                blockState.isOf(Blocks.IRON_ORE) ||
                blockState.isOf(Blocks.DEEPSLATE_IRON_ORE) ||
                blockState.isOf(Blocks.COPPER_ORE) ||
                blockState.isOf(Blocks.DEEPSLATE_COPPER_ORE) ||
                blockState.isOf(Blocks.GOLD_ORE) ||
                blockState.isOf(Blocks.DEEPSLATE_GOLD_ORE) ||
                blockState.isOf(Blocks.REDSTONE_ORE) ||
                blockState.isOf(Blocks.DEEPSLATE_REDSTONE_ORE) ||
                blockState.isOf(Blocks.EMERALD_ORE) ||
                blockState.isOf(Blocks.DEEPSLATE_EMERALD_ORE) ||
                blockState.isOf(Blocks.LAPIS_ORE) ||
                blockState.isOf(Blocks.DEEPSLATE_LAPIS_ORE) ||
                blockState.isOf(Blocks.DIAMOND_ORE) ||
                blockState.isOf(Blocks.DEEPSLATE_DIAMOND_ORE) ||
                blockState.isOf(Blocks.NETHER_QUARTZ_ORE) ||
                blockState.isOf(Blocks.NETHER_GOLD_ORE) ||
                blockState.isOf(Blocks.ANCIENT_DEBRIS);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canBeEnchantedWith(ItemStack stack, RegistryEntry<Enchantment> enchantment, EnchantingContext context) {
        return enchantment.getKey()
                .map(resourceKey -> {
                    //System.out.println("Attempting to enchant: " + resourceKey);
                    //System.out.println("Attempting to enchant against: " + Enchantments.UNBREAKING);
                    return resourceKey == Enchantments.UNBREAKING || resourceKey == Enchantments.EFFICIENCY || resourceKey == Enchantments.FORTUNE;
                })
                .orElse(false);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.tutorialmod.vein_mining_pickaxe.shift_down"));
        }else {
            tooltip.add(Text.translatable("tooltip.tutorialmod.vein_mining_pickaxe"));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
