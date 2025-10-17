package ejercicio8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		File f= new File("bin");
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c","type ejpal.txt > salida.txt");

		Process p= pb.start();
		p.waitFor();

		Thread.sleep(2000);



		try (BufferedReader br = new BufferedReader(new FileReader("salida.txt"))) {
			String palabra;

			while((palabra= br.readLine())!=null) {
				palabra=palabra.trim();
				if (palabra.isEmpty()) continue;

				ProcessBuilder pbPal = new ProcessBuilder("java", "ejercicio8.Palindromo", palabra);
				pbPal.directory(f);
				
				pbPal.redirectError(ProcessBuilder.Redirect.appendTo(new File("error.txt")));
				pbPal.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("palindromo.txt")));
				
				Process pr= pbPal.start();

				
				
//				try (BufferedReader brr=new BufferedReader(new InputStreamReader(pr.getInputStream()))){
//					String salida;
//
//					while((salida=brr.readLine())!=null){
//						System.out.println(palabra + " "+salida);
//					}
//				}

				
				
				pr.waitFor();
				System.out.println(pr.exitValue());
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
