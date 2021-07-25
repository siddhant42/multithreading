package thread;
class A implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<100;i++){
			System.out.print(i+" ");
			
		}
		System.out.println(Thread.currentThread().getName()+" exited");
		return;
	}

}
class B implements Runnable{

	@Override
	public void run() {
		for(int i=100;i<200;i++){
			System.out.print(i+" ");

		}
		System.out.println(Thread.currentThread().getName()+" exited");
		return;
	}

}
public class Test {
	public static void main(String[] args) {
		//final A obj = new A();
		Thread t1 = new Thread(new A());
		Thread t2 = new Thread(new B());
		t1.setName("thread1");
		t2.setName("thread2");
		t1.start();
		t2.start();
	}
}
