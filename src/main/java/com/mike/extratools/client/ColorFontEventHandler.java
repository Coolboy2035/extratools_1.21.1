package com.mike.extratools.client;

import com.mike.extratools.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

/**
 * 彩色字体事件处理器
 * 负责在各种场景中为抗战方块应用彩色字体效果
 */
public class ColorFontEventHandler {
    
    /**
     * 处理物品提示事件，为抗战方块应用彩色字体
     */
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        
        // 检查是否是抗战方块
        if (stack.getItem() instanceof BlockItem blockItem) {
            if (blockItem.getBlock() == ModBlocks.KANGZHAN_BLOCK.get()) {
                // 替换第一行（物品名称）为彩色文字
                if (!event.getToolTip().isEmpty()) {
                    event.getToolTip().set(0, ColorFontEffects.createColorText("抗日战争胜利80周年"));
                }
            }
        }
    }
    
    /**
     * 检查物品堆栈是否需要彩色字体效果
     * 这个方法可以被其他地方调用
     */
    public static boolean hasColorFontEffect(ItemStack stack) {
        if (stack.getItem() instanceof BlockItem blockItem) {
            return blockItem.getBlock() == ModBlocks.KANGZHAN_BLOCK.get();
        }
        return false;
    }
    
    /**
     * 获取抗战方块的彩色名称
     * 可用于快捷栏、物品栏等各种显示场景
     */
    public static Component getColoredItemName() {
        return ColorFontEffects.createColorText("抗日战争胜利80周年");
    }
}