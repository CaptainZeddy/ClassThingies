package contador;

public class App {

	public static void main(String[] args) throws InterruptedException {

		Contador1000 contar= new Contador1000();
		Thread h1= new Thread(contar);
		h1.start();
		
		Thread.sleep(1000);
		h1.interrupt();
		
		
	}

}
