package coordinador;

public class Tarea implements Runnable{

	private static int contador=0;

	public synchronized void checkear() {
		contador++;
		
		if(contador!=3) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(contador==3) {
			contador=0;
			notifyAll();
		}
	}

	@Override
	public void run() {
		try {
			System.out.println("Preparando...");
			Thread.sleep((long) (Math.random() * 3000 + 1000));
			checkear();
			System.out.println("En ejecucion...");
			Thread.sleep((long) (Math.random() * 3000 + 1000));
			checkear();
			System.out.println("Finalizando...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
