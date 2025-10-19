package sumaDiferencias;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SumaBuilder {

	public static void main(String[] args)throws IOException {


		// Directorio donde está la clase compilada
		File f = new File("bin");

		// Crear proceso para ejecutar SumaMain con argumentos 2 y 4
		ProcessBuilder pb = new ProcessBuilder("java", "sumaDiferencias.SumaMain", " ", "37");

		pb.directory(f); // Establecer directorio de trabajo

		// Redirigir salida estándar y errores a archivos
		pb.redirectOutput(new File("salida.txt"));
		pb.redirectError(new File("error.txt"));

		// Ejecutar el proceso
		Process p = pb.start();


		try {
			InputStream is = p.getInputStream();
			int c;
			while ((c = is.read()) != -1)
				System.out.print((char) c);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			InputStream er = p.getErrorStream();
			BufferedReader brer = new BufferedReader(new InputStreamReader(er));
			String liner = null;
			while ((liner = brer.readLine()) != null)
				System.out.println("ERROR >" + liner);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
