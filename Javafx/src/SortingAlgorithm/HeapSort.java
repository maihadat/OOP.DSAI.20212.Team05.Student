package SortingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class HeapSort extends Sort implements Display {
	
	public HeapSort(int arrayLength) {
		super(arrayLength);
		currentSwitchIndex = new ArrayList<int[]>();
	}
    public HeapSort(int[] inputArray) {
		super(inputArray);
		currentSwitchIndex = new ArrayList<int[]>();
	}

    void heapify(int arr[], int n, int i)
    {
        int parent = i;
        int l = 2*i + 1;
        int r = 2*i + 2;
  
        if (l < n && arr[l] > arr[parent]) {
            parent = l;
        }
        if (r < n && arr[r] > arr[parent]) {
        	parent = r;
        }
        if (parent != i) {
            int swap = arr[i];
            arr[i] = arr[parent];
            arr[parent] = swap;
            int[] si = {i, parent};
            currentSwitchIndex.add(si);
            numberSteps++;
        	arrayOfSteps.add(arr.clone());
            heapify(arr, n, parent);
        }
    }
    
    public void sort()
    {
    	numberSteps = 0;
    	arrayOfSteps.add(arr.clone());
        int n = arr.length; 
        for (int i = n/2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            int[] si = {i, 0};
            currentSwitchIndex.add(si);
            numberSteps++;
        	arrayOfSteps.add(arr.clone());
            heapify(arr, i, 0);
        }
    }
    
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
	
	public static void main(String[] args) {
		HeapSort s = new HeapSort(10);
		s.sort();
		for (int i = 0; i <= s.numberSteps; i++) {
			for (int j = 0; j < s.arrayOfSteps.get(i).length; j++) {
    			System.out.printf("%d ", s.arrayOfSteps.get(i)[j]);
    		}
            if (i < s.arrayOfSteps.size() - 1) {
            	System.out.printf("\n%d %d\n", s.getCurrentSwitchIndex(i)[0], s.getCurrentSwitchIndex(i)[1]);
            }
		}
	}
}
