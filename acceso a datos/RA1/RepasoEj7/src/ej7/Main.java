package ej7;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		File f= new File("utensilios.dat");
		File g= new File("utensilios.txt");

		ArrayList<Utensilio> utensilio= new ArrayList<Utensilio>();

		utensilio.add(new Utensilio("tijeras", 7));
		utensilio.add(new Utensilio("colador", 1));
		utensilio.add(new Utensilio("colador", 4));
		utensilio.add(new Utensilio("batidora", 3));
		utensilio.add(new Utensilio("alicates", 5));
		utensilio.add(new Utensilio("batidora", 2));
		utensilio.add(new Utensilio("alicates", 10));
		utensilio.add(new Utensilio("cafetera", 1));
		utensilio.add(new Utensilio("embudo", 2));
		utensilio.add(new Utensilio("tetera", 1));
		utensilio.add(new Utensilio("embudo", 3));
		utensilio.add(new Utensilio("alicates", 2));
		utensilio.add(new Utensilio("destornillador", 6));


		if(!f.exists()) {
			for(int i=0; i <utensilio.size();i++) {
				try {
					RanMethods.escribir(f, utensilio.get(i).getNombre(), utensilio.get(i).getCantidad());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		//		try {
		//			RanMethods.leer(f, 5);
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}

		try {
			System.out.println(RanMethods.leerTodo(f));
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			RanMethods.leerIntoTxt(g, RanMethods.leerTodo(f));
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
