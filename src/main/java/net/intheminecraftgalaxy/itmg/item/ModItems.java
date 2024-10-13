package net.intheminecraftgalaxy.itmg.item;

import net.intheminecraftgalaxy.itmg.ITMG;
import net.intheminecraftgalaxy.itmg.item.custom.HeartItem;
import net.intheminecraftgalaxy.itmg.item.custom.SnowSwordItem;
import net.intheminecraftgalaxy.itmg.item.custom.TimberAxeItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;


public class ModItems {

    public static final Item HEART_STATE_1 = registerItem("heart_state_1", new Item(new Item.Settings().maxCount(16)));
    public static final Item HEART = registerItem("heart", new HeartItem(new Item.Settings().maxCount(1)));

    public static final Item CRAFTING_KEY = registerItem("crafting_key", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));

    public static final Item SNOW_SWORD = registerItem("snow_sword",
            new SnowSwordItem(ModToolMaterials.SNOW_TOOL, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.SNOW_TOOL, 3, -2.4f))));

    //public static final Item SNOW_SWORD = registerItem("snow_sword",
    //        new SnowSwordItem(ModToolMaterials.SNOW_TOOL, new Item.Settings()
    //                ));


    public static final Item TIMBER_AXE = registerItem("timber_axe",
            new TimberAxeItem(ModToolMaterials.TIMBER, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.TIMBER, 1, -2.8f))
            ));
    public static final Item TIMBER_AXE_HEAD = registerItem("timber_axe_head", new Item(new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ITMG.MOD_ID, name), item);
    }


    public static void registerModItems(){
        ITMG.LOGGER.info("Registering Mod Items for " + ITMG.MOD_ID);
    }
}
