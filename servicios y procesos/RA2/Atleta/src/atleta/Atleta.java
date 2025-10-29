package atleta;

public class Atleta implements Runnable{

	
	
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		
		for(int i =1; i<16; i++) {
			
			System.out.println("Km "+i);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("Fin del trayecto. ");
	}
}
