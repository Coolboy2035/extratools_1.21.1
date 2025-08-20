package com.mike.extratools.datagen;

import com.mike.extratools.ExtraToolsMod;
import com.mike.extratools.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper){
        super(output, ExtraToolsMod.MOD_ID, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels(){
        blockWithItem(ModBlocks.SILVER_BLOCK);
        blockWithItem(ModBlocks.KAIWEN_BLOCK);
        blockWithItem(ModBlocks.XUANYU_BLOCK);

    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
