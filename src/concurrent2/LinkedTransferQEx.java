package concurrent2;

import java.util.concurrent.LinkedTransferQueue;

class Producer1 implements Runnable {
	private final LinkedTransferQueue<Integer> sharedQueue;
	private final int size;
	Producer1(LinkedTransferQueue<Integer> sharedQueue,int size) {
		this.sharedQueue = sharedQueue;
		this.size = size;
	}
	@Override
	public void run() {
		try{
			for(int i=0;i<size;i++) {
				sharedQueue.transfer(i);
				System.out.println(Thread.currentThread().getName()+" produced "+i);
			}
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
class Consumer1 implements Runnable {
	private final LinkedTransferQueue<Integer> sharedQueue;
	private final int size;
	Consumer1(LinkedTransferQueue<Integer> sharedQueue,int size) {
		this.sharedQueue = sharedQueue;
		this.size = size;
	}
	@Override
	public void run() {
		try{
		for(int i=0;i<size;i++) {
			int c = sharedQueue.take();
			System.out.println(Thread.currentThread().getName()+" consumed "+c);
		}
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}	
	}
	
}
public class LinkedTransferQEx {
	public static void main(String[] args) {
		LinkedTransferQueue<Integer> sharedQueue = new LinkedTransferQueue<>();
		int size = 10;
		Thread t1 = new Thread(new Producer1(sharedQueue,size),"Producer");
		Thread t2 = new Thread(new Consumer1(sharedQueue,size),"Consumer");
		t1.start();
		t2.start();
	}
}
