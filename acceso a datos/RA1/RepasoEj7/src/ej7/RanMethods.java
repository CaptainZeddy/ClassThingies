package ej7;

import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RanMethods {

	public static void escribir(File f, String s, int i) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(f, "rw");
		raf.seek(raf.length());

		StringBuffer caracteres = new StringBuffer(s);
		caracteres.setLength(20);
		raf.writeChars(caracteres.toString());
		raf.writeInt(i);	
		raf.close();
	}

	public static void leer(File f, int i) throws IOException, EOFException {
		RandomAccessFile raf = new RandomAccessFile(f, "r");
		int registro= (i-1)*44;
		raf.seek(registro);
		String nombre="";

		for (int j =0; j<20;j++) {
			nombre+=raf.readChar();
		}

		int cantidad=raf.readInt();

		System.out.println(nombre.trim()+": "+cantidad);

	}

	public static String leerTodo(File f) throws IOException, EOFException {
		RandomAccessFile raf = new RandomAccessFile(f, "r");
		raf.seek(0);
		String utensilio="";
		int cantidad=0;

		for(int k=0;k<(raf.length()/44);k++) {
			String nombre="";
			
			for (int j =0; j<20;j++) {
				nombre+=raf.readChar();
			}
			cantidad=raf.readInt();
			utensilio=utensilio+(nombre.trim()+": "+cantidad)+"\n";
		}
		return utensilio;
	}

	public static void leerIntoTxt(File f ,String s) throws IOException {
		try(FileWriter fw = new FileWriter(f,true)){
			fw.write(s);
		}
	}

}
