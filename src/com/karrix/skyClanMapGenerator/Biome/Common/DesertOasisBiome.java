package com.karrix.skyClanMapGenerator.Biome.Common;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Biome;

import com.karrix.skyClanMapGenerator.Biome.BiomeBuilder;
import com.karrix.skyClanMapGenerator.Group.GroundGroup;
import com.karrix.skyClanMapGenerator.Group.SaltGroup;
import com.karrix.skyClanMapGenerator.Salt.Salt;
import com.karrix.skyClanMapGenerator.Salt.SaltCactus;
import com.karrix.skyClanMapGenerator.Salt.SaltTree;

public class DesertOasisBiome extends BiomeBuilder {
	
	public DesertOasisBiome()
	{
		biome 			= Biome.DESERT;
		
		groundSurface 	= Material.SAND;
		groundGroup 	= new GroundGroup[] {
								new GroundGroup(Material.SAND, 1),
								new GroundGroup(Material.SANDSTONE, 1)
							};
		groundSupport 	= Material.SANDSTONE;
		
		saltLayers		= new SaltGroup[] {
			new SaltGroup (256 / 24, 256 / 12 / 2, new Salt[]{
					new Salt(Material.DEAD_BUSH, groundSurface)			
			}),
			new SaltGroup (256 / 40, 256 / 24, new Salt[]{
					new SaltCactus()
			})
		};
		
		treeLayers		= new SaltGroup[] {
				new SaltGroup (256 / 40, 256 / 24, new Salt[]{
						new SaltTree(TreeType.ACACIA, groundSurface),
				})
		};	
		
	}
	
}
