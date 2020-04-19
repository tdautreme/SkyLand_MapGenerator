package com.karrix.skyClanMapGenerator.Salt.Preset;

import org.bukkit.Material;
import org.bukkit.TreeType;

import com.karrix.skyClanMapGenerator.Group.SaltGroup;
import com.karrix.skyClanMapGenerator.Salt.Salt;
import com.karrix.skyClanMapGenerator.Salt.SaltOre;
import com.karrix.skyClanMapGenerator.Salt.SaltSurface;

public class SaltCommonPreset {
	
	public static Material[] simpleSaltSurfaceSupport = new Material[] {Material.GRASS_BLOCK, Material.DIRT};
	public static Material[] simpleSaltOreSupport = new Material[] {Material.STONE};
	
	public static Salt[] GetForestTreeSalt()
	{
		return new Salt[] {
			new SaltSurface(TreeType.TREE)
		};
	}
	
	public static SaltGroup GetForestTreeSaltGroup()
	{
		return new SaltGroup(0.02, 0.04, simpleSaltSurfaceSupport, GetForestTreeSalt());
	}
	
	// ==================================================
	
	public static Salt[] GetForestRareFlowerSalt()
	{
		return new Salt[] {
				new SaltSurface(Material.POPPY),
				new SaltSurface(Material.DANDELION),
				new SaltSurface(Material.BLUE_ORCHID),
				new SaltSurface(Material.ALLIUM),
				new SaltSurface(Material.AZURE_BLUET),
				new SaltSurface(Material.RED_TULIP),
				new SaltSurface(Material.ORANGE_TULIP),
				new SaltSurface(Material.WHITE_TULIP),
				new SaltSurface(Material.PINK_TULIP),
				new SaltSurface(Material.OXEYE_DAISY),
				new SaltSurface(Material.BROWN_MUSHROOM),
				new SaltSurface(Material.RED_MUSHROOM),
				new SaltSurface(Material.MELON),
				new SaltSurface(Material.PUMPKIN)
		};
	}
	
	public static SaltGroup GetForestRareFlowerSaltGroup()
	{
		return new SaltGroup(0.02, 0.04, simpleSaltSurfaceSupport, GetForestRareFlowerSalt());
	}
	
	// ==================================================

	public static Salt[] GetForestFlowerSalt()
	{
		return new Salt[] {
				new SaltSurface(Material.SUNFLOWER),
				new SaltSurface(Material.LILAC),
				new SaltSurface(Material.ROSE_BUSH),
				new SaltSurface(Material.PEONY)
		};
	}
	
	public static SaltGroup GetForestFlowerSaltGroup()
	{
		return new SaltGroup(0.05, 0.1, simpleSaltSurfaceSupport, GetForestFlowerSalt());
	}
	
	// ==================================================
	
	public static Salt[] GetForestGrassSalt()
	{
		return new Salt[] {
			new SaltSurface(Material.TALL_GRASS),
			new SaltSurface(Material.GRASS),
			new SaltSurface(Material.LARGE_FERN)
		};
	}
	
	public static SaltGroup GetForestGrassSaltGroup()
	{
		return new SaltGroup(0.20, 0.40, simpleSaltSurfaceSupport, GetForestGrassSalt());
	}
	
	
	// ================================================= REGROUP SURFACE SALT
	
	public static SaltGroup[] GetForestSaltSurfaceGroup()
	{
		return new SaltGroup[] {
				GetForestTreeSaltGroup(),
				GetForestRareFlowerSaltGroup(),
				GetForestFlowerSaltGroup(),
				GetForestGrassSaltGroup()
		};
	}
	
	// ================================================= Ground area
	
	public static SaltGroup[] GetSimpleOreGroup()
	{
		return new SaltGroup[] {
			new SaltGroup (0, 0.00015, simpleSaltOreSupport, new Salt[]{
					new SaltOre(Material.DIAMOND_ORE),
			}),
			new SaltGroup (0.0003, 0.00075, simpleSaltOreSupport, new Salt[]{
					new SaltOre(Material.GOLD_ORE),
					new SaltOre(Material.EMERALD_ORE),
			}),
			new SaltGroup (0.0003, 0.00075, simpleSaltOreSupport, new Salt[]{
					new SaltOre(Material.REDSTONE_ORE),
					new SaltOre(Material.LAPIS_ORE),
			}),
			new SaltGroup (0.0006, 0.0009, simpleSaltOreSupport, new Salt[]{
					new SaltOre(Material.IRON_ORE),
			}),
			new SaltGroup (0.00075, 0.00105, simpleSaltOreSupport, new Salt[]{
					new SaltOre(Material.COAL_ORE),
			}),
		};
	}
}
