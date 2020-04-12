package com.karrix.skyClanMapGenerator.Salt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.karrix.skyClanMapGenerator.CommonTools;
import com.karrix.skyClanMapGenerator.Vector3;

public class SaltOre extends Salt {

	private int 	oreCounter;
	private int 	minOre 		= 3;
	private int 	maxOre 		= 6;
	private int 	limitOre;
	
	public SaltOre(Material i_saltBlock, Material i_surfaceBlock, Integer... argsArr) {
		super(i_saltBlock, i_surfaceBlock, argsArr);
	}
	
	@Override
	public void GenerateSalt(Random random, World world, Chunk chunk, int X, int Y, int Z)
	{
		limitOre = CommonTools.randInt(minOre, maxOre);
		oreCounter = 0;
		
		OrePropagationRecursive(world, new Vector3(X, Y, Z));
	}
	
	public void OrePropagationRecursive(World world, Vector3 location)
	{

		int X = location.getX();
		int Y = location.getY();
		int Z = location.getZ();
		Block currentBlock = world.getBlockAt(X, Y, Z);

		if (currentBlock.getType() != surfaceBlock) return;
		
		if (oreCounter < limitOre)  {
			currentBlock.setType(saltBlock);
			++oreCounter;
			
			List<Vector3> neightboors = new ArrayList<Vector3>();
			neightboors.add(new Vector3(X + 1, Y, Z));
			neightboors.add(new Vector3(X - 1, Y, Z));
			neightboors.add(new Vector3(X, Y + 1, Z));
			neightboors.add(new Vector3(X, Y - 1, Z));
			neightboors.add(new Vector3(X, Y, Z + 1));
			neightboors.add(new Vector3(X, Y, Z - 1));
			
			while (oreCounter < limitOre && neightboors.size() > 0)
			{
				int currentIdx = CommonTools.randInt(0, neightboors.size() - 1);
				OrePropagationRecursive(world, neightboors.get(currentIdx));
				neightboors.remove(currentIdx);
			}
		}
	}
}
