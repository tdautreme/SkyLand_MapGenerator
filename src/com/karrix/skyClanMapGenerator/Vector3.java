package com.karrix.skyClanMapGenerator;

public class Vector3 {
	private int X;
	private int Y;
	private int Z;
	
	public Vector3(int i_X, int i_Y, int i_Z)
	{
		setX(i_X);
		setY(i_Y);
		setZ(i_Z);
	}
	
	public int getX() {
		return X;
	}
	public int getY() {
		return Y;
	}	
	public int getZ() {
		return Z;
	}
	
	public void setX(int value) {
		X = value;
	}
	public void setY(int value) {
		Y = value;
	}
	public void setZ(int value) {
		Z = value;
	}
	
	public String toString()
	{
		return "[" + X + ", " + Y + ", " + Z + "]";
	}
}
