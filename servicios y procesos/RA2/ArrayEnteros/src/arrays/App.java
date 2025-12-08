package arrays;

public class App {

	public static void main(String[] args) {

		Sumador s=new Sumador(new int[]{1,2,3,4,5});

		Thread h1= new Thread(s,"Suma 1");
		Thread h2= new Thread(s,"Suma 2");

//		try {
			h1.start();
//			h1.join();
			h2.start();
//			h2.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
