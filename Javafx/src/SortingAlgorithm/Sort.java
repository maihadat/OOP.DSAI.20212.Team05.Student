package SortingAlgorithm;

import java.util.ArrayList;
import java.util.Random;

public abstract class Sort implements Display {
	protected int n;
	protected int[] arr;
	protected int numberSteps;
	protected int currentSteps;
	// Storing all the sorted array in sorting process step by step 
	public ArrayList<int[]> arrayOfSteps; 
	public ArrayList<int[]> currentSwitchIndex;
	
	// getters
	public int getN() {
		return n;
	}
	public int getNumberSteps() {
		return numberSteps;
	}
	public int[] getArr() {
		return arr;
	}
	
	
	// Constructor for input an array
	public Sort(int[] inputArray) {
		n = inputArray.length;
		arr = inputArray;
		numberSteps = 0;
		currentSteps = 0;
		arrayOfSteps = new ArrayList<int[]>();
	}
	// Constructor create a random array based on the input array length
	public Sort(int arrayLength) {
		n = arrayLength;
		this.Randomize(arrayLength);
		numberSteps = 0;
		currentSteps = 0;
		arrayOfSteps = new ArrayList<int[]>();
	}
	
	
	// Methods
	// These two below method is use to create an n length with element from 1->n  and randomize it 
	private void Randomize(int arrayLength) {
		n = arrayLength;
		int[] initArray = new int[arrayLength];
		for(int i=1; i<=arrayLength; i++) {
			initArray[i-1] = i; 
		}
		shuffleArray(initArray);
		arr = initArray;
	}
	private void shuffleArray(int[] array) {
	    int index;
	    Random random = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        if (index != i)
	        {
	            array[index] ^= array[i];
	            array[i] ^= array[index];
	            array[index] ^= array[i];
	        }
	    }
	}
	// Abstract method
	public void sort() {
	}
	// Return a string of sorted array in the process given step number
	protected static String displayStep(ArrayList<int[]> arrayOfSteps, int stepNumber) {
		int n = arrayOfSteps.get(0).length;
		String rs = "";
		for(int index=0; index<n; index++) {
			if(index == n-1) {
				rs += String.valueOf(arrayOfSteps.get(stepNumber)[index]);
			}else {
				rs += String.valueOf(arrayOfSteps.get(stepNumber)[index]) + "; ";
			}
		}
		return rs;
	}
}
