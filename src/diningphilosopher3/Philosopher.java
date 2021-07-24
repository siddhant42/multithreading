package diningphilosopher3;

import java.util.Random;

public class Philosopher implements Runnable {
	private int id;
	private Chopstick left,right;
	private boolean full;
	private int eatCounter;
	private static Random random = new Random();
	public Philosopher(int id,Chopstick left,Chopstick right) {
		this.id = id;
		this.left = left;
		this.right = right;
	}
	@Override
	public void run() {
		try {
			while(!full) {
				think();
				left.peekup(this, State.LEFT);
				Thread.sleep(random.nextInt(1000));
				right.peekup(this, State.RIGHT);
				eat();
				right.putdown(this, State.RIGHT);
				left.putdown(this, State.LEFT);
			}
		} catch(InterruptedException ex) {
			System.out.println(this+" got interrupted");
		}
	}
	private void eat() throws InterruptedException {
		System.out.println(this+" eating");
		eatCounter++;
		Thread.sleep(random.nextInt(1000));
	}
	private void think() throws InterruptedException {
		System.out.println(this+" thinking");
		Thread.sleep(random.nextInt(1000));
	}
	
	public boolean isFull() {
		return full;
	}
	public void setFull(boolean full) {
		this.full = full;
	}
	public int getEatCounter() {
		return eatCounter;
	}
	public void setEatCounter(int eatCounter) {
		this.eatCounter = eatCounter;
	}
	public String toString() {
		return "Philosopher-"+id;
	}
}
