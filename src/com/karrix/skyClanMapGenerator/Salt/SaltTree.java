package com.karrix.skyClanMapGenerator.Salt;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;

public class SaltTree extends Salt{
	TreeType tree;
	
	public SaltTree(TreeType i_tree, Material i_surfaceBlock, Integer... argsArr) {
		super(Material.AIR, i_surfaceBlock, argsArr);
		tree = i_tree;
	}
	
	@Override
	public void GenerateSalt(Random random, World world, Chunk chunk, int X, int Y, int Z) 
	{
		if (chunk.getBlock(X, Y, Z).getType() != surfaceBlock) return;
		chunk.getBlock(X, Y, Z).setType(Material.GRASS_BLOCK);
    	world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), tree); // The tree type can be changed if you want.
		chunk.getBlock(X, Y, Z).setType(surfaceBlock);
	}
}
