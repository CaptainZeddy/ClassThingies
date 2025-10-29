package atleta;

public class App {
	
	public static void main(String[] args) {

		Atleta atleta=new Atleta();
		
		Thread h1=new Thread(atleta,"Paco");
		
		h1.start();
		
		
	}
}
