package me.banana.minceraft;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class Minceraft {
    public static final String MOD_ID = "minceraft";

    public static ModConfig config;

    public static void init() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    public static float[] HsvToRgb(float hue, float saturation, float brightness) {
        if (saturation == 0.0F) {
            return new float[]{brightness, brightness, brightness};
        } else {
            float h = (hue - (float) Math.floor(hue)) * 6.0F;
            float f = h - (float) Math.floor(h);
            float p = brightness * (1.0F - saturation);
            float q = brightness * (1.0F - saturation * f);
            float t = brightness * (1.0F - saturation * (1.0F - f));
            return switch ((int) h) {
                case 0 -> new float[]{brightness, t, p};
                case 1 -> new float[]{q, brightness, p};
                case 2 -> new float[]{p, brightness, t};
                case 3 -> new float[]{p, q, brightness};
                case 4 -> new float[]{t, p, brightness};
                case 5 -> new float[]{brightness, p, q};
                default -> new float[]{0, 0, 0};
            };
        }
    }
}