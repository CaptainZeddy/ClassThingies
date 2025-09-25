package writeRead;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedThing {

	
	public static void escribir (String a, String b) {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(a,true));
			bw.write(b);
			bw.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void leer(String a) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(a));
			String text;
			while ((text = br.readLine())!= null) {
				System.out.println(text);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
