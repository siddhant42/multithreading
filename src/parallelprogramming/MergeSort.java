package parallelprogramming;

public class MergeSort extends Thread {
	private int low,high;
	private int[] nums;
	public MergeSort() {}
	public MergeSort(int[] nums,int low,int high) {
		this.nums = nums;
		this.low = low;
		this.high = high;
	}
	public static void mergeSort(int[] nums,int low,int high) {
		if(low>=high) return;
		int mid = (low+high)/2;
		mergeSort(nums,low,mid);
		mergeSort(nums,mid+1,high);
		Merge.merge(nums, low, mid, high);
	}
	@Override 
	public void run() {
		mergeSort(nums,low,high);
	}
}
