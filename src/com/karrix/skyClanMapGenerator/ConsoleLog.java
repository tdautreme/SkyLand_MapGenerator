package com.karrix.skyClanMapGenerator;

public class ConsoleLog {
	private static MapGenerator mapGenerator;
	
	public static void SetLogger(MapGenerator i_mapGenerator)
	{
		mapGenerator = i_mapGenerator;
	}
	
	public static void Log(String message)
	{
		mapGenerator.getLogger().info(message);
	}
}
