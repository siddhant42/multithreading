package parallelprogramming;

public class Merge {
	public static void merge(int[] nums,int low,int mid,int high) {
		int[] tmp = new int[high-low+1];
		int i=low,j=mid+1,k=0;
		while(i<=mid && j<=high) {
			if(nums[i]<nums[j]) {
				tmp[k++] = nums[i++];
			} else {
				tmp[k++] = nums[j++];
			}
		}
		while(i<=mid) {
			tmp[k++] = nums[i++];
		}
		while(j<=high) {
			tmp[k++] = nums[j++];
		}
		k=0;
		i=low;
		while(i<=high) {
			nums[i++] = tmp[k++];
		}
	}
}
