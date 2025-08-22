package com.mike.extratools.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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

    public SoundBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            Random random = new Random();
            // 简单测试：只播放一个声音
            level.playSound(
                null,
                pos,
                SoundEvents.VILLAGER_HURT,
                SoundSource.BLOCKS,
                1.0F,
                1.0F
            );
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }
}
