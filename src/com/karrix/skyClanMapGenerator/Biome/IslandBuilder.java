package com.karrix.skyClanMapGenerator.Biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.karrix.skyClanMapGenerator.ChunkConfig;
import com.karrix.skyClanMapGenerator.CommonTools;
import com.karrix.skyClanMapGenerator.ConsoleLog;
import com.karrix.skyClanMapGenerator.Group.BlockGroup;
//import com.karrix.skyClanMapGenerator.Group.OreGroup;
import com.karrix.skyClanMapGenerator.Group.SaltGroup;
import com.karrix.skyClanMapGenerator.WorldGenerator.WorldGenerator;
import com.karrix.skyClanMapGenerator.WorldGenerator.WorldGeneratorDim;
import com.karrix.skyClanMapGenerator.WorldGenerator.WorldGeneratorGetter;
import com.karrix.skyClanMapGenerator.WorldGenerator.WorldGeneratorSimpleIsland;

/*
 * What is salt ? Salt is tree, plant, ores or any blocks we add to chunk
 * 
 * */

public class IslandBuilder {
	public String 			worldGenerator = "SimpleIsland";
	public Material			worldConstructMaterial = Material.STONE;
	public Biome			biome = Biome.FOREST;
	public boolean			isLakeSpawning = true;
	public int				sizeMin = 35;
	public int				sizeMax = 50;
	public int				seaHeight = ChunkConfig.seaHeight;
	public int				seaRange = 16;
	
	public BlockGroup[] 	blockGroup;
	
	public SaltGroup[] 		saltSurfaceGroup;
	public SaltGroup[]		saltOreGroup;

	
	
	// ========================= CONSTANT SEPARARTION
	
	public void generate(World world, Chunk chunk, Random random) {
		int 	chunkX = chunk.getX();
		int 	chunkZ = chunk.getZ();
		
		// First we need to generate ground to get his dim
		WorldGeneratorDim wGD = WorldGeneratorGetter.Get(worldGenerator).generate(world, chunkX, chunkZ, worldConstructMaterial);
		
		// And next we populate
		populate(world, wGD);
	}
	
	public void populate(World world, WorldGeneratorDim wGD)
	{
		generateBiomeLauncher(world, wGD);
		generateBlockLauncher(world, wGD);
		if (isLakeSpawning)
			generateLakeLauncher(world, wGD);
		
		// We get dict of all surface block and ground block for put Salt optimized
		List<BlockKey> surfaceBlockDict = getSurfaceBlockDict(world, wGD);
		List<BlockKey> groundBlockDict	= getGroundBlockDict(world, wGD);
		
		// We set base length for Salt probability
		for (BlockKey sbk : surfaceBlockDict) 	sbk.SetBaseLength();
		for (BlockKey gbk : groundBlockDict) 	gbk.SetBaseLength();
		
		generateSaltSurfaceLauncher(world, surfaceBlockDict);
		generateSaltOreLauncher(world, groundBlockDict);
	}
	
	// ========================== LAUNCHER
	
	public void generateBiomeLauncher(World world, WorldGeneratorDim wGD)
	{
		generateBiome(world, wGD);
	}
	
	public void generateBlockLauncher(World world, WorldGeneratorDim wGD)
	{
		generateBlock(world, wGD);
	}
	
	public void generateLakeLauncher(World world, WorldGeneratorDim wGD)
	{
		generateLake(world, wGD);
	}
	
	public void generateSaltSurfaceLauncher(World world, List<BlockKey> surfaceBlockDict)
	{
		generateSalt(world, saltSurfaceGroup, surfaceBlockDict);
	}
	
	public void generateSaltOreLauncher(World world, List<BlockKey> groundBlockDict)
	{
		generateSalt(world, saltOreGroup, groundBlockDict);
	}
	
	// ========================== EXECUTION

	public void generateBiome(World world, WorldGeneratorDim wGD)
	{
		for (int X = wGD.worldStartX; X < wGD.worldEndX; ++X)
			for (int Z = wGD.worldStartZ; Z < wGD.worldEndZ; ++Z)
				world.getBlockAt(X, 0, Z).setBiome(biome);
	}
	
	public void generateBlock(World world, WorldGeneratorDim wGD)
	{
		for (int X = 0; X < wGD.sizeX; ++X)
			for (int Z = 0; Z < wGD.sizeZ; ++Z)
			{
				// Start at top of Y bound
				int Y = wGD.worldStartY + wGD.sizeY - 1;

				// Go to bottom while AIR
				for (; Y >= wGD.worldStartY && world.getBlockAt(wGD.worldStartX + X, Y, wGD.worldStartZ + Z).getType() == Material.AIR; --Y);
				
				// Start put block
				generateBlockRecursive(wGD.worldStartX + X, Y, wGD.worldStartZ + Z, world, blockGroup);
			}
	}
	
	public int generateBlockRecursive(int X, int Y, int Z, World world, BlockGroup[] blockGroup)
	{
		for (BlockGroup layerGroup : blockGroup)
			for (int i = 0; layerGroup.layerNbr == -1 || i < layerGroup.layerNbr; ++i)
				if (layerGroup.isMaterial)
					for (Material currentMaterial : layerGroup.material)
					{
						Block currentBlock = world.getBlockAt(X, Y, Z);
						if (currentBlock.getType() != Material.AIR)
							currentBlock.setType(currentMaterial);
						else
							return -1;
						--Y;
					}
				else
				{
					Y = generateBlockRecursive(X, Y, Z, world, layerGroup.blockGroup);
					if (Y == -1) return Y;
				}
		return Y;
	}
	
	public List<BlockKey> getSurfaceBlockDict(World world, WorldGeneratorDim wGD)
	{
		List<BlockKey> dict = new ArrayList<>();
		for (int X = wGD.worldStartX; X < wGD.worldEndX; ++X)
			for (int Z = wGD.worldStartZ; Z < wGD.worldEndZ; ++Z)
			{
				int Y = wGD.worldEndY - 1;
				for (; Y >= wGD.worldStartY && world.getBlockAt(X, Y, Z).getType() == Material.AIR; --Y);
				if (Y >= wGD.worldStartY && world.getBlockAt(X, Y + 1, Z).getType() == Material.AIR)
				{
					Block currentBlock = world.getBlockAt(X, Y, Z);
					if (currentBlock.getType() != Material.AIR)
						addBlockToDict(dict, currentBlock);
				}
			}
		return dict;
	}
	
	public List<BlockKey> getGroundBlockDict(World world, WorldGeneratorDim wGD)
	{
		List<BlockKey> dict = new ArrayList<>();
		Block currentBlock = null;
		Block topBlock = null;
		for (int X = wGD.worldStartX; X < wGD.worldEndX; ++X)
			for (int Z = wGD.worldStartZ; Z < wGD.worldEndZ; ++Z)
			{
				for (int Y = wGD.worldStartY; Y < wGD.worldEndY; ++Y)
				{
					if (currentBlock == null)
						currentBlock = world.getBlockAt(X, Y, Z);
					topBlock = world.getBlockAt(X, Y + 1, Z);
					if (currentBlock.getType() != Material.AIR && topBlock.getType() != Material.AIR)
						addBlockToDict(dict, currentBlock);
					currentBlock = topBlock;
				}
				currentBlock = null;
			}
		return dict;
	}
	
	public void addBlockToDict(List<BlockKey> dict, Block block)
	{
		Material targetMaterial = block.getType();
		for (BlockKey key : dict)
			if (key.material == targetMaterial)
			{
				key.list.add(block);
				return ;
			}
		dict.add(new BlockKey(block));
	}
	
	public void generateSalt(World world, SaltGroup[] currentGroup, List<BlockKey> currentBlockDict)
	{
		if (currentGroup == null) return ;
		for (int i = 0; i < currentGroup.length; ++i)
			currentGroup[i].Generate(world, currentBlockDict);	
	}
	
	public void generateLake (World world, WorldGeneratorDim wGD) {
		Random random = new Random();
//			if (random.nextInt(3) == 0) {  // The chance of spawning a lake
		if (true) {  // The chance of spawning a lake
			int X = wGD.worldStartX + random.nextInt(wGD.sizeX - 1) - wGD.sizeX / 2;
			int Z = wGD.worldStartZ + random.nextInt(wGD.sizeZ - 1) - wGD.sizeZ / 2;
			int Y;
			for (Y = wGD.worldEndY - 1; world.getBlockAt(X, Y, Z).getType() == Material.AIR && Y >= wGD.worldStartY; --Y);
			if (! (Y >= wGD.worldStartY)) return ;
//			for (Y = world.getMaxHeight()-1; world.getBlockAt(X, Y, Z).getType() == Material.AIR; Y--);
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
					
//			for (j = 0; j < 16; ++j) {
//	            for (k1 = 0; k1 < 16; ++k1) {
//	                for (j1 = 4; j1 < 8; ++j1) {
//	                    if (aboolean[(j * 16 + k1) * 8 + j1]) {
//	                      	int X1 = X+j;
//	                       	int Y1 = Y+j1-1;
//	                       	int Z1 = Z+k1;
//	                        if (world.getBlockAt(X1, Y1, Z1).getType() == Material.DIRT) {
//	                        	if (world.getBlockAt(X1, Y1 + 1, Z1).getType() == Material.AIR)
//	                        		world.getBlockAt(X1, Y1, Z1).setType(blockGroup[0]);
//	                        	else if (groundGroup.length > 0)
//	                        		world.getBlockAt(X1, Y1, Z1).setType(groundGroup[0].material);
//	                        }
//	                    }
//	                }
//	            }
//	        }
		}
	}
	
	// ----------------- LAUNCHER GENERATOR
	
//	public void generateWorldLauncher(World world, int chunkX, int chunkY)
//	{
//		worldGenerator.generate(world, chunkX, chunkY);
//	}
	
//	public void generateBlockLauncher(World world, int chunkX, int chunkY)
//	{
//	/	generateBlock(world);
//	}
	
//	public void generateBiomeLauncher(World world, Chunk chunk, int chunkX, int chunkZ, Random random)
//	{
//		generateBiome(chunk);
//	}
	
//	public void generateGroundLauncher(World world, Chunk chunk, int chunkX, int chunkZ, Random random)
//	{
//		generateGround(world, chunk, chunkX, chunkZ);
//	}
	
//	public void generateLakeLauncher(World world, Chunk chunk, int chunkX, int chunkZ, Random random)
//	{
//		generateLake(world, chunk, chunkX, chunkZ, random);
//	}
//	
//	public void generateTreeLauncher(World world, Chunk chunk, int chunkX, int chunkZ, Random random)
//	{
//		generateTree(world, chunk, chunkX, chunkZ, random);
//	}
//	
//	public void generateSaltLauncher(World world, Chunk chunk, int chunkX, int chunkZ, Random random)
//	{
//		generateSalt(world, chunk, chunkX, chunkZ, random);
//	}
//	
//	public void generateOreLauncher(World world, Chunk chunk, int chunkX, int chunkZ, Random random)
//	{
//		generateOre(world, chunk, chunkX, chunkZ, random);
//	}
	
	
	// ----------------- REAL GENERATOR
	
//	public void generateBiome(Chunk chunk)
//	{
//		for (int X = 0; X < 16; X++)
//			for (int Z = 0; Z < 16; Z++) {
//				final Block block = chunk.getBlock(X, 0, Z);
//				block.setBiome(biome);
//			}
//	}
	
//	public void generateGround(World world, Chunk chunk, int chunkX, int chunkZ) {
//    	SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
////    	SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 100);
////        generator.setScale(1D);
////        generator.setScale(CommonTools.randDouble(0.005D, 0.01D));
//        if (CommonTools.randProb() < 0.95)
//            generator.setScale(0.005D);
//        else
//            generator.setScale(1D);
//
//        int tempHeight = ChunkConfig.seaHeight;
//        for (int X = 0; X < 16; X++) {
//            for (int Z = 0; Z < 16; Z++) {
//            	tempHeight = (int) (generator.noise(chunkX*16+X, chunkZ*16+Z, 0.5D, 0.5D)*15D + ChunkConfig.seaHeight);
//                
//            	if (groundSurface != null)
//            	{
//        			chunk.getBlock(X, tempHeight, Z).setType(groundSurface);
//        			tempHeight--;
//            	}
//            	
//    			if (groundGroup != null)
//    			{
//                	for (int j = 0; j < groundGroup.length; ++j)
//                		for (int i = 0; i < groundGroup[j].layerNbr; ++i)
//                		{
//                			chunk.getBlock(X, tempHeight, Z).setType(groundGroup[j].material);
//                			--tempHeight;
//                		}
//    			}
//            	                    
//    			if (groundSupport != null)
//    			{
//    				double borderDistanceCenter = ChunkConfig.maxDistance - Math.hypot(X - 8, Z - 8) + CommonTools.randInt(0, 1);
//	                
//    				int limit = tempHeight - (int)borderDistanceCenter;
//	                
//    				for (int i = tempHeight; i > limit; i--)
//    					chunk.getBlock(X, i, Z).setType(groundSupport);
//    			}
//
//            }
//    	}
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
