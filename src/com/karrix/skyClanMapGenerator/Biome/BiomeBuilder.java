package com.karrix.skyClanMapGenerator.Biome;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.karrix.skyClanMapGenerator.ChunkConfig;
import com.karrix.skyClanMapGenerator.CommonTools;
import com.karrix.skyClanMapGenerator.Group.GroundGroup;
import com.karrix.skyClanMapGenerator.Group.OreGroup;
import com.karrix.skyClanMapGenerator.Group.SaltGroup;

/*
 * What is salt ? Salt is tree, plant or any blocks we add to chunk
 * 
 * */

public class BiomeBuilder {
	public Biome			biome = Biome.FOREST;
	
	public Material 		groundSurface;
	public GroundGroup[] 	groundGroup;
	public Material			groundSupport;
	
	public Material 		groundSurfaceOre = Material.STONE;
	
	public SaltGroup[]		treeLayers;
	public SaltGroup[] 		saltLayers;
	public OreGroup[]		oreLayers;
	
	public boolean			isLakeSpawning = true;
	
	public void generate(World world, Chunk chunk, Random random) {
		int 	chunkX = chunk.getX();
		int 	chunkZ = chunk.getZ();
		
		generateBiomeLauncher(world, chunk, chunkX, chunkZ, random);
		generateGroundLauncher(world, chunk, chunkX, chunkZ, random);
		if (isLakeSpawning)
			generateLakeLauncher(world, chunk, chunkX, chunkZ, random);
		generateTreeLauncher(world, chunk, chunkX, chunkZ, random);
		generateSaltLauncher(world, chunk, chunkX, chunkZ, random);
		generateOreLauncher(world, chunk, chunkX, chunkZ, random);
	}
	
	// ----------------- LAUNCHER GENERATOR
	
	public void generateBiomeLauncher(World world, Chunk chunk, int chunkX, int chunkZ, Random random)
	{
		generateBiome(chunk);
	}
	
	public void generateGroundLauncher(World world, Chunk chunk, int chunkX, int chunkZ, Random random)
	{
		generateGround(world, chunk, chunkX, chunkZ);
	}
	
	public void generateLakeLauncher(World world, Chunk chunk, int chunkX, int chunkZ, Random random)
	{
		generateLake(world, chunk, chunkX, chunkZ, random);
	}
	
	public void generateTreeLauncher(World world, Chunk chunk, int chunkX, int chunkZ, Random random)
	{
		generateTree(world, chunk, chunkX, chunkZ, random);
	}
	
	public void generateSaltLauncher(World world, Chunk chunk, int chunkX, int chunkZ, Random random)
	{
		generateSalt(world, chunk, chunkX, chunkZ, random);
	}
	
	public void generateOreLauncher(World world, Chunk chunk, int chunkX, int chunkZ, Random random)
	{
		generateOre(world, chunk, chunkX, chunkZ, random);
	}
	
	
	// ----------------- REAL GENERATOR
	
	public void generateBiome(Chunk chunk)
	{
		for (int X = 0; X < 16; X++)
			for (int Z = 0; Z < 16; Z++) {
				final Block block = chunk.getBlock(X, 0, Z);
				block.setBiome(biome);
			}
	}
	
	public void generateGround(World world, Chunk chunk, int chunkX, int chunkZ) {
    	SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        generator.setScale(0.005D);
        int tempHeight = ChunkConfig.seaHeight;
        for (int X = 0; X < 16; X++) {
            for (int Z = 0; Z < 16; Z++) {
            	tempHeight = (int) (generator.noise(chunkX*16+X, chunkZ*16+Z, 0.5D, 0.5D)*15D + ChunkConfig.seaHeight);
                
    			chunk.getBlock(X, tempHeight, Z).setType(groundSurface);
    			tempHeight--;
            	
    			if (groundGroup != null)
    			{
                	for (int j = 0; j < groundGroup.length; ++j)
                		for (int i = 0; i < groundGroup[j].layerNbr; ++i)
                		{
                			chunk.getBlock(X, tempHeight, Z).setType(groundGroup[j].material);
                			--tempHeight;
                		}
    			}
            	                    
                double borderDistanceCenter = ChunkConfig.maxDistance - Math.hypot(X - 8, Z - 8) + CommonTools.randInt(0, 1);
                
                int limit = tempHeight - (int)borderDistanceCenter;
                
                for (int i = tempHeight; i > limit; i--)
                	chunk.getBlock(X, i, Z).setType(groundSupport);
            }
    	}
	}
	
	public void generateLake (World world, Chunk chunk, int chunkX, int chunkZ, Random random) {

//			if (random.nextInt(3) == 0) {  // The chance of spawning a lake
		if (true) {  // The chance of spawning a lake
			int X = chunkX * 16 + random.nextInt(15)-8;
			int Z = chunkZ * 16 + random.nextInt(15)-8;
			int Y;
			for (Y = world.getMaxHeight()-1; world.getBlockAt(X, Y, Z).getType() == Material.AIR; Y--);
			Y -= 7;
			Material laveOrWater = random.nextInt(100) < 90 ? Material.WATER : Material.LAVA;
			boolean[] aboolean = new boolean[2048];
			int i = random.nextInt(4)+4;
					
			int j, j1, k1;
					
			for (j = 0; j < i; ++j) {
	            double d0 = random.nextDouble() * 6.0D + 3.0D;
	            double d1 = random.nextDouble() * 4.0D + 2.0D;
	            double d2 = random.nextDouble() * 6.0D + 3.0D;
	            double d3 = random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
	            double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
	            double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

	            for (int k = 1; k < 15; ++k) {
	                for (int l = 1; l < 15; ++l) {
	                    for (int i1 = 1; i1 < 7; ++i1) {
	                        double d6 = ((double) k - d3) / (d0 / 2.0D);
	                        double d7 = ((double) i1 - d4) / (d1 / 2.0D);
	                        double d8 = ((double) l - d5) / (d2 / 2.0D);
	                        double d9 = d6 * d6 + d7 * d7 + d8 * d8;

	                        if (d9 < 1.0D) {
	                            aboolean[(k * 16 + l) * 8 + i1] = true;
	                        }
	                    }
	                }
	            }
	        }
				
			for (j = 0; j < 16; ++j) {
	            for (k1 = 0; k1 < 16; ++k1) {
	                for (j1 = 0; j1 < 8; ++j1) {
	                    if (aboolean[(j * 16 + k1) * 8 + j1]) {
	                    	if (world.getBlockAt(X + j + 1, Y + j1, Z + k1).getType() != Material.AIR &&
                    			world.getBlockAt(X + j - 1, Y + j1, Z + k1).getType() != Material.AIR &&
                    			world.getBlockAt(X + j, Y + j1, Z + k1 + 1).getType() != Material.AIR &&
                    			world.getBlockAt(X + j, Y + j1, Z + k1 - 1).getType() != Material.AIR &&
                    			world.getBlockAt(X + j, Y + j1 - 1, Z + k1).getType() != Material.AIR)
	                    	{
		                        world.getBlockAt(X + j, Y + j1, Z + k1).setType(j1>4 ? Material.AIR : laveOrWater);
	                    	}
	                    }
	                }
	            }
	        }
					
			for (j = 0; j < 16; ++j) {
	            for (k1 = 0; k1 < 16; ++k1) {
	                for (j1 = 4; j1 < 8; ++j1) {
	                    if (aboolean[(j * 16 + k1) * 8 + j1]) {
	                      	int X1 = X+j;
	                       	int Y1 = Y+j1-1;
	                       	int Z1 = Z+k1;
	                        if (world.getBlockAt(X1, Y1, Z1).getType() == Material.DIRT) {
	                        	if (world.getBlockAt(X1, Y1 + 1, Z1).getType() == Material.AIR)
	                        		world.getBlockAt(X1, Y1, Z1).setType(groundSurface);
	                        	else if (groundGroup.length > 0)
	                        		world.getBlockAt(X1, Y1, Z1).setType(groundGroup[0].material);
	                        }
	                    }
	                }
	            }
	        }
		}
	}
	
	public void generateTree(World world, Chunk chunk, int chunkX, int chunkZ, Random random) {
		if (treeLayers != null)
		{
			for (int i = 0; i < treeLayers.length; ++i)
				treeLayers[i].Generate(random, world, chunk);	
		}

	}
	
	public void generateSalt(World world, Chunk chunk, int chunkX, int chunkZ, Random random) {
		if (saltLayers != null)
		{
			for (int i = 0; i < saltLayers.length; ++i)
				saltLayers[i].Generate(random, world, chunk);	
		}
	}
	
	public void generateOre(World world, Chunk chunk, int chunkX, int chunkZ, Random random) {
		if (oreLayers != null)
		{
			for (int i = 0; i < oreLayers.length; ++i)
				oreLayers[i].Generate(random, world, chunk);	
		}
	}
}
