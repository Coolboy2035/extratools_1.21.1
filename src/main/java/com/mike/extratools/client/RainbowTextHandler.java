package com.mike.extratools.client;

import com.mike.extratools.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

/**
 * 彩虹文字处理器
 * 处理抗战方块的彩色流动文字效果
 */
public class RainbowTextHandler {
    
    // 配置选项：true = 国旗配色，false = 彩虹配色
    // 在这里修改设置即可切换颜色方案
    private static boolean useNationalFlagColors = true;
    
    /**
     * 切换颜色方案的辅助方法
     * 调用此方法可以动态切换（如果需要的话）
     */
    public static void setColorScheme(boolean useFlagColors) {
        useNationalFlagColors = useFlagColors;
    }
    
    /**
     * 获取当前颜色方案
     */
    public static boolean isUsingNationalFlagColors() {
        return useNationalFlagColors;
    }
    
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        
        // 检查是否是抗战方块
        if (stack.getItem() instanceof BlockItem blockItem) {
            if (blockItem.getBlock() == ModBlocks.KANGZHAN_BLOCK.get()) {
                // 替换第一行（物品名称）为彩色文字
                if (!event.getToolTip().isEmpty()) {
                    MutableComponent coloredText;
                    if (useNationalFlagColors) {
                        coloredText = createNationalFlagText("抗日战争胜利80周年");
                    } else {
                        coloredText = createRainbowText("抗日战争胜利80周年");
                    }
                    event.getToolTip().set(0, coloredText);
                }
            }
        }
    }
    
    /**
     * 创建国旗配色流动文字（多彩版本）
     * 包含红色、金色、深红、橙黄等多种变化
     */
    private static MutableComponent createNationalFlagText(String text) {
        MutableComponent result = Component.empty();

        int length = text.length();
        if (length == 0) {
            return result;
        }

        long millis = System.currentTimeMillis();
        long periodMs = 3500L; // 3.5 秒为一个完整循环
        float base = (millis % periodMs) / (float) periodMs; // 0-1 之间

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);

            // 不同字符在 0-1 之间均匀分布偏移
            float offset = (float) i / (float) (length - 1);
            float position = (base + offset) % 1.0f;

            int rgb;
            // 多色系：基于国旗色系扩展，包含多种红色和金色变化
            float segment = position * 4; // 分成4个段落
            
            if (segment < 1.0f) {
                // 深红 (#C70000) 到 红色 (#DE2910)
                rgb = interpolateColor(0xC70000, 0xDE2910, segment);
            } else if (segment < 2.0f) {
                // 红色 (#DE2910) 到 金色 (#FFDE00)  
                rgb = interpolateColor(0xDE2910, 0xFFDE00, segment - 1.0f);
            } else if (segment < 3.0f) {
                // 金色 (#FFDE00) 到 橙黄 (#FFA500)
                rgb = interpolateColor(0xFFDE00, 0xFFA500, segment - 2.0f);
            } else {
                // 橙黄 (#FFA500) 到 深红 (#C70000)
                rgb = interpolateColor(0xFFA500, 0xC70000, segment - 3.0f);
            }

            MutableComponent charComponent = Component.literal(String.valueOf(c))
                    .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(rgb)).withBold(true));

            result.append(charComponent);
        }

        return result;
    }
    
    /**
     * 颜色插值方法
     * @param color1 起始颜色 0xRRGGBB
     * @param color2 结束颜色 0xRRGGBB  
     * @param t 插值参数 0.0-1.0
     * @return 插值后的颜色
     */
    private static int interpolateColor(int color1, int color2, float t) {
        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;
        
        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;
        
        int r = (int) (r1 + (r2 - r1) * t);
        int g = (int) (g1 + (g2 - g1) * t);
        int b = (int) (b1 + (b2 - b1) * t);
        
        return (r << 16) | (g << 8) | b;
    }

    /**
     * 创建彩虹流动文字（真正的流动效果）
     * 使用系统时间 + 字符序号计算 HSV 彩虹颜色
     */
    private static MutableComponent createRainbowText(String text) {
        MutableComponent result = Component.empty();

        int length = text.length();
        if (length == 0) {
            return result;
        }

        long millis = System.currentTimeMillis();
        long periodMs = 4000L; // 4 秒为一个完整循环
        float base = (millis % periodMs) / (float) periodMs; // 0-1 之间

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);

            // 不同字符在 0-1 之间均匀分布偏移
            float offset = (float) i / (float) (length - 1);
            float hue = (base + offset) % 1.0f; // 0-1 之间

            int rgb = hsvToRgb01(hue, 1.0f, 1.0f);

            MutableComponent charComponent = Component.literal(String.valueOf(c))
                    .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(rgb)).withBold(true));

            result.append(charComponent);
        }

        return result;
    }

    /**
     * HSV(0-1) 转 RGB 0xRRGGBB
     */
    private static int hsvToRgb01(float h, float s, float v) {
        float r;
        float g;
        float b;

        float i = (float) Math.floor(h * 6.0f);
        float f = h * 6.0f - i;
        float p = v * (1.0f - s);
        float q = v * (1.0f - f * s);
        float t = v * (1.0f - (1.0f - f) * s);

        switch ((int) i % 6) {
            case 0:
                r = v;
                g = t;
                b = p;
                break;
            case 1:
                r = q;
                g = v;
                b = p;
                break;
            case 2:
                r = p;
                g = v;
                b = t;
                break;
            case 3:
                r = p;
                g = q;
                b = v;
                break;
            case 4:
                r = t;
                g = p;
                b = v;
                break;
            case 5:
            default:
                r = v;
                g = p;
                b = q;
                break;
        }

        int ri = (int) (r * 255.0f);
        int gi = (int) (g * 255.0f);
        int bi = (int) (b * 255.0f);
        return (ri << 16) | (gi << 8) | bi;
    }
    

}
