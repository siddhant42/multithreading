package thread;
class PrintOdd implements Runnable {
	private final String resource;

	public PrintOdd(String resource) {
		super();
		this.resource = resource;
	}

	@Override
	public void run() {
		synchronized(resource){
			for(int i=1;i<=20;i+=2){
				final String name = Thread.currentThread().getName();
				System.out.println(name+" "+i);
				try {
					resource.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				resource.notifyAll();
			}
		}
	}
}

class PrintEven implements Runnable{
	private final String resource;

	public PrintEven(String resource) {
		super();
		this.resource = resource;
	}

	@Override
	public void run() {
		synchronized(resource){
		for(int i=2;i<=20;i+=2){
				final String name = Thread.currentThread().getName();
				System.out.println(name+i);
				resource.notifyAll();
				try {
					resource.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
public class EvenOddTest2 {

	public static void main(String[] args) {
		final String resource = "resourceA";
		PrintOdd odd1 = new PrintOdd(resource);
		PrintEven even1 = new PrintEven(resource);
		Thread t1 = new Thread(odd1,"odd: ");
		Thread t2 = new Thread(even1,"even: ");
		t1.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
	}
}
