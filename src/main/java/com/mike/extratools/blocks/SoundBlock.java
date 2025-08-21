package com.mike.extratools.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoundBlock extends Block implements SoundBlockPort {
    private  static final List<SoundEvent> VILLAGER_SOUNDS = new ArrayList<>();
    private int currentSoundIndex = 0;

    static {
        VILLAGER_SOUNDS.add(SoundEvents.VILLAGER_HURT);
        VILLAGER_SOUNDS.add(SoundEvents.VILLAGER_DEATH);
        VILLAGER_SOUNDS.add(SoundEvents.VILLAGER_YES);
        VILLAGER_SOUNDS.add(SoundEvents.VILLAGER_NO);
        VILLAGER_SOUNDS.add(SoundEvents.VILLAGER_AMBIENT);
        VILLAGER_SOUNDS.add(SoundEvents.VILLAGER_TRADE);
    }

    public SoundBlock(Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult){
        if (!level.isClientSide && !VILLAGER_SOUNDS.isEmpty()){
            Random random = new Random();

            SoundEvent randomSound = VILLAGER_SOUNDS.get(random.nextInt(VILLAGER_SOUNDS.size()));
            level.playSound(null,
                    pos, randomSound, SoundSource.NEUTRAL,1.0F,1.0F);
            currentSoundIndex = (currentSoundIndex + 1)% VILLAGER_SOUNDS.size();
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

}
