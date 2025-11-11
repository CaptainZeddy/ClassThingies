package probadores;

import java.util.concurrent.Semaphore;

public class Tienda implements Runnable {

	static int clientes=20;
	static Semaphore s= new Semaphore(5);

	public static void entrar() {
		clientes+=1;
		System.out.println("Entra un cliente.");
	}

	public static void salir() {
		clientes-=1;
		System.out.println("Sale un cliente.");

	}

	@Override
	public void run() {
		for (int i =0;i <=20;i--) {

			try {
				s.acquire();
				entrar();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				s.release();
				salir();
			}
			
		}




	}

}
