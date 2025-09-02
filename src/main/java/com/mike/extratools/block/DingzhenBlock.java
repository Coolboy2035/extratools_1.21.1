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

public class DingzhenBlock extends Block {
    private static final SoundEvent[] SOUNDS = {
            ModSoundEvents.DING1_SOUND.get(),
            ModSoundEvents.DING2_SOUND.get(),
            ModSoundEvents.DING3_SOUND.get(),
            ModSoundEvents.DING4_SOUND.get(),
            ModSoundEvents.DING5_SOUND.get(),
            ModSoundEvents.DING6_SOUND.get()

    };
    private static int currentSoundIndex = 0;

    public DingzhenBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            SoundEvent soundToPlay = SOUNDS[currentSoundIndex];

            level.playSound(
                    null,
                    pos,
                    soundToPlay,
                    SoundSource.BLOCKS,
                    1.0F,
                    1.0F
            );

            // 移动到下一个声音（循环播放）
            currentSoundIndex = (currentSoundIndex + 1) % SOUNDS.length;

    
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }
}
