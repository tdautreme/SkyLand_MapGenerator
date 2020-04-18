package com.karrix.skyClanMapGenerator.Biome;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.karrix.skyClanMapGenerator.ChunkConfig;
import com.karrix.skyClanMapGenerator.CommonTools;
import com.karrix.skyClanMapGenerator.WorldGeneratorDim;
import com.karrix.skyClanMapGenerator.WorldGenerator.WorldGenerator;
import com.karrix.skyClanMapGenerator.WorldGenerator.WorldGeneratorGetter;

public class IslandGenerator_OLD {
	private 	IslandBuilder 		isb;
	private 	WorldGeneratorDim 	wGD;
	private 	World 				world;
	private 	int 				chunkX;
	private 	int 				chunkZ;
	
	public IslandGenerator_OLD(IslandBuilder i_isb, World i_world, int i_chunkX, int i_chunkZ)
	{
		isb = i_isb;
		world = i_world;
		chunkX = i_chunkX;
		chunkZ = i_chunkZ;
		
	}
//	
//	public void generate()
//	{
//		// Get island shape generator
//		wGD = WorldGeneratorGetter.Get(isb.worldGenerator).generate(world, chunkX, chunkZ);
//		// Generate blocks
//		
//		if (isb.isLakeSpawning)
//			generateLakeLauncher();
//		generateTreeLauncher();
//		generateSaltLauncher();
//		generateOreLauncher();
//		
//	}
//	
//	public void generateBiomeLauncher()
//	{
//		generateBiome();
//	}
//	
//	public void generateLakeLauncher()
//	{
//		generateLake();
//	}
//	
//	public void generateTreeLauncher()
//	{
//		generateTree();
//	}
//	
//	public void generateSaltLauncher()
//	{
//		generateSalt();
//	}
//	
//	public void generateOreLauncher()
//	{
//		generateOre();
//	}
//	
//	// ==========================
//	
//	public void generateBiome()
//	{
//		for (int X = 0; X < wGD.sizeX; X++)
//			for (int Z = 0; Z < wGD.sizeZ; Z++) {
//				final Block block = world.getBlockAt(wGD.worldStartX + X, 0, wGD.worldCenterZ + Z);
//				block.setBiome(isb.biome);
//			}
//	}
//	
//	public void generateTree(World world, Chunk chunk, int chunkX, int chunkZ, Random random) {
//		if (treeLayers != null)
//		{
//			for (int i = 0; i < treeLayers.length; ++i)
//				treeLayers[i].Generate(random, world, chunk);	
//		}
//
//	}
//	
//	public void generateSalt(World world, Chunk chunk, int chunkX, int chunkZ, Random random) {
//		if (saltLayers != null)
//		{
//			for (int i = 0; i < saltLayers.length; ++i)
//				saltLayers[i].Generate(random, world, chunk);	
//		}
//	}
//	
//	public void generateOre(World world, Chunk chunk, int chunkX, int chunkZ, Random random) {
//		if (oreLayers != null)
//		{
//			for (int i = 0; i < oreLayers.length; ++i)
//				oreLayers[i].Generate(random, world, chunk);	
//		}
//	}
}
