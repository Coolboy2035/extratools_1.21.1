package com.mike.extratools.datagen;

import com.mike.extratools.ExtraToolsMod;
import com.mike.extratools.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper){
        super(output, ExtraToolsMod.MOD_ID, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels(){
        blockWithItem(ModBlocks.SILVER_BLOCK);
        blockWithItem(ModBlocks.SILVER_ORE);


        cutoutBlockWithItem(ModBlocks.KAIWEN_BLOCK,"cutout");
        cutoutBlockWithItem(ModBlocks.XUANYU_BLOCK,"cutout");

    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
    private void cutoutBlockWithItem(DeferredBlock<?> deferredBlock,String renderType){
        Block block = deferredBlock.get();
        String blockId = deferredBlock.getId().getPath();
        ModelFile model =models().cubeAll(blockId,
                blockTexture(block)).renderType(renderType);
        simpleBlockWithItem(block,model);
    }
}
