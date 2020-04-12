package com.karrix.skyClanMapGenerator;

import com.karrix.skyClanMapGenerator.Biome.BiomeBuilder;
import com.karrix.skyClanMapGenerator.Biome.Common.DesertBiome;
import com.karrix.skyClanMapGenerator.Biome.Common.DesertOasisBiome;
import com.karrix.skyClanMapGenerator.Biome.Common.DesertRedBiome;
import com.karrix.skyClanMapGenerator.Biome.Common.DesertRedOasisBiome;
import com.karrix.skyClanMapGenerator.Biome.Common.ForestBiome;
import com.karrix.skyClanMapGenerator.Biome.Rare.AquariumBiome;

public class ChunkConfig {
    public static int 				seaHeight 			= 63;
    public static int 				islandDistance 		= 8;
    public static int 				territoryDistance	= 3 * islandDistance;
    public static double 			maxDistance 		= Math.hypot(0 - 8, 0 - 8);
    
    public static BiomeBuilder[] 	commonBiomes		= {
												    		new ForestBiome(),
												    		new DesertBiome()
												    	};
    public static BiomeBuilder[]	rareBiomes			= {
    														new DesertRedBiome(),
    														new DesertOasisBiome(),
    														new DesertRedOasisBiome(),
    														new AquariumBiome()
    													};
    
    public static boolean isTerritoryChunk(int chunkX, int chunkZ)
    {
    	return chunkX % territoryDistance == 0 && chunkZ % territoryDistance == 0;
    }
    
    public static boolean elseIfIsResourceChunk(int chunkX, int chunkZ)
    {
    	return chunkX % islandDistance == 0 && chunkZ % islandDistance == 0;
    }
    
    public static boolean isResourceChunk(int chunkX, int chunkZ)
    {
    	return !isTerritoryChunk(chunkX, chunkZ) && elseIfIsResourceChunk(chunkX, chunkZ);
    }
    
}
