package net.intheminecraftgalaxy.itmg.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.loader.api.metadata.ModOrigin;
import net.intheminecraftgalaxy.itmg.item.ModItems;
import net.intheminecraftgalaxy.itmg.util.ModTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.SNOW_SWORD);
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.VEIN_MINING_PICKAXE_ITEM);

        getOrCreateTagBuilder(ModTags.Items.SNOWBALL_REPAIR_ITEM)
                .add(Items.SNOWBALL);
        getOrCreateTagBuilder(ModTags.Items.TIMBER_AXE_HEAD_REPAIR_ITEM)
                .add(ModItems.TIMBER_AXE_HEAD);
        getOrCreateTagBuilder(ModTags.Items.NETHERITE_INGOT_REPAIR_ITEM)
                .add(Items.NETHERITE_INGOT);
    }
}
