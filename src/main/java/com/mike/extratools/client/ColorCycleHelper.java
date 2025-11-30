package com.mike.extratools.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.FastColor;

/**
 * 颜色循环辅助类
 * 用于生成彩色流动效果
 */
public class ColorCycleHelper {
    
    /**
     * 获取基于时间的彩虹色
     * @param offset 偏移量，用于让不同字符有不同颜色
     * @param speed 速度倍数，越大变化越快
     * @return RGB颜色值
     */
    public static int getRainbowColor(float offset, float speed) {
        long time = System.currentTimeMillis();
        float hue = (time * speed / 1000.0f + offset) % 360.0f;
        return hsvToRgb(hue, 1.0f, 1.0f);
    }
    
    /**
     * 获取红金交替色（适合爱国主题）
     * @param offset 偏移量
     * @param speed 速度倍数
     * @return RGB颜色值
     */
    public static int getRedGoldColor(float offset, float speed) {
        long time = System.currentTimeMillis();
        float wave = (float) Math.sin((time * speed / 1000.0f + offset) * Math.PI);
        
        // 在红色和金色之间插值
        int red = 255;
        int green = (int) (50 + (215 * ((wave + 1) / 2))); // 50-215
        int blue = 0;
        
        return FastColor.ARGB32.color(255, red, green, blue);
    }
    
    /**
     * 获取渐变彩虹色（更平滑的过渡）
     * @param offset 偏移量
     * @param speed 速度倍数
     * @return RGB颜色值
     */
    public static int getSmoothRainbowColor(float offset, float speed) {
        long time = System.currentTimeMillis();
        float phase = (time * speed / 2000.0f + offset) % 1.0f;
        
        // 使用正弦波创建平滑过渡
        float r = (float) (Math.sin(phase * Math.PI * 2) * 0.5 + 0.5);
        float g = (float) (Math.sin((phase + 0.33f) * Math.PI * 2) * 0.5 + 0.5);
        float b = (float) (Math.sin((phase + 0.66f) * Math.PI * 2) * 0.5 + 0.5);
        
        return FastColor.ARGB32.color(255, (int)(r * 255), (int)(g * 255), (int)(b * 255));
    }
    
    /**
     * HSV 转 RGB
     */
    private static int hsvToRgb(float hue, float saturation, float value) {
        int h = (int) (hue / 60);
        float f = hue / 60 - h;
        float p = value * (1 - saturation);
        float q = value * (1 - f * saturation);
        float t = value * (1 - (1 - f) * saturation);
        
        float r, g, b;
        switch (h) {
            case 0: r = value; g = t; b = p; break;
            case 1: r = q; g = value; b = p; break;
            case 2: r = p; g = value; b = t; break;
            case 3: r = p; g = q; b = value; break;
            case 4: r = t; g = p; b = value; break;
            default: r = value; g = p; b = q; break;
        }
        
        return FastColor.ARGB32.color(255, (int)(r * 255), (int)(g * 255), (int)(b * 255));
    }
    
    /**
     * 获取国旗色系（红黄交替）
     * @param offset 偏移量
     * @param speed 速度倍数
     * @return RGB颜色值
     */
    public static int getChinaFlagColor(float offset, float speed) {
        long time = System.currentTimeMillis();
        float wave = (float) Math.sin((time * speed / 1000.0f + offset) * Math.PI);
        
        if (wave > 0) {
            // 红色 (222, 41, 16)
            return FastColor.ARGB32.color(255, 222, 41, 16);
        } else {
            // 金黄色 (255, 222, 0)
            return FastColor.ARGB32.color(255, 255, 222, 0);
        }
    }
}
