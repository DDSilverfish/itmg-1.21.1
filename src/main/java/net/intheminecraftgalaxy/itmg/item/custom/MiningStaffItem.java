package net.intheminecraftgalaxy.itmg.item.custom;

import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MiningStaffItem extends Item {
    public MiningStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos clickedBlock = context.getBlockPos();


        if (!world.isClient() && Screen.hasShiftDown()) { // Only send the message on the server side
            PlayerEntity player = context.getPlayer();
            assert player != null;
            Vec3d lookVector = player.getRotationVec(1.0F); // Get player's look direction
            Vec3d snappedVector = snapToAxis(lookVector);

            // Loop to break the 5 blocks behind the clicked block
            for (int i = 0; i <= 4; i++) {
                BlockPos blockBehind = clickedBlock.add(
                        (int) (snappedVector.x * i), // Move in the opposite direction of the look vector
                        (int) (snappedVector.y * i),
                        (int) (snappedVector.z * i)
                );

                // Check if the block exists and break it
                if (world.getBlockState(blockBehind).isAir() || world.getBlockState(blockBehind).isOf(Blocks.BEDROCK) ) {
                    // Skip air blocks
                    continue;
                }

                world.breakBlock(blockBehind, true);
            }
        }


        return ActionResult.SUCCESS;
    }

    private Vec3d snapToAxis(Vec3d lookVector) {
        double absX = Math.abs(lookVector.x);
        double absY = Math.abs(lookVector.y);
        double absZ = Math.abs(lookVector.z);

        // Determine the dominant axis and snap the other components to 0
        if (absX > absY && absX > absZ) {
            // X-axis is dominant
            return new Vec3d(Math.signum(lookVector.x), 0, 0);
        } else if (absY > absX && absY > absZ) {
            // Y-axis is dominant
            return new Vec3d(0, Math.signum(lookVector.y), 0);
        } else {
            // Z-axis is dominant
            return new Vec3d(0, 0, Math.signum(lookVector.z));
        }
    }

}
