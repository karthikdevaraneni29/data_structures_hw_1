package main.java;

public class BinarySearch {

	public int recursiveBinarySearch(int[] input_array, int search_num, int low_value, int high_value) {
		if (low_value > high_value) { 
			return -1; 
		}
		int searched_index = (low_value + high_value) / 2; 
		if (input_array[searched_index] == search_num) { 
			return searched_index; 
		}
		else if (input_array[searched_index] < search_num) { 
			return recursiveBinarySearch(input_array, search_num, searched_index + 1, high_value); 
		}
		else { 
			return recursiveBinarySearch(input_array, search_num, low_value, searched_index - 1); 
		}
	}
}