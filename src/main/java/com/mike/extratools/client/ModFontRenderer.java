package com.mike.extratools.client;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

public class ModFontRenderer {

    public static Component applyFont(ModFontStyle style, Component baseComponent) {
        if (style == null || style == ModFontStyle.NONE || baseComponent == null) {
            return baseComponent;
        }

        String text = baseComponent.getString();
        if (text.isEmpty()) {
            return baseComponent;
        }

        return switch (style) {
            case RAINBOW -> createRainbowText(text);
            case NATIONAL_FLAG -> createNationalFlagText(text);
            case NONE -> baseComponent;
        };
    }

    private static MutableComponent createRainbowText(String text) {
        MutableComponent result = Component.empty();
        int length = text.length();
        if (length == 0) {
            return result;
        }

        long millis = System.currentTimeMillis();
        long periodMs = 4000L;
        float base = (millis % periodMs) / (float) periodMs;

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            float offset = (float) i / (float) (length - 1);
            float hue = (base + offset) % 1.0f;
            int rgb = hsvToRgb01(hue, 1.0f, 1.0f);

            MutableComponent charComponent = Component.literal(String.valueOf(c))
                    .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(rgb)).withBold(true));

            result.append(charComponent);
        }

        return result;
    }

    private static MutableComponent createNationalFlagText(String text) {
        MutableComponent result = Component.empty();
        int length = text.length();
        if (length == 0) {
            return result;
        }

        long millis = System.currentTimeMillis();
        long periodMs = 3500L;
        float base = (millis % periodMs) / (float) periodMs;

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            float offset = (float) i / (float) (length - 1);
            float position = (base + offset) % 1.0f;
            float segment = position * 4.0f;

            int rgb;
            if (segment < 1.0f) {
                rgb = interpolateColor(0xC70000, 0xDE2910, segment);
            } else if (segment < 2.0f) {
                rgb = interpolateColor(0xDE2910, 0xFFDE00, segment - 1.0f);
            } else if (segment < 3.0f) {
                rgb = interpolateColor(0xFFDE00, 0xFFA500, segment - 2.0f);
            } else {
                rgb = interpolateColor(0xFFA500, 0xC70000, segment - 3.0f);
            }

            MutableComponent charComponent = Component.literal(String.valueOf(c))
                    .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(rgb)).withBold(true));

            result.append(charComponent);
        }

        return result;
    }

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
            case 0 -> {
                r = v;
                g = t;
                b = p;
            }
            case 1 -> {
                r = q;
                g = v;
                b = p;
            }
            case 2 -> {
                r = p;
                g = v;
                b = t;
            }
            case 3 -> {
                r = p;
                g = q;
                b = v;
            }
            case 4 -> {
                r = t;
                g = p;
                b = v;
            }
            case 5 -> {
                r = v;
                g = p;
                b = q;
            }
            default -> {
                r = v;
                g = p;
                b = q;
            }
        }

        int ri = (int) (r * 255.0f);
        int gi = (int) (g * 255.0f);
        int bi = (int) (b * 255.0f);
        return (ri << 16) | (gi << 8) | bi;
    }
}
