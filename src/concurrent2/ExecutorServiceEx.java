package concurrent2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * This class demonstrate executorService Implementation
 * using thread pools with fixed no of threads and 
 * new cached thread pool which creates a new thread for 
 * every task if necessary.
 * @author kumarsid
 *
 */
public class ExecutorServiceEx {
	// nTasks will be executed using-
	// maximum of nThreads in case of newFixedThreadPool
	// maximum of tTasks no of threads will be used in case of newCachedThreadPool
	private static final int nThreads = 5;
	private static final int tTasks = 30;
	private static final ExecutorService ex = Executors.newFixedThreadPool(nThreads);
			//Executors.newCachedThreadPool();
	public static void main(String[] args) throws Exception {
		for(int i=0;i<tTasks;i++) {
			int p = i;
			Runnable task = new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+" running task "+p);
					try {
						System.out.println(Thread.currentThread().getName()+" in sleep");
						Thread.sleep(1000);
						//ex.shutdownNow();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			ex.submit(task);
		}
		// JVM will not stop untill we shutdown the executorService
		ex.shutdown();
		System.out.println("service shutdown is called "+ex.isShutdown());
		ex.awaitTermination(5000,TimeUnit.MILLISECONDS);
		System.out.println("Is service terminated? "+ex.isTerminated());
	}
}
/*
 * How can a thread can be reused after completing its task?
 * final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<Runnable>(nThreads);
	final Semaphore s = new Semaphore(nThreads);
public void submit(Runnable runs) {
    tasks.add(runs);
    // tasks.put(runs) in case of fixed thread pool
}

volatile boolean running = true;

// running in each thread in the pool
class RunsRunnable implement Runnable {
    public void run() {
    try{
    	s.aquire();
    // running will be false after shutdown of executorService
        while(!exec.isShutdown) {
           Runnable runs = tasks.take();
           try {
              runs.run();
           } catch(Throwable t) {
              // handles t
           }
        }
    }catch(Exception e){}
    finally{ s.release(); }
    }
 }*/
