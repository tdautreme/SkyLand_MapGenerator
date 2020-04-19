package com.karrix.skyClanMapGenerator;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import com.karrix.skyClanMapGenerator.Biome.IslandBuilder;

public class ChunkPopulator extends BlockPopulator {	

	@Override
	public void populate(World world, Random random, Chunk chunk) {
		int chunkX = chunk.getX();
		int chunkZ = chunk.getZ();
		if (ChunkConfig.isTerritoryChunk(chunkX, chunkZ)) {
//	    	_plugin.getLogger().info("CHUNK_GENERATION_21");
			set3DPlatform(chunk, ChunkConfig.seaHeight, 0, 0);
			set3DPlatform(chunk, ChunkConfig.seaHeight, 3, 0);
			set3DPlatform(chunk, ChunkConfig.seaHeight, 0, 3);
		}
		else if (ChunkConfig.elseIfIsResourceChunk(chunkX, chunkZ)) {
//	    	_plugin.getLogger().info("CHUNK_GENERATION_22");
			double prob = CommonTools.randProb();
//			if (prob < 0.90D) { // 90 % Chance to spawn a Biome
			if (true) { 
				prob = CommonTools.randProb();
//				_plugin.getLogger().info("COMMON PROB = " + prob);
				IslandBuilder currentBiome;
//				if (prob < 0.70D) { // 70 % Chance to spawn common Biome
				if (true) { 
					currentBiome = CommonTools.randChoice(ChunkConfig.commonBiomes);
				} else { // 30 % Chance to spawn rare Biome
					currentBiome = CommonTools.randChoice(ChunkConfig.rareBiomes);
				}
//		    	_plugin.getLogger().info("CHUNK_GENERATION_3");
				currentBiome.generate(world, chunk, random);
			} else { // 10% Chance to spawn a schema
				
			}
		}
//		else {
////	    	_plugin.getLogger().info("CHUNK_GENERATION_23");
//			for (int X = 0; X < 16; X++)
//				for (int Z = 0; Z < 16; Z++) {
//					final Block block = chunk.getBlock(X, 0, Z);
//					block.setBiome(Biome.OCEAN);
//				}
//		  	}
	}
	
  public void set3DPlatform(Chunk chunk, int height, int padX, int padZ) {
	set2DPlatform(chunk, height, padX, padZ, Material.DIRT);
	set2DPlatform(chunk, height + 1, padX, padZ, Material.DIRT);
	set2DPlatform(chunk, height + 2, padX, padZ, Material.GRASS_BLOCK);
	}

	public void set2DPlatform(Chunk chunk, int height, int padX, int padZ, Material mat) {
		for (int x = 0; x < 3; x++)
			for (int z = 0; z < 3; z++)
				chunk.getBlock(padX + x, height, padZ + z).setType(mat);
	}
}