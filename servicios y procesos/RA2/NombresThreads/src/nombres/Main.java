package nombres;

public class Main {

	public static void main(String[] args) {
		
		Thread.currentThread().setName("Manin");
		System.out.println(Thread.currentThread().getName());
		
		ThreadGroup grupo = new ThreadGroup("koi");
		
		Nombres n= new Nombres();
		
		Thread n1= new Thread(grupo, n ,"h1");
		Thread n2= new Thread(grupo, n ,"h2");
		Thread n3= new Thread(grupo, n ,"h3");
		
		n1.start();
		n2.start();
		n3.start();
		
		


	}

}
