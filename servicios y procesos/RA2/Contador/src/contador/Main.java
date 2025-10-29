package contador;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		ThreadGroup tg= new ThreadGroup("Grupo contador");

		Contador cont= new Contador();

		for (int i =1; i<6;i++) {
			String nombre="t"+i;
			
			(new Thread(tg,cont,nombre)).start();
			
			
		}
		
//		System.out.println(Thread.activeCount());
//		tg.interrupt();
//		System.out.println(Thread.activeCount());
		
	}

}
