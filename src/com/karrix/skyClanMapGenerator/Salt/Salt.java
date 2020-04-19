package com.karrix.skyClanMapGenerator.Salt;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;

public abstract class Salt {
	protected Material 		saltBlock;
	protected TreeType		saltTree;
	protected int 			height;
	protected int 			neg;
	public Salt(Material i_saltBlock)
	{
		saltBlock 		= i_saltBlock;
		neg 			= 1;
		height 			= 1;
	}
	
	public Salt(Material i_saltBlock, int i_height)
	{
		saltBlock 		= i_saltBlock;
		neg 			= i_height < 0 ? -1 : 1;
		height 			= i_height * neg;
	}
	
	public Salt(TreeType i_saltTree)
	{
		saltBlock 		= null;
		saltTree 		= i_saltTree;
		height 			= 1;
	}
	
	public void generate(World world, Block currentBlock, Material[] supportBlock)
	{
		Location loc = currentBlock.getLocation();
		GenerateSalt(world, currentBlock, supportBlock, (int)loc.getX(), (int)loc.getY(), (int)loc.getZ());
	}
	
	public void GenerateSalt(World world, Block currentBlock, Material[] supportBlock, int X, int Y, int Z)
	{
		for (int i = 0; i < height; ++i)
		{
			currentBlock.setType(saltBlock);
			Y += neg;
			currentBlock = world.getBlockAt(X, Y, Z);
		}
	}
}
