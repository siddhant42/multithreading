package thread;

public class Test3  {

	public static void main(String[] args) {
		Abc obj = new Abc();
		obj.start();
		synchronized(obj) {
			try {
				obj.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(obj.val);
		}
	}
}
class Abc extends Thread {
	int val;

	@Override
	public void run() {
		synchronized(this) {
			for(int i=1;i<=10;i++) {
				val+=i;
				notifyAll();
			}
		}
	}
}