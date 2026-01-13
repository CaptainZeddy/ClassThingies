package app;

import java.util.ArrayList;
import java.util.List;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import pojo.*;

public class MainLibros {

	public static void main(String[] args) {
		ODB odb = ODBFactory.open("biblioteca.db");

		// 📌 A) Insertar un libro sin especificar ningún autor
		Libro libroSinAutor = new Libro("Libro sin autor", 2023, "Editorial X", 200, new ArrayList<>());
		odb.store(libroSinAutor);

		// 📌 B) Insertar un autor sin especificar ningún libro
		Autor autorSinLibro = new Autor("Luis", "Martínez", "Peruana", 40, new ArrayList<>());
		odb.store(autorSinLibro);

		// 📌 Insertar 3 libros más con autores vacíos
		Libro libro1 = new Libro("Java Avanzado", 2020, "Pearson", 450, new ArrayList<>());
		Libro libro2 = new Libro("Python para todos", 2019, "O'Reilly", 350, new ArrayList<>());
		Libro libro3 = new Libro("C++ Moderno", 2021, "McGraw-Hill", 500, new ArrayList<>());

		odb.store(libro1);
		odb.store(libro2);
		odb.store(libro3);

		// 📌 Insertar 3 autores más con lista de libros vacía
		Autor autor1 = new Autor("Carlos", "Pérez", "Española", 45, new ArrayList<>());
		Autor autor2 = new Autor("María", "Gómez", "Argentina", 38, new ArrayList<>());
		Autor autor3 = new Autor("Juan", "Rodríguez", "Mexicana", 50, new ArrayList<>());

		odb.store(autor1);
		odb.store(autor2);
		odb.store(autor3);

		// 📌 C) Modificar un libro para añadirle un autor existente
		Objects<Libro> libros = odb.getObjects(Libro.class);
		if (libros.hasNext()) {
			Libro libroModificar = libros.next(); // Tomamos el primer libro
			libroModificar.getAutores().add(autor1);
			odb.store(libroModificar);
			System.out.println("Autor:"+autor1+" agregado al libro: " + libroModificar.getTitulo());
		}

		// 📌 D) Modificar un autor para añadirle un libro existente de la base de datos
		Objects<Autor> autores = odb.getObjects(Autor.class);
		if (autores.hasNext()) {
			Autor autorModificar = autores.next(); // Tomamos el primer autor
			autorModificar.getLibros().add(libro2);
			odb.store(autorModificar);
			System.out.println(
					"Libro:"+libro2+" agregado al autor: " + autorModificar.getNombre() + " " + autorModificar.getApellido());
		}

		// 📌 E) Visualizar todos los libros
		System.out.println("\n📚 Lista de Libros:");
		libros = odb.getObjects(Libro.class);
		while (libros.hasNext()) {
			Libro l = libros.next();
			System.out.println("📖 " + l);
		}

		// 📌 F) Visualizar todos los autores
		System.out.println("\n✍ Lista de Autores:");
		autores = odb.getObjects(Autor.class);
		while (autores.hasNext()) {
			Autor a = autores.next();
			System.out.println("👤 " + a);
		}

		// 🔥 Borrar un libro (por ejemplo, "Java Avanzado")
		libros = odb.getObjects(Libro.class);
		while (libros.hasNext()) {
			Libro libro = libros.next();
			if (libro.getTitulo().equals("Java Avanzado")) {
				odb.delete(libro);
				System.out.println("❌ Libro eliminado: " + libro.getTitulo());
				break;
			}
		}

		// 🔥 Borrar un autor (por ejemplo, "María Gómez")
		autores = odb.getObjects(Autor.class);
		while (autores.hasNext()) {
			Autor autor = autores.next();
			if (autor.getNombre().equals("Carlos") && autor.getApellido().equals("Pérez")) {
				odb.delete(autor);
				System.out.println("❌ Autor eliminado: " + autor.getNombre() + " " + autor.getApellido());
				break;
			}
		}
		
		// 📌 E) Visualizar todos los libros
		System.out.println("\n📚 Lista de Libros:");
		libros = odb.getObjects(Libro.class);
		while (libros.hasNext()) {
			Libro l = libros.next();
			System.out.println("📖 " + l);
		}

		// 📌 F) Visualizar todos los autores
		System.out.println("\n✍ Lista de Autores:");
		autores = odb.getObjects(Autor.class);
		while (autores.hasNext()) {
			Autor a = autores.next();
			System.out.println("👤 " + a);
		}
		
		ICriterion criterio = Where.gt("paginas", 30);
		IQuery query= new CriteriaQuery("libro", criterio);
		
		
		
		odb.close();
	}
}