package parallelprogramming;

import java.util.Arrays;
import java.util.Random;

public class TestMergeSort {
	public static void main(String[] args) {
		int[] nums = new int[1000000];
		Random random = new Random();
		for(int i=0;i<nums.length;i++) {
			nums[i] = random.nextInt(1000000);
		}
//		System.out.println("before sorting");
//		Arrays.stream(nums).forEach(x->System.out.print(x+" "));
		int numOfThreads = Runtime.getRuntime().availableProcessors();
		int[] nums2 = Arrays.copyOf(nums, nums.length);

		long start = System.currentTimeMillis();
		MergeSort.mergeSort(nums, 0, nums.length-1);
		long end = System.currentTimeMillis();
		System.out.println("total time in sequential merge sort:"+(end-start)+" ms");
		
//		System.out.println("\nwith sequential merge sort");
//		Arrays.stream(nums).forEach(x->System.out.print(x+" "));
		
		start = System.currentTimeMillis();
//		ParallelMergeSort.parallelMergeSort(nums2, numOfThreads);
		ParallelMergeSort2.parallelMergeSort(nums2, 0, nums2.length-1, numOfThreads);
		end = System.currentTimeMillis();
		System.out.println("total time in parallel merge sort:"+(end-start)+" ms");
		
//		System.out.println("\nwith parallel merge sort");
//		Arrays.stream(nums2).forEach(x->System.out.print(x+" "));
	}
}
