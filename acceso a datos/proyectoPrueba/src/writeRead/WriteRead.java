package writeRead;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriteRead {



	public static void escribir(String a, String b){

		File f= new File(a);

		try {
			FileWriter fr = new FileWriter(a,true);
			fr.write(b);
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void leer(String a) {
		int i;

		try {
			FileReader fr= new FileReader(a);

			while ((i = fr.read())!=-1) {
				char c= (char)i;
				System.out.print(c);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
