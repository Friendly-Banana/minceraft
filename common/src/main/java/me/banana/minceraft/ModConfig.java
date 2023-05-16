package me.banana.minceraft;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "minceraft")
public class ModConfig implements ConfigData {
    @ConfigEntry.BoundedDiscrete(min = 0, max = 1)
    public double minceraftChance = 1.0E-4;
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 1)
    public double rgbSpeed = 0.005;
}