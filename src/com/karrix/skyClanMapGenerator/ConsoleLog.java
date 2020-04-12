package com.karrix.skyClanMapGenerator;

import org.apache.logging.log4j.Logger;

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
