package com.karrix.skyClanMapGenerator.Salt;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class SaltCactus extends Salt {

	public SaltCactus(Integer... argsArr) {
		super(Material.CACTUS, Material.SAND, argsArr);
		if (argsArr.length == 0)
			height = 3;
	}
	
	@Override
	public void GenerateSalt(Random random, World world, Chunk chunk, int X, int Y, int Z)
	{
		Material currSuraceBlock = chunk.getBlock(X, Y, Z).getType();
		if (currSuraceBlock != Material.SAND && currSuraceBlock != Material.RED_SAND) return;
		++Y;
		Block currBlock = chunk.getBlock(X, Y, Z);
		Location currLocation = currBlock.getLocation();
		int wX = currLocation.getBlockX();
		int wY = currLocation.getBlockY();
		int wZ = currLocation.getBlockZ();
		for (int i = 0; i < height; ++i)
		{
			if (
				world.getBlockAt(wX + 1, wY + i, wZ).getType() != Material.AIR ||
				world.getBlockAt(wX - 1, wY + i, wZ).getType() != Material.AIR ||
				world.getBlockAt(wX, wY + i, wZ + 1).getType() != Material.AIR ||
				world.getBlockAt(wX, wY + i, wZ - 1).getType() != Material.AIR
					) return;
			chunk.getBlock(X, Y + i, Z).setType(saltBlock);
		}
	}
}
