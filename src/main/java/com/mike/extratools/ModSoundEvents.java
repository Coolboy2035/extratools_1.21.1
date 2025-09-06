package com.mike.extratools;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, ExtraToolsMod.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> ZHANGE_SOUND = SOUND_EVENTS.register("block.zhange_sound",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID, "block.zhange_sound"),16F));


    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}