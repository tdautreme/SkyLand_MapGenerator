package com.karrix.skyClanMapGenerator.Salt.Preset;

import org.bukkit.Material;

import com.karrix.skyClanMapGenerator.Group.OreGroup;
import com.karrix.skyClanMapGenerator.Group.SaltGroup;
import com.karrix.skyClanMapGenerator.Salt.Salt;
import com.karrix.skyClanMapGenerator.Salt.SaltOre;

public class SaltCommonPreset {
	
	public static SaltGroup[] SaltLayers(Material groundSurface)
	{
		return new SaltGroup[] {
				new SaltGroup (256 / 4, 256 / 2, new Salt[]{
						new Salt(Material.TALL_GRASS, groundSurface),
						new Salt(Material.GRASS, groundSurface),
						new Salt(Material.LARGE_FERN, groundSurface)
				}),
				new SaltGroup (256 / 24, 256 / 12, new Salt[]{
						new Salt(Material.SUNFLOWER, groundSurface),
						new Salt(Material.LILAC, groundSurface),
						new Salt(Material.ROSE_BUSH, groundSurface),
						new Salt(Material.PEONY, groundSurface)
				}),
				new SaltGroup (256 / 40, 256 / 24, new Salt[]{
						new Salt(Material.POPPY, groundSurface),
						new Salt(Material.DANDELION, groundSurface),
						new Salt(Material.BLUE_ORCHID, groundSurface),
						new Salt(Material.ALLIUM, groundSurface),
						new Salt(Material.AZURE_BLUET, groundSurface),
						new Salt(Material.RED_TULIP, groundSurface),
						new Salt(Material.ORANGE_TULIP, groundSurface),
						new Salt(Material.WHITE_TULIP, groundSurface),
						new Salt(Material.PINK_TULIP, groundSurface),
						new Salt(Material.OXEYE_DAISY, groundSurface),
						new Salt(Material.BROWN_MUSHROOM, groundSurface),
						new Salt(Material.RED_MUSHROOM, groundSurface)
				})
		};
	}
	
	public static OreGroup[] OreLayers(Material groundSurfaceOre)
	{
		return new OreGroup[] {
				new OreGroup (2, 4, new SaltOre[]{
						new SaltOre(Material.COAL_ORE, groundSurfaceOre),
				}),
				new OreGroup (2, 4, new SaltOre[]{
						new SaltOre(Material.IRON_ORE, groundSurfaceOre),
				}),
				new OreGroup (2, 4, new SaltOre[]{
						new SaltOre(Material.REDSTONE_ORE, groundSurfaceOre),
						new SaltOre(Material.LAPIS_ORE, groundSurfaceOre),
				}),
				new OreGroup (1, 2, new SaltOre[]{
						new SaltOre(Material.GOLD_ORE, groundSurfaceOre),
						new SaltOre(Material.EMERALD_ORE, groundSurfaceOre),
				}),
				new OreGroup (0, 2, new SaltOre[]{
						new SaltOre(Material.DIAMOND_ORE, groundSurfaceOre),
						new SaltOre(groundSurfaceOre, groundSurfaceOre),
						new SaltOre(groundSurfaceOre, groundSurfaceOre),
						new SaltOre(groundSurfaceOre, groundSurfaceOre),
				})
		};
	}
	
}
