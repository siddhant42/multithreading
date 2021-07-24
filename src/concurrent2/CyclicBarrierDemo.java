package concurrent2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Implementation for CyclicBarrier-
 * CyclicBarrier is used when multiple thread carry 
 * out different sub tasks and the output of these 
 * sub tasks need to be combined to form the final output
 * @author kumarsid
 *
 */
class Computation1 implements Runnable {
	public static int product = 0;
	@Override
	public void run() {
		product = 2*3;
		try {
			CyclicBarrierDemo.newBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
}
class Computation2 implements Runnable {
	public static int sum = 0;
	@Override
	public void run() {
		// check if newBarrier is broken or not
        System.out.println("Is the barrier broken? - " + CyclicBarrierDemo.newBarrier.isBroken());
		sum = 4*5;
		try {
			CyclicBarrierDemo.newBarrier.await(3000, TimeUnit.MILLISECONDS);
			// number of parties waiting at the barrier
            System.out.println("Number of parties waiting at the barrier "+
            "at this point = " + CyclicBarrierDemo.newBarrier.getNumberWaiting());
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
	
}
public class CyclicBarrierDemo implements Runnable {
	public static CyclicBarrier newBarrier = new CyclicBarrier(3);

	@Override
	public void run() {
		System.out.println("Number of parties required to trip the barrier = "+
		        newBarrier.getParties());
		System.out.println("Sum of product and sum = " + (Computation1.product + 
		        Computation2.sum));
		Computation2 comp1 = new Computation2();
		Computation2 comp2 = new Computation2();
		Thread t1 = new Thread(comp1);
		Thread t2 = new Thread(comp2);
		t1.start();
		t2.start();
		try {
			newBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		 // barrier breaks as the number of thread waiting for the barrier
        // at this point = 3
//        System.out.println("Sum of product and sum = " + (Computation1.product + 
//        Computation2.sum));
                 
        // Resetting the newBarrier
        newBarrier.reset();
        System.out.println("Barrier reset successful");
        System.out.println(newBarrier.getNumberWaiting());
	}
	public static void main(String[] args) {
		Thread t1 = new Thread(new CyclicBarrierDemo());
		t1.start();
	}
}
