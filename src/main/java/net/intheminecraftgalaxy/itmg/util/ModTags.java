package net.intheminecraftgalaxy.itmg.util;

import net.intheminecraftgalaxy.itmg.ITMG;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> INCORRECT_FOR_SNOW_TOOL = createTag("incorrect_for_snow_tool");
        public static final TagKey<Block> INCORRECT_FOR_TIMBER_TOOL = createTag("incorrect_for_snow_tool");
        public static final TagKey<Block> INCORRECT_FOR_VEINMININGPICKAXE_TOOL = createTag("incorrect_for_snow_tool");
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(ITMG.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> SNOWBALL_REPAIR_ITEM = createTag("snowball_repair_item");
        public static final TagKey<Item> TIMBER_AXE_HEAD_REPAIR_ITEM = createTag("timber_axe_head_repair_item");
        public static final TagKey<Item> NETHERITE_INGOT_REPAIR_ITEM = createTag("netherite_ingot_repair_item");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(ITMG.MOD_ID, name));
        }
    }
}
