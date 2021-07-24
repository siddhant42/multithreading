package concurrent3;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

 interface CancellableTask<T> extends Callable<T> {
	void cancel();
	RunnableFuture<T> newTask();
}
//@ThreadSafe
public class CancellingExecutor extends ThreadPoolExecutor {
	public CancellingExecutor(int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue){
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	protected<T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
		if (callable instanceof CancellableTask)
			return ((CancellableTask<T>) callable).newTask();
		else
			return super.newTaskFor(callable);
	}
}
abstract class SocketUsingTask<T> implements CancellableTask<T> {
	//@GuardedBy("this")
	private Socket socket;
	protected synchronized void setSocket(Socket s) { socket = s; }
	public synchronized void cancel() {
		try {
			if (socket != null)
				socket.close();
		} catch (IOException ignored) { }
	}
	public RunnableFuture<T> newTask() {
		return new FutureTask<T>(this) {
			public boolean cancel(boolean mayInterruptIfRunning) {
				try {
					SocketUsingTask.this.cancel();
				} finally {
					return super.cancel(mayInterruptIfRunning);
				}
			}
		};
	}
}
