package com.mike.extratools.item;

import com.mike.extratools.ExtraToolsMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExtraToolsMod.MODID);

    public static final DeferredItem<Item> COPPER_HAMMER = ITEMS.register("copper_hammer",
            () -> new Item(new Item.Properties()));




    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
