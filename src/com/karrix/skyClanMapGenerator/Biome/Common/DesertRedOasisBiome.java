package com.karrix.skyClanMapGenerator.Biome.Common;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Biome;

import com.karrix.skyClanMapGenerator.Biome.BiomeBuilder;
import com.karrix.skyClanMapGenerator.Group.GroundGroup;
//import com.karrix.skyClanMapGenerator.Group.PlantGroup;
//import com.karrix.skyClanMapGenerator.Group.ProbGroup;
import com.karrix.skyClanMapGenerator.Group.SaltGroup;
import com.karrix.skyClanMapGenerator.Salt.Salt;
import com.karrix.skyClanMapGenerator.Salt.SaltCactus;
import com.karrix.skyClanMapGenerator.Salt.SaltTree;

public class DesertRedOasisBiome extends BiomeBuilder {
	
	public DesertRedOasisBiome()
	{
		biome 			= Biome.DESERT;
		
		groundSurface 	= Material.RED_SAND;
		groundGroup 	= new GroundGroup[] {
								new GroundGroup(Material.RED_SAND, 2)
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
		
		treeLayers		= new SaltGroup[] {
				new SaltGroup (256 / 40, 256 / 24, new Salt[]{
						new SaltTree(TreeType.ACACIA, groundSurface),
				})
		};	
		
	}
	
}
