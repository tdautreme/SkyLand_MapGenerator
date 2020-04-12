package com.karrix.skyClanMapGenerator.Biome.Common;

import org.bukkit.Material;
import org.bukkit.block.Biome;

import com.karrix.skyClanMapGenerator.Biome.BiomeBuilder;
import com.karrix.skyClanMapGenerator.Group.GroundGroup;
import com.karrix.skyClanMapGenerator.Group.SaltGroup;
import com.karrix.skyClanMapGenerator.Salt.Salt;
import com.karrix.skyClanMapGenerator.Salt.SaltCactus;

public class DesertRedBiome extends BiomeBuilder {
	
	public DesertRedBiome()
	{
		biome 			= Biome.DESERT;
		
		groundSurface 	= Material.RED_SAND;
		groundGroup 	= new GroundGroup[] {
								new GroundGroup(Material.RED_SAND, 1),
								new GroundGroup(Material.RED_SANDSTONE, 1)
							};
		groundSupport 	= Material.RED_SANDSTONE;
		
		saltLayers		= new SaltGroup[] {
			new SaltGroup (256 / 24, 256 / 12 / 2, new Salt[]{
					new Salt(Material.DEAD_BUSH, groundSurface)			
			}),
			new SaltGroup (256 / 40, 256 / 24, new Salt[]{
					new SaltCactus()
			})
		};
	}
	
}
