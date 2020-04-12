package com.karrix.skyClanMapGenerator.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.karrix.skyClanMapGenerator.CommonTools;
import com.karrix.skyClanMapGenerator.Salt.Salt;

public class OreGroup extends SaltGroup {
	private int YminResearch;
	private int YmaxResearch;
	
	public OreGroup(int i_minProb, int i_maxProb, Salt[] i_group) {
		super(i_minProb, i_maxProb, i_group);
	}

	@Override
	public void Generate(Random random, World world, Chunk chunk)
	{
		List<Block> surfaceBlockList;
		setYMinMaxResearch(world, chunk);
		int amount = CommonTools.randInt(minProb, maxProb);
		for (int i = 0; i < amount; ++i) {
	        Salt currentSalt = CommonTools.randChoice(group);
	        surfaceBlockList = GetSurfaceblockList(chunk, currentSalt.surfaceBlock);
	        
	        Block targetBlock = CommonTools.randChoiceList(surfaceBlockList);
	        currentSalt.GenerateSalt(random, world, chunk, targetBlock.getX(), targetBlock.getY(), targetBlock.getZ());
		}
	}
	
	private List<Block> GetSurfaceblockList(Chunk chunk, Material surfaceBlock)
	{
		List<Block> surfaceBlockList = new ArrayList<Block>();
		for (int Y = YminResearch; Y < YmaxResearch + 1; ++Y)
			for (int X = 0; X < 16; ++X)
				for (int Z = 0; Z < 16; ++Z)
					if (chunk.getBlock(X, Y, Z).getType() == surfaceBlock)
						surfaceBlockList.add(chunk.getBlock(X, Y, Z));
		
		return surfaceBlockList;
	}
	
	// To optimize research
	private void setYMinMaxResearch(World world, Chunk chunk)
	{
		YminResearch = world.getMaxHeight() - 1;
		YmaxResearch = 0;
		for (int Y = 0; Y < world.getMaxHeight() - 1; ++Y)
			for (int X = 0; X < 16; ++X)
				for (int Z = 0; Z < 16; ++Z)
				{
					if (chunk.getBlock(X, Y, Z).getType() != Material.AIR && chunk.getBlock(X, Y, Z).getType() != Material.VOID_AIR)
					{
						if (Y < YminResearch)
							YminResearch = Y;
						if (Y > YmaxResearch)
							YmaxResearch = Y;
					}
				}

	}
}
