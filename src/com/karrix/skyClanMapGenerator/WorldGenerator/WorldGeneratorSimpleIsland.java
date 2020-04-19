package com.karrix.skyClanMapGenerator.WorldGenerator;

import org.bukkit.World;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.karrix.skyClanMapGenerator.ChunkConfig;
import com.karrix.skyClanMapGenerator.CommonTools;

public class WorldGeneratorSimpleIsland extends WorldGenerator {
	
	@Override
	public void generateWorld(World world, int chunkX, int chunkZ)
	{
		int maxSeaHeightRange = 8;
		sizeX = CommonTools.randInt(35, 50);
		sizeZ = CommonTools.randInt(35, 50);
		
		int botHeight = (int)((sizeX > sizeZ ? sizeX : sizeZ) * 0.75);		
		int topHeight = botHeight / 4; 
		sizeY = topHeight + botHeight;
		
		
		worldCenterX = chunkX * 16 + 8;
		worldCenterY = CommonTools.randInt(
				ChunkConfig.seaHeight - maxSeaHeightRange,
				ChunkConfig.seaHeight + maxSeaHeightRange
				);
		worldCenterZ = chunkZ * 16 + 8;
		
		worldStartX = worldCenterX - sizeX / 2;
		worldStartY = worldCenterY - botHeight;
		worldStartZ = worldCenterZ - sizeZ / 2;
		
		double[][][] perlinMap = perlinMapGenerator(sizeX, sizeZ, topHeight, botHeight);		
		ShapeGenerator(world,  perlinMap[0], perlinMap[1], sizeX, sizeZ, worldStartX, worldStartZ, worldCenterY);
	}
	
	public void ShapeGenerator(World world, double[][] topPerlin, double[][] botPerlin, int sizeX, int sizeZ, int worldStartX, int worldStartZ, int worldCenterY)
	{
		int topHeight;
		int botHeight;
    	for (int  X = 0; X < sizeX; ++X)
    		for (int Z = 0; Z < sizeZ; ++Z)
    		{
    			topHeight = (int)topPerlin[X][Z];
    			botHeight = (int)botPerlin[X][Z];
    			if (topHeight + botHeight > 1)
    			{
        			for (int Y = 0; Y < topHeight; ++Y)
        				world.getBlockAt(worldStartX + X, worldCenterY + Y, worldStartZ + Z).setType(constructMaterial);
    				for (int Y = 1; Y < botHeight; ++Y)
        				world.getBlockAt(worldStartX + X, worldCenterY - Y, worldStartZ + Z).setType(constructMaterial);	
    			}
    		}
	}
	
	public double[][][] perlinMapGenerator(int sizeX, int sizeZ, int topHeight, int botHeight)
	{
		double[][] perlin_map_moule = matrixPerlinGenerator(sizeX, sizeZ, 2D);
		matrixNormalizer(perlin_map_moule); // ATATATA
		matrixThreesholdNeg(perlin_map_moule, 0.3D);
		
		double[][] perlin_map = matrixPerlinGenerator(sizeX, sizeZ, 2D); 
		matrixNormalizer(perlin_map); // ATATATA
		matrixThreesholdNeg(perlin_map, 0.3D);

		matrixFirstMult(perlin_map, perlin_map_moule);

		
		double[][] circle_grad = getElipseGradiant(sizeX, sizeZ);

		matrixFirstMult(perlin_map, circle_grad);

		double[][] down_island = matrixPerlinGenerator(sizeX, sizeZ, 5D); 
		matrixNormalizer(down_island); // ATATATA
		matrixFirstMult(down_island, perlin_map);
		matrixNormalizer(down_island); // ATATATA
		double max_perlin_map = topHeight / matrixMax(perlin_map);
		double max_down_map = botHeight / matrixMax(down_island);
		
		for (int y = 0; y < perlin_map.length; ++y)
			for (int x = 0; x < perlin_map[y].length; ++x)
			{
				perlin_map[y][x] *= max_perlin_map;
				down_island[y][x] *= max_down_map;
			}
		return new double[][][]{perlin_map, down_island};
	}
	
	public static double matrixMin(double[][] matrix)
	{
		boolean found = false;
		double min = 0;
		for (int y = 0; y < matrix.length; ++y)
			for (int x = 0; x < matrix[y].length; ++x)
				if (!found || matrix[y][x] < min)
				{
					found = true;
					min = matrix[y][x];
				}
		return min;
	}
	
	public static double matrixMax(double[][] matrix)
	{
		boolean found = false;
		double max = 0;
		for (int y = 0; y < matrix.length; ++y)
			for (int x = 0; x < matrix[y].length; ++x)
				if (!found || matrix[y][x] > max)
				{
					found = true;
					max = matrix[y][x];
				}
		return max;
	}
	
	public static void matrixNormalizer(double[][] matrix)
	{
		double min = matrixMin(matrix);
		double max = matrixMax(matrix);
		
		for (int y = 0; y < matrix.length; ++y)
			for (int x = 0; x < matrix[y].length; ++x)
				matrix[y][x] = (matrix[y][x] - min) / (max - min);
	}
	
	public static double[][] getElipseGradiant(int size_y, int size_x)
	{
		double[][] 	elipse_gradiant = new double[size_y][size_x];
		double 		center_x 		= size_x / 2;
		double 		center_y 		= size_y / 2;
		int  		grad_pow 		= 3;
		
		double 		max_x 			= 0;
		double 		max_y 			= center_x;
		
		double 		max_grad 		= Math.sqrt(max_x * max_x + max_y * max_y);
		max_grad = Math.pow(max_grad, grad_pow);
		
		double dist_x, dist_y, new_x, new_y, curdist;
		
		for (int y = 0; y < elipse_gradiant.length; ++y)
			for (int x = 0; x < elipse_gradiant[y].length; ++x)
			{
	            dist_x = Math.abs((double)x - center_x);
                dist_y = Math.abs((double)y - center_y);
     
                new_x = dist_y * center_x / center_y;
                new_y = dist_x;

                curdist = Math.sqrt((new_x * new_x) + (new_y * new_y));
                curdist = Math.pow(curdist, grad_pow);
                if (curdist > max_grad)
                    curdist = max_grad;
                curdist = max_grad - curdist;
                elipse_gradiant[y][x] = curdist;
			}
		return elipse_gradiant;
	}
	
	public static void matrixFirstMult(double[][] matrix_1, double[][] matrix_2)
	{
		for (int y = 0; y < matrix_1.length; ++y)
			for (int x = 0; x < matrix_1[y].length; ++x)
				matrix_1[y][x] *= matrix_2[y][x];
	}
	
	public static double[][] matrixPerlinGenerator(int size_y, int size_x, double frequency)
	{
		int seed = CommonTools.randInt(0, 99999);
    	SimplexOctaveGenerator generator = new SimplexOctaveGenerator(seed, 8);
        generator.setScale(0.005D);
        
		double[][] perlin_map = new double[size_y][size_x];
		for (int y = 0; y < perlin_map.length; ++y)
			for (int x = 0; x < perlin_map[y].length; ++x)
				perlin_map[y][x] = (generator.noise(y, x, frequency, 0.5D, true) + 1) / 2;
		return perlin_map;
	}
	
	public static void matrixThreesholdNeg(double[][] matrix, double threeshold) 
	{
		for (int y = 0; y < matrix.length; ++y)
			for (int x = 0; x < matrix[y].length; ++x)
				if (matrix[y][x] < threeshold)
					matrix[y][x] = 0;
	}
}
