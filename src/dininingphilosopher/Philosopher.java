package dininingphilosopher;

import java.util.Random;

public class Philosopher implements Runnable {
	private int id;
	private Chopstick left,right;
	private final Random random = new Random();
	
	public Philosopher(int id,Chopstick left, Chopstick right) {
		this.id = id;
		this.left = left;
		this.right =right;
	}
//	public void eat() throws InterruptedException {
//		System.out.println(this+" waiting left");
//		if(left.getLock().tryLock()) {
//			System.out.println(this+" taken left");
//			System.out.println(this+" waiting right");
//			Thread.sleep(random.nextInt(1000));
//			if(right.getLock().tryLock()) {
//				System.out.println(this+" right taken right");
//				System.out.println(this+" eating");
//				Thread.sleep(random.nextInt(1000));
//				if(right.getLock().isLocked()) {
//					right.getLock().unlock();
//					System.out.println(this+" dropping right");
//				}
//			}
//			if(left.getLock().isLocked()) {
//				left.getLock().unlock();
//				System.out.println(this+" left dropping left");	
//			} 
//
//		}
//	}
	public void think() throws InterruptedException {
		System.out.println(this+" is thinking");
		Thread.sleep(random.nextInt(1000));
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				think();
				System.out.println(this+" waiting left");
				while(true) {
					if(left.getLock().tryLock()) {
						System.out.println(this+" taken left");
						System.out.println(this+" waiting right");
						Thread.sleep(random.nextInt(1000));
						if(right.getLock().tryLock()) {
							System.out.println(this+" taken right");
							System.out.println(this+" eating");
							Thread.sleep(random.nextInt(1000));
						} else if(left.getLock().isLocked()){
							System.out.println(this+" not got right");
							System.out.println(this+" drop left");
							left.getLock().unlock();
						} 
						if(right.getLock().isLocked()) {
							right.getLock().unlock();
							System.out.println(this+" dropping right");
						}
					}
					if(left.getLock().isLocked()) {
						left.getLock().unlock();
						System.out.println(this+" dropping left");
						break;
					}
					else {
						System.out.println(this+" trying left again");
						Thread.sleep(random.nextInt(1000));
					}
				}
				
			}
		}catch(InterruptedException ex) {
			System.out.println(this+" interrupted");
		}
		
	}
	@Override
	public String toString() {
		return "Philosopher-"+id;
	}

	
}
