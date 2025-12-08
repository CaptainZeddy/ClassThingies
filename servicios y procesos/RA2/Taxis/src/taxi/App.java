package taxi;

import java.util.concurrent.CyclicBarrier;

public class App {

	public static void main(String[] args) {
		CyclicBarrier barrera= new CyclicBarrier(3);
		
		Viajero v=new Viajero("paco", barrera);
		Viajero v1=new Viajero("pepe",barrera );
		Viajero v2=new Viajero("juan", barrera);
		
		Thread h1= new Thread(v);
		Thread h2= new Thread(v1);
		Thread h3= new Thread(v2);
		
		h1.start();
		h2.start();
		h3.start();
	}

}
