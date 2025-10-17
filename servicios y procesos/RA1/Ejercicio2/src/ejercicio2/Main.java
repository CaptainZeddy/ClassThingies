package ejercicio2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Main {

	public static void main(String[] args) throws IOException {
		File f =new File("bin");

		ProcessBuilder pb = new ProcessBuilder("java", "ejercicio2.LeerNombre", "Luna");

		pb.directory(f);

		Process p = pb.start();

		try {
			p.waitFor();
			System.out.println("La salida es: "+p.exitValue());
		}catch (Exception e) {
			e.printStackTrace();
		}


		try {
			InputStream is = p.getInputStream();
			int c;
			while ((c=is.read())!=-1)
			System.out.print((char)c);
			is.close();
		}catch (Exception e) {
			e.printStackTrace();
		}


	}

}
