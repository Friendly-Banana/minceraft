package me.banana.minceraft.forge;

import me.banana.minceraft.Minceraft;
import net.minecraftforge.fml.common.Mod;

@Mod(Minceraft.MOD_ID)
public class MinceraftForge {
    public MinceraftForge() {
        Minceraft.init();
    }
}