package parallelprogramming;

import java.util.stream.IntStream;

public class ParallelStreamExample {
	public static boolean isPrime(int num) {
		if(num<=1) return false;
		if(num==2) return true;
		if(num%2==0) return false;
		int max = (int) Math.sqrt(num);
		for(int i=3;i<=max;i+=2) 
			if(num%i==0) return false;
		return true;
	}
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long numOfPrimes = IntStream.rangeClosed(2, Integer.MAX_VALUE/100)
				.filter(ParallelStreamExample::isPrime).count();
		System.out.println("number of primes using sequential:"+numOfPrimes);
		long end = System.currentTimeMillis();
		System.out.println("time taken for primes using sequential:"+(end-start));
		
		start = System.currentTimeMillis();
		numOfPrimes = IntStream.rangeClosed(2, Integer.MAX_VALUE/100).parallel()
				.filter(ParallelStreamExample::isPrime).count();
		System.out.println("number of primes using parallel:"+numOfPrimes);
		end = System.currentTimeMillis();
		System.out.println("time taken for primes using parallel:"+(end-start));
	}
}
