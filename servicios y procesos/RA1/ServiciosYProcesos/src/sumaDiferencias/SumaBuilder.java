package sumaDiferencias;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SumaBuilder {

	public static void main(String[] args)throws IOException {

		File f= new File("bin");

		ProcessBuilder pb= new ProcessBuilder("java","sumaDiferencias.SumaMain","2","4");

		ProcessBuilder cl = new ProcessBuilder("sh");

		cl.directory(f);
		pb.directory(f);
		
		System.out.println("Ruta completa: "+pb.directory());
		
		File fOut = new File ("salida.txt");;
		File fErr = new File ("error.txt");;
		File fBat = new File ("fichero.txt");;
		
		cl.redirectInput(ProcessBuilder.Redirect.from(fBat));
		cl.redirectOutput(ProcessBuilder.Redirect.to(fOut));
		cl.redirectError(ProcessBuilder.Redirect.appendTo(fErr));

		
		Process a = cl.start();
		Process p = pb.start();




	
		

		try {
			InputStream is = a.getInputStream();
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
