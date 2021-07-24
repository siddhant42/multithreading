package concurrent3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrimeGenerator implements Runnable {
	private final List<BigInteger> primes
	= new ArrayList<BigInteger>();
	private volatile boolean cancelled;
	public void run() {
		BigInteger p = BigInteger.ONE;
		while (!cancelled) {
			p = p.nextProbablePrime();
			synchronized (this) {
				primes.add(p);
			}
		}
		//System.out.println("run method runned by "+Thread.currentThread().getName());
	}
	public void cancel() {
		cancelled = true; 
	//System.out.println("cancelled by "+Thread.currentThread().getName());
	}
	public synchronized List<BigInteger> get() {
		return new ArrayList<BigInteger>(primes);
	}
	static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
		PrimeGenerator generator = new PrimeGenerator();
		new Thread(generator).start();
		try {
			Thread.sleep(1000);
		} finally {
			generator.cancel();
		}
		return generator.get();
	}
	public static void main(String[] args) throws InterruptedException {
		
		List<BigInteger> list = aSecondOfPrimes();
		for(BigInteger b:list) {
			System.out.println(b);
		}
	}
}
