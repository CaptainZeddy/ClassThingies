package nombres;

public class Nombres extends Thread{

	@Override
	public void run() {
		System.out.println("hola: "+getName());
		System.out.println("Current thread: "+Thread.currentThread().toString());
	}
}
