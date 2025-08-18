package com.mike.extratools.item;

import com.mike.extratools.ExtraToolsMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExtraToolsMod.MODID);

    //public static final DeferredItem<HammerItem> COPPER_HAMMER = ITEMS.register("copper_hammer",
            //()-> new HammerItem(Tiers.IRON,new Item.Properties()
                    //.attributes(HammerItem.createAttributes(Tiers.IRON, 1.0F, -2.8F))
                    //.stacksTo(1).durability(128)));       //.rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<PickaxeItem> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            ()->new PickaxeItem(ModToolTiers.COPPER,new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.COPPER,1F,-2.8F))));
    public static final DeferredItem<SwordItem> COPPER_SWORD = ITEMS.register("copper_sword",
            ()->new SwordItem(ModToolTiers.COPPER,new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.COPPER,4F,-1F))));








    public static final DeferredItem<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
            ()-> new Item(new Item.Properties()));




    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
