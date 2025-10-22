package contador;

import java.util.Random;

public class Contador extends Thread{

	@Override
	public void run(){
		for(int i= 0;i<11;i++) {


			try {
				Thread.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				System.out.print("");
			}
		}
		System.out.println(Thread.currentThread().getName()+" ha terminado.");

	}

}
