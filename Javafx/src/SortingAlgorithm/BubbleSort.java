package SortingAlgorithm;

public class BubbleSort extends Sort implements Display {
	// Constructors
	public BubbleSort(int arrayLength) {
		super(arrayLength);
	}

	public BubbleSort(int[] inputArray) {
		super(inputArray);
	}

	// Methods
	public void sort() {
		numberSteps = 0;
		arrayOfSteps.add(arr.clone());
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int[] tmp = new int[2];
					tmp[0] = j;
					tmp[1] = j + 1;
					currentSwitchIndex.add(tmp);
					int tmp2 = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp2;
					arrayOfSteps.add(arr.clone());
					numberSteps++;

				}

			}
		}
	}

	// test
	/*
	public static void main(String[] args) {
		BubbleSort s = new BubbleSort(10);
		s.sort();
		s.displayStart();
		System.out.print(Sort.displayStep(s.arrayOfSteps, s.currentSteps) + " ");
		System.out.print(s.getCurrentSwitchIndex(s.currentSteps)[0] + " ");
		System.out.println(s.getCurrentSwitchIndex(s.currentSteps)[1]);
		for (int i = 0; i < 51; i++) {
			s.nextStep();
			System.out.print(Sort.displayStep(s.arrayOfSteps, s.currentSteps) + " ");
			System.out.print(s.getCurrentSwitchIndex(s.currentSteps)[0] + " ");
			System.out.println(s.getCurrentSwitchIndex(s.currentSteps)[1]);
		}
	}*/

}