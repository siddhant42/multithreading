package diningphilosopher2;

import java.util.Random;

public class Philosopher implements Runnable {
	private int id;
	Chopstick left,right;
	public Philosopher(int id,Chopstick left,Chopstick right) {
		this.id = id;
		this.left = left;
		this.right = right;
	}
	public void pause() throws InterruptedException {
		Random random = new Random();
		Thread.sleep(random.nextInt(1000));
	}
//	public void eat() throws InterruptedException {
//		System.out.println("philosopher:"+id+" eating");
//		Random random = new Random();
//		Thread.sleep(100*random.nextInt(30));
//	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				System.out.println(this+" thinking");
				pause();
				System.out.println(this+" waiting left");
				left.take();
				System.out.println(this+" taken left");
				pause();
				System.out.println(this+" waiting right");
				right.take();
				System.out.println(this+" taken right");
				System.out.println(this+" eating");
				pause();
				right.drop();
				System.out.println(this+" dropped right");
				left.drop();
				System.out.println(this+" dropped left");
			}
		} catch(InterruptedException ex) {
			System.out.println(this +" exiting via interrupt");
		}
	}
	@Override
	public String toString() {
		return "Philosopher-" + id;
	}
	
}
