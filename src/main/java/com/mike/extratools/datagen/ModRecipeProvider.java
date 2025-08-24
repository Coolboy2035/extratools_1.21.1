package com.mike.extratools.datagen;

import com.mike.extratools.ExtraToolsMod;
import com.mike.extratools.blocks.ModBlocks;
import com.mike.extratools.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.chunk.storage.ChunkSerializer;
import net.neoforged.neoforge.common.conditions.*;
import net.neoforged.neoforge.common.crafting.ConditionalRecipeOutput;
import org.spongepowered.include.com.google.gson.JsonObject;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.List;


public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }


    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SILVER_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.SILVER_INGOT.get())
                .unlockedBy("has_silver_ingot", has(ModItems.SILVER_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 9)
                .requires(ModBlocks.SILVER_BLOCK.get())
                .unlockedBy("has_silver_block", has(ModBlocks.SILVER_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_SWORD.get())
                .pattern(" B ")
                .pattern(" B ")
                .pattern(" A ")
                .define('B', Items.COPPER_INGOT)
                .define('A', Items.STICK)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_PICKAXE.get())
                .pattern("BBB")
                .pattern(" A ")
                .pattern(" A ")
                .define('B', Items.COPPER_INGOT)
                .define('A', Items.STICK)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(recipeOutput);

    }
}
