package me.banana.minceraft;

import net.minecraftforge.common.ForgeConfigSpec;


public class Minceraft {
    public static final String MOD_ID = "minceraft";

    public static ForgeConfigSpec GENERAL_SPEC;
    public static final ForgeConfigSpec.DoubleValue minceraftChance;
    public static final ForgeConfigSpec.ConfigValue<Double> rgbSpeed;

    public static void init() {
    }

    static {
        var builder = new ForgeConfigSpec.Builder();
        builder.comment("Chance the title screen spells Minceraft");
        minceraftChance = builder.defineInRange("chance", 1.0E-4, 0, 1);
        builder.comment("Speed of the title changing color. Use 0 to disable");
        rgbSpeed = builder.define("rgbSpeed", 0.001);
        GENERAL_SPEC = builder.build();
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