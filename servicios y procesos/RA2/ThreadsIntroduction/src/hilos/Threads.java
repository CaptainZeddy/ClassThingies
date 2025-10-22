package hilos;

import java.util.Scanner;

public class Threads extends Thread {

	private String nombre;
	private int dia;
	private String hora;
	
	
	public Threads(String nombre, int dia, String hora) {
		super();
		this.nombre = nombre;
		this.dia = dia;
		this.hora = hora;
	}


	@Override
	public void run() {
		Scanner scan= new Scanner(System.in);
		if (hora.equals("8:00")) {
		System.out.println(nombre+" ha llegado a las: "+hora+" el dia: "+dia);
		}else {
			System.out.println(nombre+" ha llegado fuera de hora");
		}
		
	}

	
	
	
	
}
