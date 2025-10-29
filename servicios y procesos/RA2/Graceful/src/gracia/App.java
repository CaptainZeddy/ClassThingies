package gracia;

public class App {

	public static void main(String[] args) {
		Grace grace= new Grace();
		Thread h1 = new Thread(grace);
		h1.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		h1.interrupt();
		
		
	}

}
