package parallelprogramming;

public class ParallelMergeSort {
	public static void parallelMergeSort(int[] nums,int numOfThreads) {
		int low = 0,high = nums.length-1;
		parallelMergeSort(nums,low,high,numOfThreads);
	}
	// only last level sort will be in parallel
	private static Thread parallelMergeSort(int[] nums, int low, int high, int numOfThreads) {
		if(numOfThreads<=1) {
			Thread mergeSort = new MergeSort(nums,low,high);
			mergeSort.start();
			return mergeSort;
		}
		int mid = (low+high)/2;
		Thread ms1 = parallelMergeSort(nums,low,mid,numOfThreads/2);
		Thread ms2 = parallelMergeSort(nums,mid+1,high,numOfThreads/2);
		try {
			if(ms1!=null)
			ms1.join();
			if(ms2!=null)
			ms2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Merge.merge(nums,low,mid,high);
//		return Thread.currentThread();
		return null;
	}
}
