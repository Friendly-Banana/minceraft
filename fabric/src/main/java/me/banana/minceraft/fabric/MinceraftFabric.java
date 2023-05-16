package me.banana.minceraft.fabric;

import me.banana.minceraft.Minceraft;
import net.fabricmc.api.ModInitializer;

public class MinceraftFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Minceraft.init();
    }
}