package robots;

public class Colocador implements Runnable {

	@Override
	public void run() {

		for (int i=0;i<9;i++) {
			
			if(Cadena.chain[i][1]!=0) {
				Cadena.chain[i][1]=0;
				Cadena.chain[i][2]=(int)(Math.random()*4);
				System.out.println("Se coloca un paquete con posicion y tipo: "+Cadena.chain[i][1]+" "+Cadena.chain[i][2]);
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}

		}
	}
}
