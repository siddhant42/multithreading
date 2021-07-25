package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolEx {
	private static final ExecutorService ex = Executors.newCachedThreadPool();
	public static void main(String[] args) {
		for(int i=0;i<25;i++) {
			Runnable task = new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+" running ");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			};
			ex.submit(task);
		}
		ex.shutdown();
	}
}
