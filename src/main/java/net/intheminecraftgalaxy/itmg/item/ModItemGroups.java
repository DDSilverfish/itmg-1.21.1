package net.intheminecraftgalaxy.itmg.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.intheminecraftgalaxy.itmg.ITMG;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup LIFESTEAL = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(ITMG.MOD_ID, "lifesteal_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.HEART))
                    .displayName(Text.translatable("itemgroup.itmg.lifesteal_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.HEART_STATE_1);
                        entries.add(ModItems.HEART);
                    }).build());

    public static final ItemGroup UNIQUE_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(ITMG.MOD_ID, "unique_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.SNOW_SWORD))
                    .displayName(Text.translatable("itemgroup.itmg.unique_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.SNOW_SWORD);
                    }).build());

    public static void registerItemGroups() {
        ITMG.LOGGER.info("Registering Item Groups for " + ITMG.MOD_ID);
    }
}
