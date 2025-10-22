package hilos;

public class Main {

	public static void main(String[] args) {
		
		int dia=23;
		String hora="5:00";
		String nombre="pepe";
		
		Threads t= new Threads(nombre, dia, hora);
		t.start();
	}

}
