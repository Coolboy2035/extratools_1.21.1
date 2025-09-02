package com.mike.extratools.blocks;

import com.mike.extratools.sounds.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.InteractionHand;

import java.util.Random;

public class SoundBlock extends Block {

    // 使用自定义声音事件
    private static final SoundEvent[] CUSTOM_SOUNDS = {
        ModSoundEvents.DINGZHEN_SOUND.get()
    };

    public SoundBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            Random random = new Random();
            // 随机选择一个自定义声音
            SoundEvent soundToPlay = CUSTOM_SOUNDS[random.nextInt(CUSTOM_SOUNDS.length)];
            
            // 播放自定义声音
            level.playSound(
                null,
                pos,
                soundToPlay,
                net.minecraft.sounds.SoundSource.BLOCKS,
                1.0F,
                1.0F
            );
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }
}