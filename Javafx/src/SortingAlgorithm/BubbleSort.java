package SortingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class BubbleSort extends Sort implements Display {
	// Attribute storing indices of two switching elements at the i step as an array
	// (= currentSwitchIndex.get(i))

	// Constructors
	public BubbleSort(int arrayLength) {
		super(arrayLength);
		currentSwitchIndex = new ArrayList<int[]>();
	}

	public BubbleSort(int[] inputArray) {
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
		return arrayOfSteps.get(this.currentSteps + 1);
	}

	public int[] preStep() {
		currentSteps -= 1;
		return arrayOfSteps.get(this.currentSteps - 1);
	}

	public void sort() {
		numberSteps = 0;
		arrayOfSteps.add(arr.clone());
		// Bubble Sort
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				int[] tmp = new int[2];
				tmp[0] = j;
				tmp[1] = j + 1;
				currentSwitchIndex.add(tmp);
				if (arr[j] > arr[j + 1]) {
					int tmp2 = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp2;
				}
				arrayOfSteps.add(arr.clone());
				numberSteps++;
			}
		}
		arrayOfSteps.add(arr.clone());
	}

	// test
	public static void main(String[] args) {
		BubbleSort s = new BubbleSort(10);
		s.sort();
		for (int i = 0; i < s.numberSteps; i++) {
			System.out.println(Sort.displayStep(s.arrayOfSteps, i) + " : " + Arrays.toString(s.currentSwitchIndex.get(i)));
		}
		System.out.println(Arrays.toString(s.displayFinish()));
	}

}
