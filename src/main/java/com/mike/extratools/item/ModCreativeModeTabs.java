package com.mike.extratools.item;

import com.mike.extratools.ExtraToolsMod;
import com.mike.extratools.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExtraToolsMod.MODID);

    public static final Supplier<CreativeModeTab> EXTRA_TOOLS_TAB = CREATIVE_MODE_TAB.register("extratools_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SILVER_INGOT.get()))
                    .title(Component.translatable("creativetab.extratoolsmod.extratools"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(Items.COPPER_INGOT);
                        output.accept(ModItems.COPPER_SWORD);
                        output.accept(ModItems.COPPER_PICKAXE);
                        
                        //output.accept(ModItems.COPPER_HAMMER);
                        output.accept(ModItems.SILVER_NUGGET);
                        output.accept(ModBlocks.SILVER_ORE);
                        output.accept(ModItems.SILVER_INGOT);
                        output.accept(ModBlocks.SILVER_BLOCK);

                    }).build());
    public static final Supplier<CreativeModeTab> EXTRA_PEERS_TAB = CREATIVE_MODE_TAB.register("extrapeers_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.XUANYU_BLOCK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID,"extratools_tab"))
                    .title(Component.translatable("creativetab.extratoolsmod.extrapeers"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.XUANYU_BLOCK);
                        output.accept(ModBlocks.KAIWEN_BLOCK);
                        output.accept(ModBlocks.KANGZHAN_BLOCK);

                    }).build());



    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
