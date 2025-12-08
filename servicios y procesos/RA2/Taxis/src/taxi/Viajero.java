package taxi;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Viajero implements Runnable {
	private String nombre;
	private CyclicBarrier barrera;

	public String getNombre() {
		return nombre;
	}


	public Viajero(String nombre, CyclicBarrier barrera) {
		this.nombre = nombre;
		this.barrera = barrera;
	}


	@Override
	public void run() {

		try {
			Thread.sleep((int)(Math.random()*5000));

			System.out.println("El viajero "+getNombre()+" ha llegado a A y está esperando.");

			barrera.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

		System.out.println("Viajero "+getNombre()+" ha llegado a su destino.");

	}


}
