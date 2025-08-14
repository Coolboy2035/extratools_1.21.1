package com.mike.extratools.item;

import com.mike.extratools.ExtraToolsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExtraToolsMod.MODID);

    public static final Supplier<CreativeModeTab> EXTRAL_TOOLS_TAB = CREATIVE_MODE_TAB.register("extratools_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.COPPER_HAMMER.get()))
                    .title(Component.translatable("creativetab.extratoolsmod.extratools"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.COPPER_HAMMER);

                    }).build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
