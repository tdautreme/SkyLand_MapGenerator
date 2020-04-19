package com.karrix.skyClanMapGenerator.WorldGenerator;

import org.bukkit.Material;
import org.bukkit.World;

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

	public Material constructMaterial;
	
	public WorldGeneratorDim generate(World world, int chunkX, int chunkZ, Material i_constructMaterial)
	{
		constructMaterial = i_constructMaterial;
		generateWorld(world, chunkX, chunkZ);
		return new WorldGeneratorDim(sizeX, sizeY, sizeZ, worldCenterX, worldCenterY, worldCenterZ, worldStartX, worldStartY, worldStartZ);
	}
	
	public void generateWorld(World world, int chunkX, int chunkZ)
	{
		return ;
	}
	
}
