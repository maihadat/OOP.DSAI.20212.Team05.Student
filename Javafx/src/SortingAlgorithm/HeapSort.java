package SortingAlgorithm;

public class HeapSort extends Sort{
		
		public HeapSort(int arrayLength) {
			super(arrayLength);
		}
	    public HeapSort(int[] inputArray) {
			super(inputArray);
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
	
	    // test
	    /*
		public static void main(String[] args) {
			HeapSort s = new HeapSort(3);
			s.sort();
			s.displayStart();
			for (int i = 0; i < s.arrayOfSteps.size(); i++) {
				for (int j = 0; j < s.arrayOfSteps.get(i).length; j++) {
	    			System.out.printf("%d ", s.arrayOfSteps.get(i)[j]);
	    		}
	            if (i < s.arrayOfSteps.size() - 1) {
	            	System.out.printf("\n%d %d\n", s.getCurrentSwitchIndex(i)[0], s.getCurrentSwitchIndex(i)[1]);
	            }
			}
		}
		*/
	}