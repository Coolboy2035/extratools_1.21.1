package com.mike.extratools.block;

import com.mike.extratools.ModSoundEvents;
import net.minecraft.client.Minecraft;
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
            ModSoundEvents.ZHANGE_SOUND.get()
    };
    private static int currentSoundIndex = 0;

    public KangzhanBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        // 检查是否按下了Shift键
        if (player.isShiftKeyDown()) {
            if (level.isClientSide()) {
                SoundEvent soundToPlay = SOUNDS[currentSoundIndex];

                level.playSound(
                        player,
                        pos,
                        soundToPlay,
                        SoundSource.BLOCKS,
                        1.0F,
                        1.0F
                );

                // 移动到下一个声音（循环播放）
                currentSoundIndex = (currentSoundIndex + 1) % SOUNDS.length;
            }

            // 在服务器端也更新索引以保持同步
            if (!level.isClientSide()) {
                currentSoundIndex = (currentSoundIndex + 1) % SOUNDS.length;
            }

            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }

        // 如果没有按下Shift键，执行默认行为
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}