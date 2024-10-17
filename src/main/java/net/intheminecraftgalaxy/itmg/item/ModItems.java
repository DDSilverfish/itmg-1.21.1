package net.intheminecraftgalaxy.itmg.item;

import net.intheminecraftgalaxy.itmg.ITMG;
import net.intheminecraftgalaxy.itmg.item.custom.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;


public class ModItems {

    public static final Item HEART_STATE_1 = registerItem("heart_state_1", new Item(new Item.Settings().maxCount(16)));
    public static final Item HEART = registerItem("heart", new HeartItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));

    public static final Item CRAFTING_KEY = registerItem("crafting_key", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.tutorialmod.crafting_key"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item SNOW_SWORD = registerItem("snow_sword",
            new SnowSwordItem(ModToolMaterials.SNOW_TOOL, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.SNOW_TOOL, 3, -2.4f)).rarity(Rarity.EPIC)));

    //public static final Item SNOW_SWORD = registerItem("snow_sword",
    //        new SnowSwordItem(ModToolMaterials.SNOW_TOOL, new Item.Settings()
    //                ));

    public static final Item VEIN_MINING_PICKAXE_ITEM = registerItem("vein_mining_pickaxe",
            new VeinMiningPickaxeItem(ModToolMaterials.VeinMiningPickaxe, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.VeinMiningPickaxe, 1, -2.8f))
                    .rarity(Rarity.EPIC)));

    public static final Item TIMBER_AXE = registerItem("timber_axe",
            new TimberAxeItem(ModToolMaterials.TIMBER, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.TIMBER, 6, -3.2f)).rarity(Rarity.EPIC)
            ));

    public static final Item TIMBER_AXE_HEAD = registerItem("timber_axe_head", new Item(new Item.Settings().maxCount(1)));

    public static final Item MINING_STAFF = registerItem("mining_staff", new MiningStaffItem(new Item.Settings().maxDamage(32).rarity(Rarity.EPIC)));

    public static final Item THUNDERBLADE = registerItem("thunderblade", new ThunderBladeItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ITMG.MOD_ID, name), item);
    }


    public static void registerModItems(){
        ITMG.LOGGER.info("Registering Mod Items for " + ITMG.MOD_ID);
    }
}
