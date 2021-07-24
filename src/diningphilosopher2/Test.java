package diningphilosopher2;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	private static final int max = 5;
	// will get into deadlock
	public static void methodDeadlock(Chopstick[] chopsticks,ExecutorService ex) {
		for(int i=0;i<max;i++) {
			Philosopher philosopher = new Philosopher(i,chopsticks[i],chopsticks[(i+1)%max]);
			ex.submit(philosopher);
		}
	}
	// removing cyclic dependency
	public static void removeDeadlock(Chopstick[] chopsticks,ExecutorService ex) {
		for(int i=0;i<max;i++) {
			Philosopher philosopher = null;
			if(i==max-1) {
				philosopher = new Philosopher(i,chopsticks[0],chopsticks[i]);
			} else {
				philosopher = new Philosopher(i,chopsticks[i],chopsticks[i+1]);
			}
			ex.submit(philosopher);
		}
	}
	public static void main(String[] args) throws IOException {
		ExecutorService ex = Executors.newFixedThreadPool(max);
		Chopstick[] chopsticks = new Chopstick[max];
		for(int i=0;i<max;i++) {
			chopsticks[i] = new Chopstick();
		}
		//methodDeadlock(chopsticks, ex);
		removeDeadlock(chopsticks, ex);
		System.out.println("Please press Enter to quit");
		System.in.read();
		ex.shutdownNow();
	}
}
