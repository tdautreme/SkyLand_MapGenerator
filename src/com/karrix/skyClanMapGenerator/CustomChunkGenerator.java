package com.karrix.skyClanMapGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class CustomChunkGenerator extends ChunkGenerator {    

    
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
    	ChunkData chunk_data = createChunkData(world);
    	return chunk_data;
    }
    
    
    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList((BlockPopulator)new ChunkPopulator());
    }
}