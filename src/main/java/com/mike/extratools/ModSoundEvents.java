package com.mike.extratools;

import com.mike.extratools.ExtraToolsMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, ExtraToolsMod.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> DING1_SOUND = SOUND_EVENTS.register("block.ding1_sound",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID, "block.ding1_sound"),16F));

    public static final DeferredHolder<SoundEvent, SoundEvent> DING2_SOUND = SOUND_EVENTS.register("block.ding2_sound",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID,"block.ding2_sound"),16F));

    public static final DeferredHolder<SoundEvent, SoundEvent> DING3_SOUND = SOUND_EVENTS.register("block.ding3_sound",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID,"block.ding3_sound"),16F));

    public static final DeferredHolder<SoundEvent, SoundEvent> DING4_SOUND = SOUND_EVENTS.register("block.ding4_sound",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraToolsMod.MOD_ID,"block.ding4_sound"),16F));
}