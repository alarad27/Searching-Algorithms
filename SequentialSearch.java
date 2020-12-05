// Author: Alara Dasdan
// Date: Jul 2020

package searching;

import java.util.Random;

// searching using sequential search algorithm
public class SequentialSearch {
	static int seed = 12345; // original RandomArray function
	static int sz = 10;
	
	// generate random int in a given range
	public static int randInt(Random rd, int min, int max) {
		return min + rd.nextInt(max - min + 1);
	}

	// generate random num in a given range
	public static double randDouble(Random rd, double min, double max) {
		return min + rd.nextDouble() * (max - min + 1);
	}
	
	// generate array of random numbers of size sz
	public static double[] getRandNumbers(int sz) {
		Random rd = new Random(seed);
		double[] arr = new double[sz];  // array of random numbers

		// generate random numbers
		for (int i = 0; i < arr.length; i++) {
			double r = (int) (10 *  randDouble(rd, 0, 10));
			arr[i] = r;
		}

		return arr;
	}
	
	// print input array
	public static void printNums(double[] arr) {
		System.out.format("print array of size %d:\n", arr.length);
		String sep = "";
		for (int i = 0; i < arr.length; i++) {
			System.out.format(sep + "%.2f", arr[i]);
			sep = ", ";
		}
		System.out.println("\n");
	}
	
	// swap arr[x] with arr[y]
	private static void swap(double[] arr, int x, int y) {
		double tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}
	
	// randomized pivot selection
	private static void getPivot(double[] arr, int l, int r) {
		Random rd = new Random(seed);
		int pi = randInt(rd, l, r);  // pivot index
		swap(arr, l, pi);
	}
	
	// partition arr[l..r] around a random pivot
	public static int partition(double [] arr, int l, int r, boolean isAscending) {
		getPivot(arr, l, r);
		int pi = l;  // pivot index
		double p = arr[pi]; // pivot
		int li = l;  // left index to move to right
		int ri = r;  // right index to move to left
		if (isAscending) {
			while (li < ri) {
				while ((li <= r) && (arr[li] <= p)) {
					li++;
				}
				while ((l <= ri) && (p < arr[ri])) {
					ri--;
				}
				if (li < ri) {
					swap(arr, li, ri);
				}
			}
		} else {
			while (li < ri) {
				while ((li <= r) && (arr[li] >= p)) {
					li++;
				}
				while ((l <= ri) && (p > arr[ri])) {
					ri--;
				}
				if (li < ri) {
					swap(arr, li, ri);
				}
			}
		}
		if (pi < ri) {
			swap(arr, pi, ri);
		}
		return ri;
	}
	
	// sort input array arr[l..r] using quicksort
	public static void sortRecur(double[] arr, int l, int r, boolean isAscending) {
		if (l >= r) {
			return;
		}
		int pi = partition(arr, l, r, isAscending);
		sortRecur(arr, l, pi - 1, isAscending);
		sortRecur(arr, pi + 1, r, isAscending);
	}
		
	// sort input array into a new array and return it
	public static double[] sort(double[] arr, boolean isAscending) {
		double[] arr2 = arr;
		sortRecur(arr2, 0, arr.length - 1, isAscending);
		return arr2;
	}
	
	// return a random number from arr
	public static double getNum(double[] arr) {
		Random rd = new Random(2 * seed);
		int inx = randInt(rd, 0, arr.length - 1);
		return arr[inx];
	}
	
	// search for x in arr using binary search
	public static boolean seqSearch(double x, double[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == x) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		boolean isAscending = true;
		double[] arr = getRandNumbers(sz); 
		printNums(arr);
		
		double[] arr2 = sort(arr, isAscending);
		printNums(arr2);
		
		double num = getNum(arr2);
		boolean isFound = seqSearch(num, arr2);
		System.out.println("Search result for " + num + " is: " + isFound);
		isFound = seqSearch(12345, arr2);
		System.out.println("Search result for 12345 is: " + isFound);
	}
}
