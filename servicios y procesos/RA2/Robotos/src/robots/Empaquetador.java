package robots;

public class Empaquetador implements Runnable {

	private int tipo;


	public Empaquetador(int tipo) {
		this.tipo = tipo;
	}

	public synchronized static void empaquetar(int numero) {

	}

	@Override
	public void run() {

		for (int i=0;i<9;i++) {

			if(Cadena.chain[i][1]==0) {
				Cadena.chain[i][1]=0;
				Cadena.chain[i][2]=(int)(Math.random()*4);
			}
		}

	}
}
