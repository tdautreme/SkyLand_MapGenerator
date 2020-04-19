package com.karrix.skyClanMapGenerator.Group;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.karrix.skyClanMapGenerator.CommonTools;
import com.karrix.skyClanMapGenerator.Biome.BlockKey;
import com.karrix.skyClanMapGenerator.Salt.Salt;

public class SaltGroup {
	public double 		minProb;
	public double 		maxProb;
	public Material[]	supportBlock;
	public Salt[]		group;
	
	public SaltGroup (double i_minProb, double i_maxProb, Material[] i_supportBlock, Salt[] i_group)
	{
		minProb = i_minProb;
		maxProb = i_maxProb;
		supportBlock = i_supportBlock;
		group 	= i_group;
	}
	
	public SaltGroup (int i_minProb, int i_maxProb, Material[] i_supportBlock, Salt i_group)
	{
		minProb = i_minProb;
		maxProb = i_maxProb;
		supportBlock = i_supportBlock;
		group 	= new Salt[] { i_group };
	}

	public void Generate(World world, List<BlockKey> blockDict)
	{
		List<Block> allowedBlock 	= GetAllowedblock(blockDict);
		int 		blockNbr 		= allowedBlock.size();
		int 		sizedMinProb 	= (int)(minProb * blockNbr);
		int 		sizedMaxProb 	= (int)(maxProb * blockNbr);
		int 		amount 			= CommonTools.randInt(sizedMinProb, sizedMaxProb);
		for (int i = 0; i < amount; ++i) {
			Block currentBlock 	= 	CommonTools.randChoiceDel(allowedBlock);
			Salt currentSalt 	= 	CommonTools.randChoice(group);
			currentSalt.generate(world, currentBlock, supportBlock);
		}
	}
	
	public List<Block> GetAllowedblock(List<BlockKey> blockDict)
	{
		List<Block> allowedBlock = new ArrayList<>();
		for (BlockKey blockList : blockDict)
			for (Material allowedMat : supportBlock)
				if (allowedMat == blockList.material)
					allowedBlock.addAll(blockList.list);
		return allowedBlock;
	}
}
