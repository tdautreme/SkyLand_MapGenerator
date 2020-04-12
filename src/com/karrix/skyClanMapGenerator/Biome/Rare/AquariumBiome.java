package com.karrix.skyClanMapGenerator.Biome.Rare;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

import com.karrix.skyClanMapGenerator.CommonTools;
import com.karrix.skyClanMapGenerator.Biome.BiomeBuilder;

public class AquariumBiome extends BiomeBuilder {
	
	private Material liquid;
	
	public AquariumBiome()
	{
		biome 			= Biome.FOREST;
		
		if (CommonTools.randProb() < 0.70D)
		{
			groundSurface 	= Material.CYAN_STAINED_GLASS;
			groundSupport 	= Material.CYAN_STAINED_GLASS;
			liquid 			= Material.WATER;
		}
		else
		{
			groundSurface 	= Material.RED_STAINED_GLASS;
			groundSupport 	= Material.RED_STAINED_GLASS;
			liquid 			= Material.LAVA;
		}
		
		isLakeSpawning = false;
	}
	
	@Override
	public void generateGroundLauncher(World world, Chunk chunk, int chunkX, int chunkZ, Random random)
	{
		generateGround(world, chunk, chunkX, chunkZ);
		generateLiquid(world, chunk);
	}
	
	public void generateLiquid(World world, Chunk chunk)
	{
		Block currBlock = chunk.getBlock(0, 0, 0);
		Location currLocation = currBlock.getLocation();
		int wX = currLocation.getBlockX();
		int wY = currLocation.getBlockY();
		int wZ = currLocation.getBlockZ();
        for (int X = 0; X < 16; X++) 
            for (int Y = 0; Y < world.getMaxHeight(); Y++) 
                for (int Z = 0; Z < 16; Z++)
                {
                	if (
            			world.getBlockAt(wX + X + 1, wY + Y,	 wZ + Z).getType() != Material.AIR &&
    					world.getBlockAt(wX + X - 1, wY + Y, 	 wZ + Z).getType() != Material.AIR &&
						world.getBlockAt(wX + X,	 wY + Y,	 wZ + Z + 1).getType() != Material.AIR &&
						world.getBlockAt(wX + X,	 wY + Y,	 wZ + Z - 1).getType() != Material.AIR &&
						world.getBlockAt(wX + X, 	 wY + Y - 1, wZ + Z).getType() != Material.AIR
        			) {
                		chunk.getBlock(X, Y, Z).setType(liquid);
                	}
                }
	}
}
