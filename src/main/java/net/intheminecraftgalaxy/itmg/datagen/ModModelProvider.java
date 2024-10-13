package net.intheminecraftgalaxy.itmg.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.intheminecraftgalaxy.itmg.item.ModItems;
import net.minecraft.data.client.*;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.HEART_STATE_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.HEART, Models.GENERATED);
        itemModelGenerator.register(ModItems.SNOW_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TIMBER_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TIMBER_AXE_HEAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRAFTING_KEY, Models.GENERATED);
    }
}
