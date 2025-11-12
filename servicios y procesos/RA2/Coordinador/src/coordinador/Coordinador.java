package coordinador;

public class Coordinador {

	
	
	public static void main(String[] args) throws InterruptedException {
		
		ThreadGroup grupo=new ThreadGroup("this");
		Tarea t= new Tarea();
		Thread t1=new Thread(grupo,t);
		Thread t2=new Thread(grupo,t);
		Thread t3=new Thread(grupo,t);
		
		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
		
		
		System.out.println("Tarea finalizada");
	}

}
