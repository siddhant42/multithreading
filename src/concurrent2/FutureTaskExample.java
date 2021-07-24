package concurrent2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask is used to do some task which you want to get/use in future
 * @author skkumar
 *
 */
public class FutureTaskExample {
	// Callable in which actual task is there
	static	class MyCallable implements Callable<String> {

		private long waitTime;

		public MyCallable(int timeInMillis){
			this.waitTime=timeInMillis;
		}
		// task which needs to be performed
		@Override
		public String call() throws Exception {
			// do some long running task here
			Thread.sleep(waitTime);
			//return the thread name executing this callable task
			return Thread.currentThread().getName();
		}

	}
	// FutureTask in which task is passed to perform
	FutureTask<String> futureTask = new FutureTask<>(new MyCallable(1000));
	
	// thread in which task will run, although here we can also use
	// executor service if this is a big task and needs to be performed
	// using multi threads
	final Thread thread = new Thread(futureTask);
	
	//final ExecutorService ex = Executors.newFixedThreadPool(3);
	
	// call this method to start the thread to run the task
	public void start(){
		thread.start();
		
		//ex.execute(futureTask);
	}
	// call this method in future to get the result of the performed task 
	public String get(){
		String result = null;
		try {
			result =  futureTask.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		/*finally{
			ex.shutdown();
		}*/
		return result;
	}
	public static void main(String[] args) throws Exception {
		FutureTaskExample f = new FutureTaskExample();
		f.start();
		// do some other work and after that you can get the 
		// task done by your future task
		Thread.sleep(1000);
		String result = f.get();
		System.out.println(result);
	}
}
