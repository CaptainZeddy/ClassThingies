package binariosPractica;

import java.io.File;
import java.util.Scanner;

public class Main {
	public static final String ruta="Numeros";

	public static void main(String[] args) {
		File f= new File(ruta);
		Scanner scan =new Scanner (System.in);

		if(f.exists()) {
			f.delete();
		}else {
			f= new File(ruta);
		}
		System.out.println("Introduce numeros y -1 para terminar");
		int numeros=0;

		while(numeros!=-1) {
			BinaryStructure.write(numeros, f);
			numeros= scan.nextInt();
		}
		
		BinaryStructure.read(f);
		scan.close();
	}

}


