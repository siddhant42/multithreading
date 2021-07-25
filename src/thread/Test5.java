package thread;

public class Test5 extends Thread {
	public void start() {
		System.out.println("in start method");
	}
	public void run() {
		System.out.println("in run method");
	}
	public static void main(String[] args) {
		Test5 obj = new Test5();
		obj.start();
	}
}
