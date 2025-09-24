package RandomPractice;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class RandomMethods {



	public static void escribirRandom(File s, int i) throws IOException {

		if (!s.exists()) {
			s.createNewFile();
		}

		RandomAccessFile fr= new RandomAccessFile(s, "rw");
		fr.seek(fr.length());
		fr.writeInt(i);
		fr.close();
	}

	public static void leerRandom(String s) throws IOException, EOFException {
		int numero;
		//int pos=0;

		try (RandomAccessFile fr = new RandomAccessFile(s, "r")) {
			while(fr.getFilePointer()< fr.length()) {
				//				fr.seek(pos);
				numero=fr.readInt();
				System.out.println("numero: "+numero);
				//				pos+=4;
			}
		}


	}
	public static void buscar(String s, int pos, int n) throws FileNotFoundException, IOException {
		try (RandomAccessFile fr= new RandomAccessFile(s, "rw")){
			int numero;
			int posicion=(pos-1)*4;
			fr.seek(posicion);
			numero=fr.readInt();
			System.out.println("Numero buscado: "+ numero );
			fr.seek(posicion);
			fr.writeInt(n);
			fr.seek(posicion);
			numero=fr.readInt();
			System.out.println("Numero nuevo: "+ numero);

		}
	}

}
