package SortingAlgorithm;

public class ShellSort extends Sort{
	// Constructors
	public ShellSort(int arrayLength) {
		super(arrayLength);
	}
	public ShellSort(int[] inputArray) {
		super(inputArray);
	}
	

	// Methods	
	public void sort() {
		numberSteps = 0;
		arrayOfSteps.add(arr.clone());
	    for (int gap = n / 2; gap > 0; gap /= 2) {
	        for (int i = gap; i < n; i++) {
	            int key = arr[i];
	            int j;
	            for (j = i; j >= gap && arr[j - gap] > key; j -= gap) 
                    arr[j] = arr[j - gap];
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
}