package parallelprogramming;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ParallelProcess implements Callable<Long> {
	private int low,high;
	private int[] nums;
	public ParallelProcess(int low,int high,int[] nums) {
		this.low = low;
		this.high = high;
		this.nums = nums;
	}

	@Override
	public Long call() throws Exception {
		long sum = 0;
		for(int i=low;i<high;i++) {
			sum = sum+ nums[i];
		}
		return sum;
	}
	
}
public class ParallelSum {
	private int numOfThreads;
	private ExecutorService ex;
	public ParallelSum(int numOfThreads) {
		this.numOfThreads = numOfThreads;
		ex = Executors.newFixedThreadPool(numOfThreads);
	}
	public long sum(int[] nums) {
		long sum = 0;
		int n1 = nums.length/numOfThreads;
		for(int i=0;i<numOfThreads;i++) {
			ParallelProcess pp = new ParallelProcess(i*n1,(i+1)*n1,nums);
			Future<Long> future = ex.submit(pp);
			try {
				sum = sum+ future.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		ex.shutdown();
		return sum;
	}
}
