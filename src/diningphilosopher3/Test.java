package diningphilosopher3;

import static diningphilosopher3.Constants.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
	// lockdown due to cyclic dependency
	private static void cyclicDeadlock(ExecutorService ex, Chopstick[] chopsticks, Philosopher[] philosophers) {
		for(int i=0;i<MAX_THREADS;i++) {
			philosophers[i] = new Philosopher(i,chopsticks[i],chopsticks[(i+1)%MAX_THREADS]);
			ex.submit(philosophers[i]);
		}
	}
	// remove cyclic dependency to avoid deadlock
	private static void uncyclicDeadlock(ExecutorService ex, Chopstick[] chopsticks, Philosopher[] philosophers) {
		for(int i=0;i<MAX_THREADS;i++) {
			Chopstick left = null;
			Chopstick right = null;
			if(i==MAX_THREADS-1) {
				left = chopsticks[0];
				right = chopsticks[i];
			} else {
				left = chopsticks[i];
				right = chopsticks[i+1];
			}
			philosophers[i] = new Philosopher(i,left,right);
			ex.submit(philosophers[i]);
		}
	}
	public static void main(String[] args) throws InterruptedException {
		ExecutorService ex = Executors.newFixedThreadPool(MAX_THREADS);
		Chopstick[] chopsticks = new Chopstick[MAX_RESOURCES];
		for(int i=0;i<MAX_RESOURCES;i++) {
			chopsticks[i] = new Chopstick(i);
		}
		Philosopher[] philosophers = new Philosopher[MAX_THREADS];
		uncyclicDeadlock(ex, chopsticks, philosophers);
		Thread.sleep(MAX_SIMULATION_TIME);
		for(Philosopher philosopher: philosophers) {
			philosopher.setFull(true);
		}
		ex.shutdown();
		while(!ex.isTerminated())
			ex.awaitTermination(1, TimeUnit.SECONDS);
		for(Philosopher philosopher: philosophers) {
			System.out.println(philosopher+" eaten "+philosopher.getEatCounter()+" times.");
		}
	}


}
