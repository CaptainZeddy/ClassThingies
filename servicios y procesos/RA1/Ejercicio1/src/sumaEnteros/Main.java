package sumaEnteros;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Main {
	public static void main(String[] args) throws IOException {

		File f = new File("bin");

		ProcessBuilder pb =new ProcessBuilder("java", "sumaEnteros.Sumar", "2", "4");

		pb.directory(f);

		Process p =pb.start();


		try {
			InputStream is= p.getInputStream();
			int c;
			while((c = is.read()) !=-1)
				System.out.println((char) c);
			is.close();
		}catch (Exception e) {
			e.printStackTrace();
		}


	}
}
