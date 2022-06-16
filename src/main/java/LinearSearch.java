package main.java;

public class LinearSearch {
	
	public int linearSearch(int inputArray[], int target_number) {
		int search = -1;
		for(int i=0;i<inputArray.length;i++) {
			if (inputArray[i] == target_number) {
				search = i;
				return search;
			}
		}
		return search;
	}	
}