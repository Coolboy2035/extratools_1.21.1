package com.mike.extratools.block;

import com.mike.extratools.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.InteractionHand;

public class KangzhanBlock extends Block {
    private static final SoundEvent[] SOUNDS = {
            ModSoundEvents.ZHANGE1_SOUND.get(),
            ModSoundEvents.ZHANGE2_SOUND.get(),
            ModSoundEvents.ZHANGE3_SOUND.get(),
            ModSoundEvents.ZHANGE4_SOUND.get(),
            ModSoundEvents.ZHANGE5_SOUND.get(),
            ModSoundEvents.ZHANGE6_SOUND.get(),
            ModSoundEvents.ZHANGE7_SOUND.get(),
            ModSoundEvents.ZHANGE8_SOUND.get(),
            ModSoundEvents.ZHANGE9_SOUND.get()
    };

    public KangzhanBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            // 直接在客户端播放所有声音
            for (SoundEvent sound : SOUNDS) {
                level.playSound(player, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }
}