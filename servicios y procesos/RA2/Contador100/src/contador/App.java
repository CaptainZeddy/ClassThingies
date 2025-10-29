package contador;

public class App {

	public static void main(String[] args) throws InterruptedException {

		Contador100 contar= new Contador100();
		Thread h1= new Thread(contar);
		h1.start();
		
		Thread.sleep(2000);
		h1.interrupt();
		
		
	}

}
