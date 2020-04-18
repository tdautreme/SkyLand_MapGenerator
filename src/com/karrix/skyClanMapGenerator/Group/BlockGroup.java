package com.karrix.skyClanMapGenerator.Group;

import org.bukkit.Material;

public class BlockGroup {
	public Material[]	material;
	public BlockGroup[] blockGroup;
	public int 			layerNbr;
	public boolean		isMaterial;
	
	
	public BlockGroup(Material i_material, int i_layerNbr)
	{
		material = new Material[]{ i_material };
		layerNbr = i_layerNbr;
		isMaterial = true;
	}
	
	public BlockGroup(Material[] i_material, int i_layerNbr)
	{
		material = i_material;
		layerNbr = i_layerNbr;
		isMaterial = true;
	}
	
	public BlockGroup(BlockGroup[] i_blockGroup, int i_layerNbr)
	{
		blockGroup = i_blockGroup;
		layerNbr = i_layerNbr;
		isMaterial = false;
	}
}