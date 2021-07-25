package thread;

import java.util.concurrent.CountDownLatch;
/**
 * Implementation for CountDownLatch-
 * CountDownLatch is used to make sure that a task 
 * waits for other threads before it starts.
 * @author kumarsid
 *
 */
public class CountDownLatchDemo {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);
		Worker worker1 = new Worker(1000,latch,"worker-1");
		Worker worker2 = new Worker(2000,latch,"worker-2");
		Worker worker3 = new Worker(3000,latch,"worker-3");
		Worker worker4 = new Worker(3000,latch,"worker-4");
		worker1.start();
		worker2.start();
		worker3.start();
		worker4.start();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" finished");
	}
}
class Worker extends Thread {
	private final int delay;
	private final CountDownLatch latch;
	public Worker(int delay, CountDownLatch latch,String name) {
		super(name);
		this.delay = delay;
		this.latch = latch;
	}
	@Override
	public void run(){
		try{
			Thread.sleep(delay);
			latch.countDown();
			System.out.println(Thread.currentThread().getName()+" finished");
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}