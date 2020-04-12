package com.karrix.skyClanMapGenerator.Group;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;

import com.karrix.skyClanMapGenerator.CommonTools;
import com.karrix.skyClanMapGenerator.Salt.Salt;

public class SaltGroup {
	public int 		minProb;
	public int 		maxProb;
	public Salt[]	group;
	
	public SaltGroup (int i_minProb, int i_maxProb, Salt[] i_group)
	{
		minProb = i_minProb;
		maxProb = i_maxProb;
		group 	= i_group;
	}
	
	public void Generate(Random random, World world, Chunk chunk)
	{
		int amount = CommonTools.randInt(minProb, maxProb);
		for (int i = 0; i < amount; ++i) {
	        int X = random.nextInt(15);
	        int Z = random.nextInt(15);
	        int Y;
	        for (Y = world.getMaxHeight()-1; chunk.getBlock(X, Y, Z).getType() == Material.AIR; Y--); // Find the highest block of the (X,Z) coordinate chosen.
	        Salt currentSalt = CommonTools.randChoice(group);
	        currentSalt.GenerateSalt(random, world, chunk, X, Y, Z);
		}
	}
}
