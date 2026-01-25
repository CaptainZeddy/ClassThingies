package ejercicio1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import pojos.Autor;
import pojos.Libro;

public class Biblioteca {
private static final String db="biblioteca.db";
	
    public static void main(String[] args) {
    	
    	File file= new File(db);
    	if (file.exists()) file.delete();
        ODB odb = ODBFactory.open("biblioteca.db");

      

        // 📌 Insertar Libros
        List<Autor> listaAutores1 = new ArrayList<>();
        List<Autor> listaAutores2 = new ArrayList<>();
        List<Autor> listaAutores3 = new ArrayList<>();
        List<Autor> listaAutores4 = new ArrayList<>();

        Libro libro1 = new Libro("Java Avanzado", 2020, "Pearson", 450, listaAutores1);
        Libro libro2 = new Libro("Python para Todos", 2019, "O'Reilly", 350, listaAutores2);
        Libro libro3 = new Libro("C++ Moderno", 2021, "McGraw-Hill", 500, listaAutores3);
        Libro libro4 = new Libro("Historias Cortas", 2015, "Anaya", 25, listaAutores4); // Menos de 30 páginas
        odb.store(libro1);
        odb.store(libro2);
        odb.store(libro3);
        odb.store(libro4);

        // 📌 Insertar Autores
        List<Libro> listaLibros1 = new ArrayList<>();
        List<Libro> listaLibros2 = new ArrayList<>();
        List<Libro> listaLibros3 = new ArrayList<>();
        List<Libro> listaLibros4 = new ArrayList<>();

        Autor autor1 = new Autor("Carlos", "Pérez", "Española", 45, listaLibros1);
        Autor autor2 = new Autor("María", "Sánchez", "Argentina", 38, listaLibros2); // Apellido con "S"
        Autor autor3 = new Autor("Juan", "Rodríguez", "Mexicana", 50, listaLibros3);
        Autor autor4 = new Autor("Ana", "López", "Española", 42, listaLibros4);
        odb.store(autor1);
        odb.store(autor2);
        odb.store(autor3);
        odb.store(autor4);

        // 📌 Relacionar autores con libros
        libro1.getAutores().add(autor1);
        libro2.getAutores().add(autor2);
        libro3.getAutores().add(autor3);
        libro4.getAutores().add(autor4);
        odb.store(libro1);
        odb.store(libro2);
        odb.store(libro3);
        odb.store(libro4);

        // 📌 Relacionar libros con autores
        autor1.getLibros().add(libro1);
        autor2.getLibros().add(libro2);
        autor3.getLibros().add(libro3);
        autor4.getLibros().add(libro4);
        odb.store(autor1);
        odb.store(autor2);
        odb.store(autor3);
        odb.store(autor4);

        odb.close();
        System.out.println("✅ Datos insertados correctamente.");
        
//        masDe30Paginas();
//        autoresEspañoles();
        apellidoS();
    }
    
    public static void masDe30Paginas() {
    	ODB odb= ODBFactory.open(db);
    	
    	ICriterion criterio= Where.gt("paginas", 30);
    	
    	CriteriaQuery query= new CriteriaQuery(Libro.class,criterio);
    	
    	Objects<Libro> libro= odb.getObjects(query);
    	   	
    	while(libro.hasNext()) {
    		Libro l= libro.next();
    		System.out.println(l);
    	}
    	odb.close();
    }
    
    
    public static void autoresEspañoles() {
    	ODB odb= ODBFactory.open(db);
    	
    	ICriterion criterio= Where.equal("nacionalidad", "Española");
    	
    	CriteriaQuery query= new CriteriaQuery(Autor.class,criterio);
    	
    	Objects<Autor> autor= odb.getObjects(query);
    	   	
    	while(autor.hasNext()) {
    		Autor a= autor.next();
    		System.out.println(a);
    	}
    	odb.close();
    }
    
    public static void apellidoS() {
    	ODB odb= ODBFactory.open(db);
    	
    	ICriterion criterio= Where.like("apellido", "o");
    	
    	CriteriaQuery query= new CriteriaQuery(Autor.class,criterio);
    	
    	Objects<Autor> autor= odb.getObjects(query);
    	   	
    	while(autor.hasNext()) {
    		Autor a= autor.next();
    		
    		for(Libro l : a.getLibros()) {
    			System.out.println("Titulo: "+l.getTitulo()+", Año: "+l.getAño());
    		}
    		
    	}
    	odb.close();
    }
    
    
    
    
    
    
}
