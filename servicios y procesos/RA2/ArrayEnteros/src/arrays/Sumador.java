package arrays;

public class Sumador implements Runnable {
	private static int[] enteros= new int[4];

	public Sumador(int[] enteros) {
		this.enteros = enteros;
	}

	public synchronized static void sumArray() {
		int resultado=0;
		
		for (int i=0;i<enteros.length;i++) {
			resultado+=enteros[i];
			System.out.println("Total parcial de: "+resultado);
		}
		System.out.println("El resultado del hilo "+Thread.currentThread().getName()+" es: "+resultado);
	}
	
	
	@Override
	public void run() {
		System.out.println("Iniciando: "+Thread.currentThread().getName());
		sumArray();
		System.out.println("Finalizando: "+Thread.currentThread().getName());
	}
}
