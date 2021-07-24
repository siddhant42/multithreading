package concurrent2;

import java.util.concurrent.SynchronousQueue;

class Producer implements Runnable {
	private final SynchronousQueue<Integer> sharedQueue;
	private final int size;
	Producer(SynchronousQueue<Integer> sharedQueue,int size) {
		this.sharedQueue = sharedQueue;
		this.size = size;
	}
	@Override
	public void run() {
		try{
			for(int i=0;i<size;i++) {
				sharedQueue.put(i);
				System.out.println(Thread.currentThread().getName()+" Produced "+i);
			}
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
class Consumer implements Runnable {
	private final SynchronousQueue<Integer> sharedQueue;
	private final int size;
	Consumer(SynchronousQueue<Integer> sharedQueue,int size) {
		this.sharedQueue = sharedQueue;
		this.size = size;
	}
	@Override
	public void run() {
		try{
			for(int i=0;i<size;i++) {
				int p = sharedQueue.take();
				System.out.println(Thread.currentThread().getName()+" Consumed "+p);
			}
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
public class SynchronousQExample {
	public static void main(String[] args) {
		SynchronousQueue<Integer> sharedQueue = new SynchronousQueue<>(true);
		int size = 10;
		Thread t1 = new Thread(new Producer(sharedQueue,size),"Producer");
		Thread t2 = new Thread(new Consumer(sharedQueue,size),"Consumer");
		t1.start();
		t2.start();
	}
}
