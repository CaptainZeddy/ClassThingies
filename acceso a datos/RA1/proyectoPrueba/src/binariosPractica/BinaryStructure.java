package binariosPractica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryStructure {
	
	public static void write(int a, File f) {
		try {
			FileOutputStream fos= new FileOutputStream(f,true);
			fos.write(a);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void read(File f) {
		try {
			FileInputStream fis= new FileInputStream(f);
			int numeritos;
			while((numeritos=fis.read())!=-1) {
				System.out.println(numeritos);
			}
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
