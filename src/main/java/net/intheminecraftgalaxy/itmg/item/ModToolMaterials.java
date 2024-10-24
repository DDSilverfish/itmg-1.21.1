package net.intheminecraftgalaxy.itmg.item;

import net.intheminecraftgalaxy.itmg.InTheMinecraftGalaxyConfig;
import net.minecraft.item.ToolMaterial;

import static net.intheminecraftgalaxy.itmg.util.ModTags.Blocks.*;
import static net.intheminecraftgalaxy.itmg.util.ModTags.Items.*;

public enum ModToolMaterials {

    //Durability, Mining Speed Multiplier, Attack Damage Multiplier, Enchantebility
    SNOW_TOOL(
            new ToolMaterial(
                    INCORRECT_FOR_SNOW_TOOL, InTheMinecraftGalaxyConfig.snowSwordDurability, 1.0f, 0.0f, 22,
                    SNOWBALL_REPAIR_ITEM
            )
    ),
    TIMBER_TOOL(
            new ToolMaterial(
            INCORRECT_FOR_TIMBER_TOOL, InTheMinecraftGalaxyConfig.timberAxeDurability, 6.0f, 2.0f, 14,
            TIMBER_AXE_HEAD_REPAIR_ITEM
            )
    ),
    VEINMININGPICKAXETOOL(
            new ToolMaterial(
            INCORRECT_FOR_VEINMININGPICKAXE_TOOL, InTheMinecraftGalaxyConfig.veinMiningPickaxeDurability, 6.0f, 2.0f, 14,
            NETHERITE_INGOT_REPAIR_ITEM
            )
    );

    private final ToolMaterial material;

    ModToolMaterials(ToolMaterial material) {
        this.material = material;
    }

    public ToolMaterial getMaterial() {
        return this.material;
    }
}
