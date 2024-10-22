package net.intheminecraftgalaxy.itmg.item;

import com.google.common.base.Suppliers;
import net.intheminecraftgalaxy.itmg.InTheMinecraftGalaxyConfig;
import net.intheminecraftgalaxy.itmg.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial{
    SNOW_TOOL(ModTags.Blocks.INCORRECT_FOR_SNOW_TOOL,
            InTheMinecraftGalaxyConfig.snowSwordDurability, 1.0F, 0.0f, 22, () -> Ingredient.ofItems(Items.SNOWBALL)),
    TIMBER(ModTags.Blocks.INCORRECT_FOR_TIMBER_TOOL, InTheMinecraftGalaxyConfig.timberAxeDurability, 6.0F, 2.0F, 14, () -> Ingredient.ofItems(ModItems.TIMBER_AXE_HEAD)),
    VeinMiningPickaxe(ModTags.Blocks.INCORRECT_FOR_VEINMININGPICKAXE_TOOL, InTheMinecraftGalaxyConfig.veinMiningPickaxeDurability, 6.0F, 2.0F, 14, () -> Ingredient.ofItems(ModItems.TIMBER_AXE_HEAD));// New Timber material

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(final TagKey<Block> inverseTag, final int itemDurability, final float miningSpeed,
                     final float attackDamage, final int enchantability, final Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}

