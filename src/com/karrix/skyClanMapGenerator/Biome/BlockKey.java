package com.karrix.skyClanMapGenerator.Biome;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockKey {
	public Material 	material;
	public List<Block> 	list 		= new ArrayList<>();
	public int			baseLength 	= 0;
	
	public BlockKey(Block firstBlock)
	{
		material = firstBlock.getType();
		list.add(firstBlock);
	}
	
	public void SetBaseLength()
	{
		baseLength = list.size();
	}
}
