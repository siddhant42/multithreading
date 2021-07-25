package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

public class ProducerConsumerPattern {

	public static void main(String args[]){

		//Creating shared object
		BlockingQueue<Integer> sharedQueue = new LinkedTransferQueue<Integer>();

		//Creating Producer and Consumer Thread
		Thread prodThread = new Thread(new Producer2(sharedQueue));
		Thread consThread = new Thread(new Consumer2(sharedQueue));

		//Starting producer and Consumer thread
		prodThread.start();
		consThread.start();
	}

}

//Producer Class in java
class Producer2 implements Runnable {

	private final BlockingQueue<Integer> sharedQueue;

	public Producer2(BlockingQueue<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<10; i++){
			try {
				
				System.out.println("Produced: " + i);
				sharedQueue.put(i);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

}

//Consumer Class in Java
class Consumer2 implements Runnable{

	private final BlockingQueue<Integer> sharedQueue;

	public Consumer2 (BlockingQueue<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		while(true){
			try {
				System.out.println("Consumed: "+ sharedQueue.take());
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}


}


