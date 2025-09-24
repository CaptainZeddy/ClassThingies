package writeRead;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		
		System.out.println("Introduzca un nombre para el fichero:");
		
		String name= scan.nextLine();
		
		System.out.println("Escriba un texto para el fichero:");
		
		String text= scan.nextLine();
		
		WriteRead.escribir(name, text);
		WriteRead.leer(name);
		System.out.println();
		
		BufferedThing.escribir(name, text);
		BufferedThing.leer(name);
	
	}

}
