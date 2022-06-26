package SortingAlgorithm;

import java.util.ArrayList;

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
		return arrayOfSteps.get(this.getNumberSteps()-1);
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
	            int[] tmp = new int[2];
	            if (j==i) {
	            	tmp[0] = i;
	            	tmp[1] = j-gap;
	            }else {
	            	tmp[0] = i;
	            	tmp[1] = j;
	            }
	            numberSteps += 1;
	            arrayOfSteps.add(arr.clone());
	            currentSwitchIndex.add(tmp);
	        }
	    }
	}	
	
	/*
	// test
	public static void main(String[] args) {
		ShellSort s = new ShellSort(10);
		s.sort();
		s.displayStart();
		System.out.print(Sort.displayStep(s.arrayOfSteps, s.currentSteps) + " ");
		System.out.print(s.getCurrentSwitchIndex(s.currentSteps)[0] + " ");
		System.out.println(s.getCurrentSwitchIndex(s.currentSteps)[1]);
		for(int i=0; i<s.getNumberSteps()-1; i++) {
			s.nextStep();
			System.out.print(Sort.displayStep(s.arrayOfSteps, s.currentSteps) + " ");
			System.out.print(s.getCurrentSwitchIndex(s.currentSteps)[0] + " ");
			System.out.println(s.getCurrentSwitchIndex(s.currentSteps)[1]);
		}
	}
	*/
}
