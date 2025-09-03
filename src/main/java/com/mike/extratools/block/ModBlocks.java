package com.mike.extratools.block;

import com.mike.extratools.ExtraToolsMod;
import com.mike.extratools.item.ModItems;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ExtraToolsMod.MODID);

    public static final DeferredBlock<Block> SILVER_BLOCK = registerBlock("silver_block",
            ()-> new Block(BlockBehaviour.Properties.of()
                    .strength(1F,6F).requiresCorrectToolForDrops().sound(SoundType.METAL)));
                                        //破坏时间-4=实际填的数
    public static final DeferredBlock<Block> SILVER_ORE = registerBlock("silver_ore",
            ()-> new DropExperienceBlock(ConstantInt.of(0),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F)
                            .sound(SoundType.STONE)));
    public static final DeferredBlock<Block> XUANYU_BLOCK = registerBlock("xuanyu_block",
            ()-> new XuanyuBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
                    .noOcclusion().sound(SoundType.GLASS)));
    public static final DeferredBlock<Block> KAIWEN_BLOCK = registerBlock("kaiwen_block",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
                    .noOcclusion().sound(SoundType.GLASS).noLootTable()));


    public static final DeferredBlock<Block> KANGZHAN_BLOCK = registerBlock("kangzhan_block",
            ()-> new KangzhanBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.AMETHYST).noLootTable()));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventbus) {
        BLOCKS.register(eventbus);
    }
}
