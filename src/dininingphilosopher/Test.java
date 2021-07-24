package dininingphilosopher;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import diningphilosopher2.Chopstick;
import diningphilosopher2.Philosopher;

public class Test {
	private static final int max = 5;
	public static void removeDeadlock(Chopstick[] chopsticks,ExecutorService ex) {
		for(int i=0;i<max;i++) {
			Philosopher philosopher  = new Philosopher(i,chopsticks[i],chopsticks[(i+1)%max]);
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
