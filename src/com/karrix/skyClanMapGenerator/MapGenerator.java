package com.karrix.skyClanMapGenerator;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public final class MapGenerator extends JavaPlugin {
    @Override
    public void onEnable() {
        // TODO Insert logic to be performed when the plugin is enabled
    	ConsoleLog.SetLogger(this);
    	getLogger().info("MAP GENERATOR V0.0 LANCHED");
    }
    
    @Override
    public void onDisable() {
        // TODO Insert logic to be performed when the plugin is disabled
    }
    
    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new CustomChunkGenerator();
    }
}