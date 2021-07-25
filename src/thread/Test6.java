package thread;
class A12{
	synchronized void m1(){
		for(int i=0;i<=1000;i++)
			System.out.println(i);
	}
	static synchronized void m2(){
		for(int i=1000;i<=2000;i++)
			System.out.println(i);
	}
}
public class Test6 {
	public static void main(String[] args) {
		final A12 obj = new A12();
		new Thread(){
			public void run(){
				obj.m1();
			}
		}.start();
		new Thread(){
			public void run(){
				A12.m2();
			}
		}.start();
	}
}
