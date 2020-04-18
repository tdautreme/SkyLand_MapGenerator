package com.karrix.skyClanMapGenerator.WorldGenerator;

public class WorldGeneratorGetter {
	public static WorldGenerator Get(String generatorName)
	{
		return new WorldGeneratorSimpleIsland();
	}
}
