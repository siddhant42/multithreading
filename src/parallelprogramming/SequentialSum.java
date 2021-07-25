package parallelprogramming;

public class SequentialSum {
	public long sum(int[] nums) {
		long sum = 0;
		for(int i=0;i<nums.length;i++) {
			sum = sum+ nums[i];
		}
		return sum;
	}
}
