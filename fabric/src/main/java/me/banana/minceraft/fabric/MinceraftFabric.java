package me.banana.minceraft.fabric;

import me.banana.minceraft.Minceraft;
import net.fabricmc.api.ModInitializer;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

import static me.banana.minceraft.Minceraft.GENERAL_SPEC;
import static me.banana.minceraft.Minceraft.MOD_ID;

public class MinceraftFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Minceraft.init();
        ModLoadingContext.registerConfig(MOD_ID, ModConfig.Type.CLIENT, GENERAL_SPEC);
    }
}