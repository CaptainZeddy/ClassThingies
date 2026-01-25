package ejercicio1;

import java.io.File;

import javax.management.Query;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Not;
import org.neodatis.odb.core.query.criteria.Or;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

import pojos.Jugador;
import pojos.Paises;


public class EquiposDB {
	private static final String db="equipos.db";
	public static void main(String[] args) {
		File file= new File(db);
		if (file.exists()) file.delete();

		ODB odb = ODBFactory.open(db);

		// Crear países
		Paises irlanda = new Paises(1, "IRLANDA");
		Paises francia = new Paises(2, "FRANCIA");
		Paises italia = new Paises(3, "ITALIA");

		// Almacenar países
		odb.store(irlanda);
		odb.store(francia);
		odb.store(italia);

		// Crear jugadores
		Jugador j1 = new Jugador("Ana", "baloncesto", 16, irlanda);
		Jugador j2 = new Jugador("Luis", "fútbol", 20, francia);
		Jugador j3 = new Jugador("Mario", "tenis", 15, italia);
		Jugador j4 = new Jugador("Clara", "fútbol", 21, francia);

		// Almacenar jugadores
		odb.store(j1);
		odb.store(j2);
		odb.store(j3);
		odb.store(j4);

		odb.close();

		//        mostrar();
		//    franciaObasket();
		//		veinteYfutbol();
		//		jugadoresPorDeporte();
//		edadMedia();
//		maxYmin();
//		futbol18y25();
//		franciaOitalia();
//		noMasDe22Años();
//		franciaOtenis();
		edadMediaTenisO22Años();
	}
	public static void edadMediaTenisO22Años() {
		ODB odb= ODBFactory.open(db);

		ICriterion criterio= new Or()
				.add(Where.equal("deporte", "tenis"))
				.add(Where.gt("edad", 20));
		
		Values valor= odb.getValues(new ValuesCriteriaQuery(Jugador.class, criterio).avg("edad"));
				
		while (valor.hasNext()) {
			ObjectValues resultado= valor.next();

			System.out.println(resultado);			
		}
		odb.close();
	}
	
	public static void franciaOtenis() {
		ODB odb= ODBFactory.open(db);

		ICriterion criterio= new Or()
				.add(Where.equal("deporte", "tenis"))
				.add(Where.equal("pais.nombrepais", "FRANCIA"));
		
		Values valor= odb.getValues(new ValuesCriteriaQuery(Jugador.class, criterio).count("Tenis o francia:"));
				
		while (valor.hasNext()) {
			ObjectValues resultado= valor.next();

			System.out.println(resultado);			
		}
		odb.close();
	}
	
	public static void noMasDe22Años() {
		ODB odb= ODBFactory.open(db);

		ICriterion criterio= Where.not(Where.gt("edad",15));
				
			
		IQuery query= new CriteriaQuery(Jugador.class, criterio);

		Objects<Jugador> jugador = odb.getObjects(query);

		while (jugador.hasNext()) {
			System.out.println(jugador.next());			
		}
		odb.close();
	}
	
	public static void franciaOitalia() {
		ODB odb= ODBFactory.open(db);

		ICriterion criterio= new Or()
				.add(Where.equal("pais.nombrepais", "FRANCIA"))
				.add(Where.equal("pais.nombrepais", "ITALIA"));
			
		IQuery query= new CriteriaQuery(Jugador.class, criterio);

		Objects<Jugador> jugador = odb.getObjects(query);

		while (jugador.hasNext()) {
			System.out.println(jugador.next());			
		}
		odb.close();
	}
	
	public static void futbol18y25() {
		ODB odb= ODBFactory.open(db);

		ICriterion criterio= new And()
				.add(Where.ge("edad", 20))
				.add(Where.lt("edad", 25));
		
		ICriterion criterio2= Where.equal("deporte","fútbol");
		
		ICriterion criterioFinal= new And()
				.add(criterio)
				.add(criterio2);
		
		IQuery query= new CriteriaQuery(Jugador.class, criterioFinal);

		Objects<Jugador> jugador = odb.getObjects(query);

		while (jugador.hasNext()) {
			System.out.println(jugador.next());			
		}
		odb.close();
	}
	
	public static void maxYmin() {
		ODB odb= ODBFactory.open(db);

		Values valor= odb.getValues(new ValuesCriteriaQuery(Jugador.class).min("edad").max("edad"));
				
		while (valor.hasNext()) {
			ObjectValues resultado= valor.next();

			System.out.println("Minima: "+resultado.getByIndex(0)+"\nMaxima: "+resultado.getByIndex(1));			
		}
		odb.close();
	}
	
	public static void edadMedia() {
		ODB odb= ODBFactory.open(db);

		Values valor= odb.getValues(new ValuesCriteriaQuery(Jugador.class).avg("edad"));

		while (valor.hasNext()) {
			ObjectValues resultado= valor.next();

			System.out.println(resultado);			
		}
		odb.close();
	}

	public static void jugadoresPorDeporte() {
		ODB odb= ODBFactory.open(db);

		Values valor= odb.getValues(new ValuesCriteriaQuery(Jugador.class).field("deporte").count("jugadores").groupBy("deporte"));


		while (valor.hasNext()) {
			ObjectValues resultado= valor.next();

			System.out.println(resultado);			
		}
		odb.close();
	}


	public static void veinteYfutbol() {
		ODB odb= ODBFactory.open(db);

		ICriterion criterio= new And()
				.add(Where.equal("edad", 20))
				.add(Where.equal("deporte", "fútbol"));

		IQuery query= new CriteriaQuery(Jugador.class, criterio);

		Objects<Jugador> jugador = odb.getObjects(query);

		while (jugador.hasNext()) {
			System.out.println(jugador.next());			
		}
		odb.close();
	}


	public static void franciaObasket() {
		ODB odb= ODBFactory.open(db);

		ICriterion criterio= new Or()
				.add(Where.equal("pais.nombrepais", "FRANCIA"))
				.add(Where.equal("deporte", "baloncesto"));

		IQuery query= new CriteriaQuery(Jugador.class, criterio);

		Objects<Jugador> jugador = odb.getObjects(query);

		while (jugador.hasNext()) {
			System.out.println(jugador.next());			
		}
		odb.close();
	}

	public static void mostrar() {
		ODB odb= ODBFactory.open(db);

		IQuery query= new CriteriaQuery(Jugador.class);

		Objects<Jugador> jugador = odb.getObjects(query);

		while (jugador.hasNext()) {
			System.out.println(jugador.next());			
		}
		odb.close();
	}


	// Método para visualizar jugadores de 14 años de IRLANDA, FRANCIA e ITALIA
	public static void visualizarJugadores14() {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		ICriterion criterio = new Or()
				.add(Where.equal("pais.nombrepais", "IRLANDA"))
				.add(Where.equal("pais.nombrepais", "FRANCIA"))
				.add(Where.equal("pais.nombrepais", "ITALIA"))
				.add(Where.equal("edad", 14));
		Objects<Jugador> jugadores = odb.getObjects(new CriteriaQuery(Jugador.class, criterio));

		System.out.println("\nJugadores de 14 años de IRLANDA, FRANCIA e ITALIA:");
		while (jugadores.hasNext()) {
			System.out.println(jugadores.next());
		}
		odb.close();
	}

	// Método para actualizar edades de jugadores de un país
	public static void actualizarEdades(String nombrePais) {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		ICriterion criterio = Where.equal("pais.nombrepais", nombrePais);
		Objects<Jugador> jugadores = odb.getObjects(new CriteriaQuery(Jugador.class, criterio));

		if (jugadores.isEmpty()) {
			System.out.println("\nNo hay jugadores del país " + nombrePais + ".");
		} else {
			while (jugadores.hasNext()) {
				Jugador jugador = jugadores.next();
				jugador.setEdad(jugador.getEdad() + 2);
				odb.store(jugador);
			}
			System.out.println("\nLas edades de los jugadores de " + nombrePais + " se han actualizado.");
		}
		odb.close();
	}

	// Método para visualizar jugadores de un país y deporte
	public static void visualizarJugadoresPorPaisYDeporte(String nombrePais, String deporte) {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		ICriterion criterio = new And()
				.add(Where.equal("pais.nombrepais", nombrePais))
				.add(Where.equal("deporte", deporte));
		Objects<Jugador> jugadores = odb.getObjects(new CriteriaQuery(Jugador.class, criterio));

		System.out.println("\nJugadores de " + nombrePais + " que practican " + deporte + ":");
		if (jugadores.isEmpty()) {
			System.out.println("No hay jugadores.");
		} else {
			while (jugadores.hasNext()) {
				System.out.println(jugadores.next());
			}
		}
		odb.close();
	}

	// Método para borrar un país de la base de datos
	public static void borrarPais(String nombrePais) {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		ICriterion criterioPais = Where.equal("nombrepais", nombrePais);
		Objects<Paises> paises = odb.getObjects(new CriteriaQuery(Paises.class, criterioPais));

		if (paises.isEmpty()) {
			System.out.println("\nEl país " + nombrePais + " no existe en la base de datos.");
		} else {
			Paises pais = paises.getFirst();
			ICriterion criterioJugadores = Where.equal("pais.nombrepais", nombrePais);
			Objects<Jugador> jugadores = odb.getObjects(new CriteriaQuery(Jugador.class, criterioJugadores));

			if (!jugadores.isEmpty()) {
				System.out.println("\nEl país tiene jugadores asignados. Asignando null a sus países...");
				while (jugadores.hasNext()) {
					Jugador jugador = jugadores.next();
					jugador.setPais(null);
					odb.store(jugador);
				}
			}
			odb.delete(pais);
			System.out.println("\nEl país " + nombrePais + " ha sido eliminado de la base de datos.");
		}
		odb.close();
	}
}
