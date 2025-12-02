package com.mike.extratools.client;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

/**
 * 彩色字体效果类
 * 提供多种彩色字体效果，可在物品、方块等任何地方使用
 */
public class ColorFontEffects {
    
    // 配置选项：true = 国旗配色，false = 彩虹配色
    private static boolean useNationalFlagColors = true;
    
    /**
     * 为指定文本创建彩色文字效果
     * @param text 要显示的文本
     * @return 彩色文本组件
     */
    public static MutableComponent createColorText(String text) {
        if (useNationalFlagColors) {
            return createNationalFlagText(text);
        } else {
            return createRainbowText(text);
        }
    }
    
    /**
     * 创建国旗配色流动文字（多彩版本）
     * 包含红色、金色、深红、橙黄等多种变化
     */
    public static MutableComponent createNationalFlagText(String text) {
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
     * 创建彩虹流动文字（真正的流动效果）
     * 使用系统时间 + 字符序号计算 HSV 彩虹颜色
     */
    public static MutableComponent createRainbowText(String text) {
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
     * 创建静态三色文字（红-金-白循环）
     * @param text 文本内容
     * @return 彩色文本组件
     */
    public static MutableComponent createStaticTriColorText(String text) {
        MutableComponent result = Component.empty();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            // 简单的三色循环：0 = 红色，1 = 金色，2 = 白色
            int rgb;
            int mod = i % 3;
            if (mod == 0) {
                rgb = 0xFF0000; // 红
            } else if (mod == 1) {
                rgb = 0xFFD700; // 金（略偏黄色）
            } else {
                rgb = 0xFFFFFF; // 白
            }

            MutableComponent charComponent = Component.literal(String.valueOf(c))
                    .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(rgb)).withBold(true));

            result.append(charComponent);
        }

        return result;
    }
    
    /**
     * 创建双色渐变文字
     * @param text 文本内容
     * @param startColor 起始颜色 0xRRGGBB
     * @param endColor 结束颜色 0xRRGGBB
     * @return 渐变文本组件
     */
    public static MutableComponent createGradientText(String text, int startColor, int endColor) {
        MutableComponent result = Component.empty();
        int length = text.length();
        
        if (length == 0) return result;
        
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            float t = length > 1 ? (float) i / (length - 1) : 0;
            int rgb = interpolateColor(startColor, endColor, t);
            
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
    
    /**
     * 切换颜色方案的辅助方法
     * @param useFlagColors true=国旗配色，false=彩虹配色
     */
    public static void setColorScheme(boolean useFlagColors) {
        useNationalFlagColors = useFlagColors;
    }
    
    /**
     * 获取当前颜色方案
     * @return true=使用国旗配色，false=使用彩虹配色
     */
    public static boolean isUsingNationalFlagColors() {
        return useNationalFlagColors;
    }
}