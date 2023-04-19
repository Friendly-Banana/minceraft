package me.banana.minceraft.forge;

import me.banana.minceraft.Minceraft;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import static me.banana.minceraft.Minceraft.GENERAL_SPEC;

@Mod(Minceraft.MOD_ID)
public class MinceraftForge {
    public MinceraftForge() {
        Minceraft.init();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, GENERAL_SPEC);
    }
}