package net.intheminecraftgalaxy.itmg.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.intheminecraftgalaxy.itmg.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
    //private static final RegistryKey<LootTable> COAL_ORE_LOOT_TABLE_ID = Blocks.COAL_ORE.getLootTableKey();
    private static final RegistryKey<LootTable> TRIAL_CHAMBERS_REWARD_OMINOUS_UNIQUE =
            RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of("minecraft", "chests/trial_chambers/reward_ominous_unique"));

    public static void modifyLootTables() {
        /*.MODIFY.register((key, tableBuilder, source) -> {
            if(COAL_ORE_LOOT_TABLE_ID.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f)) // Drops 100% of the time
                        .with(ItemEntry.builder(ModItems.CRAFTING_KEY))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
        });*/

        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (TRIAL_CHAMBERS_REWARD_OMINOUS_UNIQUE.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.075f)) // Drops 7.5% of the time
                        .with(ItemEntry.builder(ModItems.CRAFTING_KEY)) // Use your custom item
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}