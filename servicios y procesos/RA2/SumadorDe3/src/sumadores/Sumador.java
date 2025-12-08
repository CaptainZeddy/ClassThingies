package sumadores;

public class Sumador implements Runnable {
	private int menor;
	private int mayor;
	private int resultado=0;
	
	
	
	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	public Sumador(int menor, int mayor) {
		this.menor = menor;
		this.mayor = mayor;
	}

	@Override
	public void run() {
		long start=System.currentTimeMillis();
		for(int i=menor;i<mayor;i++) {
			resultado+=i;
		}
		long end=System.currentTimeMillis();
		System.out.println(resultado);
		System.out.println("Tiempo activo: "+(end-start));

	}
	
	
}
