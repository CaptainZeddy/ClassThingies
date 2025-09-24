package RandomPractice;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public final static String ruta="Random";

	public static void main(String[] args) throws IOException {
		int numero=0;
		int posicion;
		int nuevo;
		
		Scanner scan = new Scanner(System.in);

		System.out.println("Introduce un numero: ");

		numero= scan.nextInt();
		RandomMethods.escribirRandom(new File(ruta), numero);

		try{
			RandomMethods.leerRandom(ruta);
		}catch(EOFException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Escriba la posicion a cambiar: ");
		posicion=scan.nextInt();
		System.out.println("Escriba el numero nuevo: ");
		nuevo = scan.nextInt();
		
		
		try {
			RandomMethods.buscar(ruta, posicion, nuevo);

		}catch(EOFException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		try{
			RandomMethods.leerRandom(ruta);
		}catch(EOFException e) {
			System.out.println(e.getMessage());
		}
	}


}
