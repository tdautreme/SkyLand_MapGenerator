package com.karrix.skyClanMapGenerator;
import java.util.List;
import java.util.Random;

public class CommonTools {
	private static Random rand = new Random();
	
	public static int randInt(int min, int max)
	{
		return rand.nextInt((max - min) + 1) + min;
	}
	
	public static double randDouble(double min, double max)
	{
		return min + (max - min) * rand.nextDouble();
	}
	
	public static double randProb()
	{
		return 0 + (1 - 0) * rand.nextDouble();
	}
	
	public static <T> T randChoice(T[] array) {
		return array[randInt(0, array.length - 1)];
	}

	public static <T> T randChoice(List<T> list) {
		return list.get(randInt(0, list.size() - 1));
	}
	
	public static <T> int randChoiceIdx(T[] array) {
		return randInt(0, array.length - 1);
	}
	
	public static <T> int randChoiceIdx(List<T> list) {
		return randInt(0, list.size() - 1);
	}
	
	public static <T> T randChoiceDel(List<T> list) {
		int curIdx = randChoiceIdx(list);
		T ret = list.get(curIdx);
		list.remove(curIdx);
		return ret;
	}
	
	public static <T> boolean arrayContain(T[] array, T elem)
	{
		for (T curElem : array)
			if (curElem == elem)
				return true;
		return false;
	}
}