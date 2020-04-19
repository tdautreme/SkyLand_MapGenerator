package com.karrix.skyClanMapGenerator.Salt;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;

public class SaltSurface extends Salt {

	public SaltSurface(Material i_saltBlock) {
		super(i_saltBlock);
	}
	
	public SaltSurface(Material i_saltBlock, int i_height) {
		super(i_saltBlock, i_height);
	}
	
	public SaltSurface(TreeType i_saltTree) {
		super(i_saltTree);
	}
	
	
	@Override
	public void GenerateSalt(World world, Block currentBlock, Material[] supportBlock, int X, int Y, int Z)
	{
		++Y;
		currentBlock = world.getBlockAt(X, Y, Z);
		if (saltBlock != null)
			for (int i = 0; i < height; ++i, ++Y)
				if (currentBlock.getType() != Material.AIR) return ;
				else currentBlock.setType(saltBlock);
		else if (currentBlock.getType() == Material.AIR)
			world.generateTree(currentBlock.getLocation(), saltTree);
	}
}
