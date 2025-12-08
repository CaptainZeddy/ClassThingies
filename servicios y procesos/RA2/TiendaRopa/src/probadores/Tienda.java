package probadores;

import java.util.concurrent.Semaphore;

public class Tienda implements Runnable {

	static Semaphore probador= new Semaphore(4);


	@Override
	public void run() {
		
		try {
			probador.acquire();
			System.out.println("Entra: "+Thread.currentThread().getName());
			Thread.sleep((long) (Math.random() * 3000 + 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			System.out.println("Sale: "+Thread.currentThread().getName());
			probador.release();
		}


	}

}
