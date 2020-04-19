package com.karrix.skyClanMapGenerator.Biome.Common;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Biome;

import com.karrix.skyClanMapGenerator.Biome.IslandBuilder;
import com.karrix.skyClanMapGenerator.Group.BlockGroup;
import com.karrix.skyClanMapGenerator.Group.SaltGroup;
import com.karrix.skyClanMapGenerator.Salt.Salt;
import com.karrix.skyClanMapGenerator.Salt.SaltSurface;
import com.karrix.skyClanMapGenerator.Salt.Preset.SaltCommonPreset;

public class ForestBiome extends IslandBuilder {
	
	public ForestBiome()
	{
		biome 			= Biome.FOREST;
		
		blockGroup 		= new BlockGroup[] {
								new BlockGroup(Material.GRASS_BLOCK, 1),
								new BlockGroup(Material.DIRT, 2)
							};
		
//		Material[] simpleSaltSurface = new Material[] {Material.GRASS_BLOCK, Material.DIRT};
  		
		saltSurfaceGroup = SaltCommonPreset.GetForestSaltSurfaceGroup();
		saltOreGroup = SaltCommonPreset.GetSimpleOreGroup();
		//		groundSupport 	= Material.STONE;
		
//		groundSurfaceOre = Material.STONE;
		
//		saltLayers		= SaltCommonPreset.SaltLayers(groundSurface);
//		
//		treeLayers		= new SaltGroup[] {
//				new SaltGroup (256 / 40, 256 / 24, new Salt[]{
//						new SaltTree(TreeType.TREE, groundSurface),
//				})
//		};
//		
//		oreLayers		= SaltCommonPreset.OreLayers(groundSurfaceOre);
	}
	
}
