package contador;

public class App {

	public static void main(String[] args) throws InterruptedException {

		ThreadGroup tg= new ThreadGroup("Grupo contador");

		Contador cont= new Contador();

		for (int i =0; i<101;i++) {
			String nombre="t"+i;

			(new Thread(tg,cont,nombre)).start();


		}
	}
}
