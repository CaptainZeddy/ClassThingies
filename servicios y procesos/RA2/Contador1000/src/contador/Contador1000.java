package contador;

public class Contador1000 implements Runnable{

	@Override
	public void run() {

		for (int i=0; i<=1000;i++) {
			System.out.println(i);

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
