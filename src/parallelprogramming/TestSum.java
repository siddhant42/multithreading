package parallelprogramming;

import java.util.Random;

public class TestSum {
	public static void main(String[] args) {
		int[] nums = new int[100000000];
		Random random = new Random();
		for(int i=0;i<nums.length;i++) {
			nums[i] = random.nextInt(100);
		}
		SequentialSum ss = new SequentialSum();
		int numOfThreads = Runtime.getRuntime().availableProcessors();
		ParallelSum2 ps = new ParallelSum2(numOfThreads);
		long starttime = System.currentTimeMillis();
		long seqsum = ss.sum(nums);
		System.out.println("sequential sum:"+seqsum);
		long endtime = System.currentTimeMillis();
		System.out.println("process time for sequential sum:"+(endtime-starttime)+" ms");
		starttime = System.currentTimeMillis();
		long parsum = ps.sum(nums);
		System.out.println("Parallel sum:"+parsum);
		endtime = System.currentTimeMillis();
		System.out.println("process time for parallelsum:"+(endtime-starttime)+" ms");
	}
}
