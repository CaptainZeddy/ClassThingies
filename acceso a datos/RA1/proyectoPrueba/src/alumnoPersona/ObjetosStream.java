package alumnoPersona;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjetosStream {

	public static void escribir(File f, Object o) throws IOException {
		FileOutputStream fo= null;
		ObjectOutputStream oos= null;

		if(!f.exists()) {
			fo= new FileOutputStream(f);
			oos= new ObjectOutputStream(fo);
		}else {
			fo= new FileOutputStream(f,true);
			oos=new ObjectOutputStreamSinCabecera(fo);
		}
		
		oos.writeObject(o);
		oos.close();
		fo.close();
	}

	public static void leer (File f) throws FileNotFoundException, IOException, ClassNotFoundException{
		Alumno alumno;
		FileInputStream fi= new FileInputStream(f);
		ObjectInputStream ois= new ObjectInputStream(fi);

		try {
			while(true) {
				alumno =(Alumno) ois.readObject();
				System.out.println(alumno.toString());
			}
		}catch(EOFException e){
			System.out.println(e.getMessage());
		}
		ois.close();
	}

}
