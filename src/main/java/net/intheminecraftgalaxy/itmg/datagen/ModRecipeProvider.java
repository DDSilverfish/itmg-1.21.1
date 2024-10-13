package net.intheminecraftgalaxy.itmg.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.intheminecraftgalaxy.itmg.item.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HEART_STATE_1)
                .pattern("A@A")
                .pattern("@#@")
                .pattern("A@A")
                .input('#', Items.NETHER_STAR)
                .input('@', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .input('A', Items.REDSTONE)
                .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .criterion(hasItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HEART)
                .pattern(" @ ")
                .pattern("@#@")
                .pattern(" @ ")
                .input('@', ModItems.HEART_STATE_1)
                .input('#', Items.TOTEM_OF_UNDYING)
                .criterion(hasItem(ModItems.HEART_STATE_1), conditionsFromItem(ModItems.HEART_STATE_1))
                .criterion(hasItem(Items.TOTEM_OF_UNDYING), conditionsFromItem(Items.TOTEM_OF_UNDYING))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SNOW_SWORD)
                .pattern(" S ")
                .pattern(" S ")
                .pattern("KT ")
                .input('S', Items.SNOWBALL)
                .input('T', Items.STICK)
                .input('K', ModItems.CRAFTING_KEY)
                .criterion(hasItem(Items.SNOWBALL), conditionsFromItem(Items.SNOWBALL))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.CRAFTING_KEY), conditionsFromItem(ModItems.CRAFTING_KEY))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TIMBER_AXE_HEAD)
                .pattern("ST")
                .pattern("T ")
                .input('S', Items.IRON_BLOCK)
                .input('T', Items.IRON_INGOT)
                .criterion(hasItem(Items.BEDROCK), conditionsFromItem(Items.BEDROCK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TIMBER_AXE)
                .pattern(" S ")
                .pattern(" T ")
                .pattern("KT ")
                .input('S', ModItems.TIMBER_AXE_HEAD)
                .input('T', Items.STICK)
                .input('K', ModItems.CRAFTING_KEY)
                .criterion(hasItem(ModItems.TIMBER_AXE_HEAD), conditionsFromItem(ModItems.TIMBER_AXE_HEAD))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.CRAFTING_KEY), conditionsFromItem(ModItems.CRAFTING_KEY))
                .offerTo(exporter);

    }
}
