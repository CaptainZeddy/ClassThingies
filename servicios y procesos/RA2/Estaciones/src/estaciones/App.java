package estaciones;

public class App {

	public static void main(String[] args) {
		
		Estaciones estacion= new Estaciones();
		Thread h1= new Thread(estacion);
		
		h1.start();
		
	}

}
