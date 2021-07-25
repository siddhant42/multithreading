package thread;

class PrintEvenOdd{
	public void printOdd(){
		synchronized(this){
			for(int i=1;i<=20;i+=2){
				System.out.print(i+" ");
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.notifyAll();
			}
		}}
	public void printEven(){
		synchronized(this){
			for(int i=2;i<=20;i+=2){
				System.out.print(i+" ");
				this.notifyAll();
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}}
public class EvenOddTest {
	public static void main(String[] args) {


		final PrintEvenOdd obj = new PrintEvenOdd();
		new Thread(){
			public void run(){
				obj.printOdd();
			}
		}.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(){
			public void run(){
				obj.printEven();
			}
		}.start();
	}
}