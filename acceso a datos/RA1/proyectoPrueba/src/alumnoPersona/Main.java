package alumnoPersona;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		File file=new File("Alumnos.dat");
		
		if (file.exists()) {
			file.delete();
			file= new File("Alumnos.dat");
		}
		
		Fecha f = new Fecha(12,6,2069);
		
		Alumno a = new Alumno("paco", "primero", 8, f);
		Alumno b = new Alumno("Pepe", "Segundo", 5, f);
		Alumno c = new Alumno("Manin", "Primero", 9, f);
	
		try {
			ObjetosStream.escribir(file, a);
			ObjetosStream.escribir(file, b);
			ObjetosStream.escribir(file, c);
			
			ObjetosStream.leer(file);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}
