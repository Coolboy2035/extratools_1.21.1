package com.mike.extratools.datagen;

import com.mike.extratools.ExtraToolsMod;
import com.mike.extratools.blocks.ModBlocks;
import com.mike.extratools.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExtraToolsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.SILVER_INGOT.get());
        basicItem(ModItems.SILVER_NUGGET.get());


        handheldItem(ModItems.COPPER_SWORD);
        handheldItem(ModItems.COPPER_PICKAXE);
    }


    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}