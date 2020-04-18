package com.karrix.skyClanMapGenerator;

public class WorldGeneratorDim {
	public int sizeX;
	public int sizeY;
	public int sizeZ;
	
	public int worldCenterX;
	public int worldCenterY;
	public int worldCenterZ;
	
	public int worldStartX;
	public int worldStartY;
	public int worldStartZ;
	
	public WorldGeneratorDim(int i_sizeX, int i_sizeY, int i_sizeZ, int i_worldCenterX, int i_worldCenterY, int i_worldCenterZ, int i_worldStartX, int i_worldStartY, int i_worldStartZ)
	{
		sizeX = i_sizeX;
		sizeY = i_sizeY;
		sizeZ = i_sizeZ;
		worldCenterX = i_worldCenterX;
		worldCenterY = i_worldCenterY;
		worldCenterZ = i_worldCenterZ;
		worldStartX = i_worldStartX;
		worldStartY = i_worldStartY;
		worldStartZ = i_worldStartZ;
	}
	
	public String toString()
	{
		return "WorldGeneratorDim { size [" + sizeX + ", " + sizeY + ", " + sizeZ + "] - worldCenter [" + worldCenterX + ", " + worldCenterY + ", " + worldCenterZ + "] - worldStart [" + worldStartX + ", " + worldStartY + ", " + worldStartZ + "] }";
	}
}
