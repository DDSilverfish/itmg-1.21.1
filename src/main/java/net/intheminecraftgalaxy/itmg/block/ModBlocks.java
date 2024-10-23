package net.intheminecraftgalaxy.itmg.block;

import net.intheminecraftgalaxy.itmg.ITMG;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block HEART_BLOCK = registerBlock("heart_block",
            new Block(createBlockSettings("heart_block").strength(2).requiresTool()));

    private static AbstractBlock.Settings createBlockSettings(String blockName) {
        Identifier id = Identifier.of(ITMG.MOD_ID, blockName);
        RegistryKey<Block> key = RegistryKey.of(Registries.BLOCK.getKey(), id);
        return AbstractBlock.Settings.create().registryKey(key);
    }

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(ITMG.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Identifier id = Identifier.of(ITMG.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(Registries.ITEM.getKey(), id);
        Registry.register(Registries.ITEM, key,
                new BlockItem(block, new Item.Settings().registryKey(key).useBlockPrefixedTranslationKey()));
    }

    public static void registerModBlocks(){
        ITMG.LOGGER.info("Registering Mod Blocks for " + ITMG.MOD_ID);
    }
}
