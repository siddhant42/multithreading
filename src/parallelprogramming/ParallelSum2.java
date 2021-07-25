package parallelprogramming;

class ParallelProcess2 extends Thread {
	private int low,high;
	private int[] nums;
	private long partialsum;
	public ParallelProcess2(int low,int high,int[] nums) {
		this.low = low;
		this.high = high;
		this.nums = nums;
	} 
	@Override
	public void run() {
		partialsum = 0l;
		for(int i=low;i<high;i++) {
			partialsum = partialsum + nums[i];
		}
	}
	public long getPartialsum() {
		return partialsum;
	}
	
}
public class ParallelSum2 {
	private int numOfThreads;
	public ParallelSum2(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}
	public long sum(int[] nums) {
		int n1 = nums.length/numOfThreads;
		ParallelProcess2[] threads = new ParallelProcess2[numOfThreads];
		for(int i=0;i<numOfThreads;i++) {
			threads[i] = new ParallelProcess2(i*n1,(i+1)*n1,nums);
			threads[i].start();
		}
		try {
			for(int i=0;i<numOfThreads;i++) {
				threads[i].join();
			}
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		long sum = 0;
		for(int i=0;i<numOfThreads;i++) {
			sum = sum+threads[i].getPartialsum();
		}
		return sum;
	}
}
