package com.karrix.skyClanMapGenerator.WorldGenerator;

import org.bukkit.World;

import com.karrix.skyClanMapGenerator.WorldGeneratorDim;

public class WorldGenerator {	
	public int sizeX;
	public int sizeY;
	public int sizeZ;
	
	public int worldCenterX;
	public int worldCenterY;
	public int worldCenterZ;
	
	public int worldStartX;
	public int worldStartY;
	public int worldStartZ;

	public WorldGeneratorDim generate(World world, int chunkX, int chunkZ)
	{
		generateWorld(world, chunkX, chunkZ);
		return new WorldGeneratorDim(sizeX, sizeY, sizeZ, worldCenterX, worldCenterY, worldCenterZ, worldStartX, worldStartY, worldStartZ);
	}
	
	public void generateWorld(World world, int chunkX, int chunkZ)
	{
		return ;
	}
	
}
