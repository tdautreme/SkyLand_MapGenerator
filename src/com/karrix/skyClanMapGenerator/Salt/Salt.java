package com.karrix.skyClanMapGenerator.Salt;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;

public class Salt {
	protected Material 		saltBlock;
	public Material 		surfaceBlock;
	protected int 			height;
	
	public Salt(Material i_saltBlock, Material i_surfaceBlock, Integer... argsArr)
	{
		saltBlock 		= i_saltBlock;
		surfaceBlock 	= i_surfaceBlock;
		if (argsArr.length > 0)
			height = argsArr[0];
		else
			height = 1;
	}
	
	public void GenerateSalt(Random random, World world, Chunk chunk, int X, int Y, int Z)
	{
		if (chunk.getBlock(X, Y, Z).getType() != surfaceBlock) return;
		++Y;
		for (int i = 0; i < height; ++i)
			chunk.getBlock(X, Y + i, Z).setType(saltBlock);
	}
}
