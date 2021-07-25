package parallelprogramming;

public class ParallelMergeSort2 {
	// better parallel merge sort than previous one
	public static void parallelMergeSort(int[] nums,int low,int high,int numOfThreads) {
		if(low>=high) return;
		else if(numOfThreads<=1) {
			MergeSort.mergeSort(nums, low, high);
			return;
		}
		int mid = (low+high)/2;
		Thread t1 = mergeSortThread(nums,low,mid,numOfThreads);
		Thread t2 = mergeSortThread(nums,mid+1,high,numOfThreads);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		Merge.merge(nums, low, mid, high);
	}
	private static Thread mergeSortThread(int[] nums,int low,int high,int numOfThreads) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				parallelMergeSort(nums,low,high,numOfThreads/2);
			}
		};
		return thread;
	}
}
