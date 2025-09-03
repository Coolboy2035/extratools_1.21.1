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

    public static final DeferredHolder<SoundEvent, SoundEvent> ZHANGE1_SOUND = SOUND_EVENTS.register("block.zhange1_sound",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID, "block.zhange1_sound"),16F));

    public static final DeferredHolder<SoundEvent, SoundEvent> ZHANGE2_SOUND = SOUND_EVENTS.register("block.zhange2_sound",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID,"block.zhange2_sound"),16F));

    public static final DeferredHolder<SoundEvent, SoundEvent> ZHANGE3_SOUND = SOUND_EVENTS.register("block.zhange3_sound",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID,"block.zhange3_sound"),16F));

    public static final DeferredHolder<SoundEvent, SoundEvent> ZHANGE4_SOUND = SOUND_EVENTS.register("block.zhange4_sound",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID,"block.zhange4_sound"),16F));

    public static final DeferredHolder<SoundEvent, SoundEvent> ZHANGE5_SOUND = SOUND_EVENTS.register("block.zhange5_sound",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID,"block.zhange5_sound"),16F));

    public static final DeferredHolder<SoundEvent, SoundEvent> ZHANGE6_SOUND = SOUND_EVENTS.register("block.zhange6_sound",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID,"block.zhange6_sound"),16F));

    public static final DeferredHolder<SoundEvent, SoundEvent> ZHANGE7_SOUND = SOUND_EVENTS.register("block.zhange7_sound",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID,"block.zhange7_sound"),16F));

    public static final DeferredHolder<SoundEvent, SoundEvent> ZHANGE8_SOUND = SOUND_EVENTS.register("block.zhange8_sound",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID,"block.zhange8_sound"),16F));

    public static final DeferredHolder<SoundEvent, SoundEvent> ZHANGE9_SOUND = SOUND_EVENTS.register("block.zhange9_sound",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID,"block.zhange9_sound"),16F));

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}