package sumadores;

public class App {
	public static void main(String[] args) {
		Sumador primero = new Sumador(1, 20000); 
		Sumador segundo = new Sumador(20001, 40000); 
		Sumador tercero = new Sumador(40001, 60000); 

		Thread h1= new Thread(primero);
		Thread h2= new Thread(segundo);
		Thread h3= new Thread(tercero);
		
		try {
			h1.start();
			h1.join();
			h2.start();
			h2.join();
			h3.start();			
			h3.join();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.println(primero.getResultado()+segundo.getResultado()+tercero.getResultado());
	}
}
