package thread;

public class Test2 implements Runnable {
	int i;
	@Override
	public void run() {
		synchronized(Test2.class) {
			for(i=0;i<=100;i++){
				System.out.print(i+" ");
				//		System.out.println(this);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		Test2 obj = new Test2();
		Test2 obj2 = new Test2();
		Thread t1 = new Thread(obj);
		Thread t2 = new Thread(obj2);
		t1.start();
		t2.start();
	}
}
