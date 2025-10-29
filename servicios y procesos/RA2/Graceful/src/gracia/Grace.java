package gracia;

public class Grace implements Runnable{

	@Override
	public void run() {
		int i=0;
		try {
			while(i<10000&&!Thread.currentThread().isInterrupted()) {

				if(Thread.currentThread().isAlive())
					System.out.println("En ejecucion...");

				Thread.sleep(20);


				i++;

			}
			System.out.println("Ha terminado el conteo: "+i);
		}catch(InterruptedException e) {
			System.out.println("Se ha detectado una parada. "+i);
		}
	}

}


