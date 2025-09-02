package com.mike.extratools.sounds;

import com.mike.extratools.ExtraTools;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = 
        DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, ExtraTools.MODID);

    // 注册自定义声音事件
    public static final DeferredHolder<SoundEvent, SoundEvent> DINGZHEN_SOUND = register("block.dingshen_sound");
    private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> 
            SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(ExtraTools.MODID, name))
        );
    }
}