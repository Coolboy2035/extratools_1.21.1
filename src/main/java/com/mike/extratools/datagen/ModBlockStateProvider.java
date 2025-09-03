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
        blockWithItem(ModBlocks.KANGZHAN_BLOCK);

        cutoutBlockWithItem(ModBlocks.KAIWEN_BLOCK);
        cutoutBlockWithItem(ModBlocks.XUANYU_BLOCK);

    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
    private void cutoutBlockWithItem(DeferredBlock<?> deferredBlock){
        Block block = deferredBlock.get();
        String blockId = deferredBlock.getId().getPath();
        ModelFile model =models().cubeAll(blockId,
                blockTexture(block)).renderType("cutout");
        simpleBlockWithItem(block,model);
    }
}
