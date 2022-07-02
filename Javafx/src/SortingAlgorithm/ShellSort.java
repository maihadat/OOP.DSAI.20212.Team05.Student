package SortingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class ShellSort extends Sort implements Display {
	// Attribute storing indices of two switching elements at the i step as an array (= currentSwitchIndex.get(i)) 
	private ArrayList<int[]> currentSwitchIndex;
	
	
	// Constructors
	public ShellSort(int arrayLength) {
		super(arrayLength);
		currentSwitchIndex = new ArrayList<int[]>();
	}
	public ShellSort(int[] inputArray) {
		super(inputArray);
		currentSwitchIndex = new ArrayList<int[]>();
	}
	
	
	
	// Methods
	public int[] getCurrentSwitchIndex(int stepNumber) {
		return currentSwitchIndex.get(stepNumber);
	}
	
	public int[] displayStart() {
		currentSteps = 0;
		return arrayOfSteps.get(0);
	}
	public int[] displayFinish() {
		currentSteps = this.getNumberSteps();
		return arrayOfSteps.get(this.getNumberSteps());
	}
	public int[] nextStep() {
		currentSteps += 1;
		return arrayOfSteps.get(this.currentSteps+1);
	}
	public int[] preStep() {
		currentSteps -= 1;
		return arrayOfSteps.get(this.currentSteps-1);
	}
	
	public void sort() {
		numberSteps = 0;
		arrayOfSteps.add(arr.clone());
	    for (int gap = n / 2; gap > 0; gap /= 2) {
	        for (int i = gap; i < n; i++) {
	            int key = arr[i];
	            int j;
	            for (j = i; j >= gap && arr[j - gap] > key; j -= gap) {
                    arr[j] = arr[j - gap];
	            }
	            arr[j] = key;
	            int[] tmp = new int[3];
	            if (j==i) {
	            	tmp[1] = i;
	            	tmp[0] = j-gap;
	            	tmp[2] = gap;
	            }else {
	            	tmp[1] = i;
	            	tmp[0] = j;
	            	tmp[2] = gap;
	            }
	            numberSteps += 1;
	            arrayOfSteps.add(arr.clone());
	            currentSwitchIndex.add(tmp);
	        }
	    }
	    arrayOfSteps.add(arr.clone());
	}	
	
	
	// test 
	/*
	public static void main(String[] args) {
		ShellSort s = new ShellSort(10);
		System.out.println("Init array: " + Arrays.toString(s.arr));
		s.sort();
		for(int i=0; i<=s.getNumberSteps(); i++) {
			System.out.print("Step "+ i + ": ");
			System.out.println(Sort.displayStep(s.arrayOfSteps, i));
		}	
	}
	*/
}
