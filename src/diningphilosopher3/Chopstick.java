package diningphilosopher3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
	private int id;
	private Lock lock;
	public Chopstick(int id) {
		this.id = id;
		this.lock = new ReentrantLock();
	}
	public void peekup(Philosopher philosopher,State state) {
		System.out.println(philosopher+" waiting for "+state.toString()+" "+this);
		lock.lock();
		System.out.println(philosopher+" picked up "+state.toString()+" "+this);
	}
	public void putdown(Philosopher philosopher,State state) {
		lock.unlock();
		System.out.println(philosopher+" put down "+state.toString()+" "+this);
	}
	public String toString() {
		return "chopstick-"+id;
	}
}
