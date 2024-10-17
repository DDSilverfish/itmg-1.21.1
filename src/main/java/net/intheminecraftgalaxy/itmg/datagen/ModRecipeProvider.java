package net.intheminecraftgalaxy.itmg.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.intheminecraftgalaxy.itmg.ITMG;
import net.intheminecraftgalaxy.itmg.block.ModBlocks;
import net.intheminecraftgalaxy.itmg.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.HEART_STATE_1, RecipeCategory.MISC, ModBlocks.HEART_BLOCK);


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
                .offerTo(exporter, Identifier.of(ITMG.MOD_ID, "heart_state_1_from_items"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HEART)
                .pattern(" @ ")
                .pattern("@#@")
                .pattern(" @ ")
                .input('@', ModBlocks.HEART_BLOCK)
                .input('#', Items.TOTEM_OF_UNDYING)
                .criterion(hasItem(ModItems.HEART_STATE_1), conditionsFromItem(ModItems.HEART_STATE_1))
                .criterion(hasItem(Items.TOTEM_OF_UNDYING), conditionsFromItem(Items.TOTEM_OF_UNDYING))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.VEIN_MINING_PICKAXE_ITEM)
                .pattern(" # ")
                .pattern("K@K")
                .input('@', Items.NETHERITE_PICKAXE)
                .input('#', Blocks.NETHERITE_BLOCK)
                .input('K', ModItems.CRAFTING_KEY)
                .criterion(hasItem(ModItems.CRAFTING_KEY), conditionsFromItem(ModItems.CRAFTING_KEY))
                .criterion(hasItem(Blocks.NETHERITE_BLOCK), conditionsFromItem(Blocks.NETHERITE_BLOCK))
                .criterion(hasItem(ModItems.CRAFTING_KEY), conditionsFromItem(ModItems.CRAFTING_KEY))
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

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.THUNDERBLADE)
                .pattern("ABC")
                .pattern("DEF")
                .input('A', Items.BREEZE_ROD)
                .input('B', Items.DRAGON_BREATH)
                .input('C', Items.ECHO_SHARD)
                .input('D', ModItems.CRAFTING_KEY)
                .input('E', Items.PHANTOM_MEMBRANE)
                .input('F', Items.CONDUIT)
                .criterion(hasItem(Items.BREEZE_ROD), conditionsFromItem(Items.BREEZE_ROD))
                .criterion(hasItem(Items.DRAGON_BREATH), conditionsFromItem(Items.DRAGON_BREATH))
                .criterion(hasItem(Items.ECHO_SHARD), conditionsFromItem(Items.ECHO_SHARD))
                .criterion(hasItem(Items.PHANTOM_MEMBRANE), conditionsFromItem(Items.PHANTOM_MEMBRANE))
                .criterion(hasItem(Items.CONDUIT), conditionsFromItem(Items.CONDUIT))
                .criterion(hasItem(ModItems.CRAFTING_KEY), conditionsFromItem(ModItems.CRAFTING_KEY))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TIMBER_AXE_HEAD)
                .pattern("SS")
                .pattern("S ")
                .input('S', Items.IRON_BLOCK)
                .criterion(hasItem(Items.IRON_BLOCK), conditionsFromItem(Items.IRON_BLOCK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TIMBER_AXE)
                .pattern("KTS")
                .input('S', ModItems.TIMBER_AXE_HEAD)
                .input('T', Items.IRON_AXE)
                .input('K', ModItems.CRAFTING_KEY)
                .criterion(hasItem(ModItems.TIMBER_AXE_HEAD), conditionsFromItem(ModItems.TIMBER_AXE_HEAD))
                .criterion(hasItem(Items.IRON_AXE), conditionsFromItem(Items.IRON_AXE))
                .criterion(hasItem(ModItems.CRAFTING_KEY), conditionsFromItem(ModItems.CRAFTING_KEY))
                .offerTo(exporter);

    }
}
