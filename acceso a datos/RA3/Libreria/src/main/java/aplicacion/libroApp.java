package aplicacion;

import dao.libroDAO;
import dao.libroDAOimpl;
import modelo.Libro;
import modelo.Detalle_libro;

public class libroApp {

	public static void main(String[] args) {	
		
		libroDAOimpl ldao= new libroDAOimpl();
			
		
		Libro libro = new Libro();
		
		libro.setAutor("un manin");
		libro.setTitulo("el libro del manin");
		
		Detalle_libro dl= new Detalle_libro();
		
		dl.setEditorial("esta");
		dl.setSinopsis("esta otra");
		
		libro.setLibrodetalle(dl);
		dl.setLibro(libro);
		
		ldao.modelo();
//		ldao.ejercicio1();
		
		ldao.ejercicio2("J.R.R. Tolkien");
	}

}
