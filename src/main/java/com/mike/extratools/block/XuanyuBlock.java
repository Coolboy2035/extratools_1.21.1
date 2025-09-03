package com.mike.extratools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
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

public class XuanyuBlock extends Block {
    private static final SoundEvent[] SOUNDS = {
        SoundEvents.VILLAGER_AMBIENT,
        SoundEvents.VILLAGER_HURT,
        SoundEvents.VILLAGER_DEATH,
        SoundEvents.VILLAGER_YES,
        SoundEvents.VILLAGER_NO,
        SoundEvents.VILLAGER_TRADE
    };

    public XuanyuBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            Random random = new Random();
            
            SoundEvent soundToPlay = SOUNDS[random.nextInt(SOUNDS.length)];
            
            level.playSound(
                null,
                pos,
                soundToPlay,
                SoundSource.BLOCKS,
                1.0F,
                1.0F
            );
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }
}
