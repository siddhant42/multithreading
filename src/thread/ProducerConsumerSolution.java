package thread;

import java.util.Vector;

public class ProducerConsumerSolution {
public static void main(String[] args) {
	Vector<Integer> sharedQueue = new Vector<>();
	int size = 4;
	Thread t1 = new Thread(new Producer(sharedQueue,size),"Producer");
	Thread t2 = new Thread(new Consumer(sharedQueue,size),"Consumer");
	t1.start();
	t2.start();
}
}
class Producer implements Runnable {
	private final Vector<Integer> sharedQueue;
	private final int size;
	public Producer(Vector<Integer> sharedQueue, int size) {
		super();
		this.sharedQueue = sharedQueue;
		this.size = size;
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println("produced "+i);
			produce(i);
		}
	}
	private void produce(int i) {
			while(sharedQueue.size()==size){
				synchronized(sharedQueue){
				System.out.println(Thread.currentThread().getName()+" "
						+ "is full with size "+sharedQueue.size());
				try {
					sharedQueue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
			synchronized(sharedQueue){
				sharedQueue.add(i);
				sharedQueue.notifyAll();
			}
	}
	
}
class Consumer implements Runnable {
	private final Vector<Integer> sharedQueue;
	private final int size;
	public Consumer(Vector<Integer> sharedQueue, int size) {
		super();
		this.sharedQueue = sharedQueue;
		this.size = size;
	}
	@Override
	public void run() {
		while(true){
			System.out.println("consumed "+consume());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	private Integer consume() {
		while(sharedQueue.isEmpty()){
			synchronized(sharedQueue){
				System.out.println(Thread.currentThread().getName()+
						" is empty with size "+sharedQueue.size());
				try {
					sharedQueue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		synchronized(sharedQueue){
			sharedQueue.notifyAll();
			return (Integer)sharedQueue.remove(0);
		}
	}
	
}