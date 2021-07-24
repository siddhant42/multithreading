package diningphilosopher2;

public class Chopstick {
	private boolean taken = false;
	public synchronized void take() throws InterruptedException {
		while(taken) {
			wait();
		}
		this.taken = true;
	}
	public synchronized void drop() {
		this.taken = false;
		notifyAll();
	}
}
