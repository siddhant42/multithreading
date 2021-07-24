package concurrent2;

import java.util.concurrent.TimeUnit;

//Properly synchronized cooperative thread termination
/*public class StopThread {
	private static boolean stopRequested;
	private static synchronized void requestStop() {
		stopRequested = true;
	}
	private static synchronized boolean stopRequested() {
		return stopRequested;
	}
	public static void main(String[] args)
			throws InterruptedException {
		Thread backgroundThread = new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while (!stopRequested())
					i++;
			}
		});
		backgroundThread.start();
		TimeUnit.SECONDS.sleep(1);
		requestStop();
	}
}*/
//Cooperative thread termination with a volatile field
public class StopThread {
	// Broken - requires synchronization!

	private static volatile boolean stopRequested;
	public static void main(String[] args)
			throws InterruptedException {
		Thread backgroundThread = new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while (!stopRequested)
					i++;
			}
		});
		backgroundThread.start();
		TimeUnit.SECONDS.sleep(1);
		stopRequested = true;
	}
}
