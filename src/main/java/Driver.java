package main.java;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Driver {

	int input[] = new int[(int)Math.pow(2,25)];
	String fileName = "";
	long linear_search_time, binary_search_time, sort_time;

	//function to read numbers from text file, if exists else generate random numbers upto 1000 from range 1 to 2 power 25
	public void loadInputArray() {
		File file = new File(fileName);
		try{
			if(file.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(file)); //if files exists then read it and load to array
				String line = null;
				ArrayList<Integer> integerArrayList = new ArrayList<>();

				while((line = br.readLine()) != null) {
					String[] tmp = line.split(" ");
					for(String s: tmp)
						integerArrayList.add(Integer.valueOf(s));

					input = integerArrayList.stream().mapToInt(value -> value).toArray();
				}
				br.close();
			} else { //if file not exists 
				int max = (int)Math.pow(2,25); //then generate maximum numbers from 1 to 2 power 25
				int min = 1; //min value is 1
				int range = max - min + 1;
				Random rand = new Random();
				StringBuilder sb = new StringBuilder();
				for(int i=0;i<5000;i++) { //loop for 5000 numbers
					int random_number =  rand.nextInt(range) + min;
					input[i] = random_number;
					sb.append(random_number+" "); //add random number to buffer
				}
				FileWriter fw = new FileWriter("input_5000.txt"); //save buffer to file for future used
				fw.write(sb.toString());
				fw.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	//function to read search number from user keyboard
	public int getSearchedNumber() { 
		int num = 0;
		try{
			System.out.println();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter any number to search : ");
			num = Integer.parseInt(br.readLine().trim());
		}catch(IOException e) {
			e.printStackTrace();
		}
		return num;
	}

	public String getinputFileName() {
		String file = "";
		try {
			System.out.println();
			BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter the File name with an extension to read input from");
			file  = String.format(buff.readLine().trim());
			fileName = file;
		} catch (IOException e){
			e.printStackTrace();
		}
		return file;
	}

	public void linearSearch(int num) {
		LinearSearch ls = new LinearSearch();
		long start = System.nanoTime(); //get system start time before execution
		int position = ls.linearSearch(input,num); //here execute linear search function
		long end = System.nanoTime(); //get system end time after linear serach execution
		linear_search_time = end - start; //calculate linear search time
		if (position == -1)
			System.out.println("Linear Search: Given number not found and Total Search Time "+linear_search_time+" Nano. seconds");
		else
		System.out.println("Linear Search: Given number found in array at location "+position+" and Total Search Time "+linear_search_time+" Nano. seconds");
	}
	public void recursiveBinarySearch(int num) { //function to perform binary search
		long start = System.nanoTime();
		Arrays.sort(input); //sort the array before doing recursive search
		long end = System.nanoTime();
		sort_time = (end - start) / 100;
		BinarySearch bs = new BinarySearch();
		start = System.nanoTime(); //get system start time befor binary search
		int position = bs.recursiveBinarySearch(input, num, 0, input.length-1); //now execute binary search
		end = System.nanoTime(); //get system end time
		binary_search_time = end - start; //calculate binary search execution time by deducting start time from end time
		System.out.println("Total Time for sorting the array : "+sort_time+" Nano.seconds");
		if (position == -1)
			System.out.println("Binary Search: Given number not found and Total Search Time " +binary_search_time+" Nano. seconds");
		else
		System.out.println("Binary Search: Given number found in array at location "+position+" and Total Search Time "+binary_search_time+" Nano. seconds");
	}

	public static void main(String args[]) {
		Driver driver = new Driver();
		String fileName = driver.getinputFileName();
		driver.loadInputArray();
		int num = driver.getSearchedNumber();
		driver.linearSearch(num);
		driver.recursiveBinarySearch(num);
	}
}