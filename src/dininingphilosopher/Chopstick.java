package dininingphilosopher;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
//	private static final long serialVersionUID = 1L;
//	private int id;
//	public Chopstick(int id) {
//		this.id = id;
//	}
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
	boolean taken = false;
	private ReentrantLock lock = new ReentrantLock();
	public void take() {
		taken = true;
	}
	public void drop() {
		taken = false;
	}
	public ReentrantLock getLock() {
		return lock;
	}
	
}
