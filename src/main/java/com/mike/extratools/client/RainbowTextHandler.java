package com.mike.extratools.client;

import com.mike.extratools.ExtraToolsMod;
import com.mike.extratools.block.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

/**
 * 彩虹文字处理器
 * 处理抗战方块的彩色流动文字效果
 */
@EventBusSubscriber(modid = ExtraToolsMod.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.FORGE)
public class RainbowTextHandler {
    
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        
        // 检查是否是抗战方块
        if (stack.getItem() instanceof BlockItem blockItem) {
            if (blockItem.getBlock() == ModBlocks.KANGZHAN_BLOCK.get()) {
                // 清除原有的名称
                event.getToolTip().clear();
                
                // 创建彩色流动文字
                MutableComponent rainbowText = createRainbowText("抗日战争胜利80周年", 1.0f);
                event.getToolTip().add(rainbowText);
            }
        }
    }
    
    /**
     * 创建彩虹流动文字
     * @param text 文本内容
     * @param speed 流动速度
     * @return 彩色文本组件
     */
    public static MutableComponent createRainbowText(String text, float speed) {
        MutableComponent result = Component.empty();
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int color = ColorCycleHelper.getRedGoldColor(i * 0.5f, speed);
            
            // 为每个字符创建独立的样式
            MutableComponent charComponent = Component.literal(String.valueOf(c))
                    .setStyle(Style.EMPTY.withColor(color).withBold(true));
            
            result.append(charComponent);
        }
        
        return result;
    }
    
    /**
     * 创建平滑彩虹文字
     */
    public static MutableComponent createSmoothRainbowText(String text, float speed) {
        MutableComponent result = Component.empty();
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int color = ColorCycleHelper.getSmoothRainbowColor(i * 0.3f, speed);
            
            MutableComponent charComponent = Component.literal(String.valueOf(c))
                    .setStyle(Style.EMPTY.withColor(color).withBold(true));
            
            result.append(charComponent);
        }
        
        return result;
    }
    
    /**
     * 创建国旗色系文字（红黄交替）
     */
    public static MutableComponent createChinaFlagText(String text, float speed) {
        MutableComponent result = Component.empty();
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int color = ColorCycleHelper.getChinaFlagColor(i * 0.8f, speed);
            
            MutableComponent charComponent = Component.literal(String.valueOf(c))
                    .setStyle(Style.EMPTY.withColor(color).withBold(true));
            
            result.append(charComponent);
        }
        
        return result;
    }
}
