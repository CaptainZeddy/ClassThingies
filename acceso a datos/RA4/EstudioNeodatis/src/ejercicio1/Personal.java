package ejercicio1;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.management.Query;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import pojos.Departamento;
import pojos.Empleado;



public class Personal {
	private static final String db ="personal.db";


	public static void main(String[] args) {
		File file= new File(db);
		if(file.exists()) file.delete();
		
		ODB odb = ODBFactory.open(db);

	
		
		// 📌 Insertar Departamentos
		Departamento dep1 = new Departamento(10, "Ventas", "Madrid");
		Departamento dep2 = new Departamento(20, "Informática", "Barcelona");
		Departamento dep3 = new Departamento(30, "Recursos Humanos", "Sevilla");
		odb.store(dep1);
		odb.store(dep2);
		odb.store(dep3);

		// 📌 Insertar Empleados
		Empleado jefe1 = new Empleado(1, "López", "Gerente", new Date(), 2500.0f, 0, null, dep1);  // Jefe
		Empleado emp1 = new Empleado(2, "Gómez", "Vendedor", new Date(), 1200.0f, 200.0f, jefe1, dep1);
		Empleado emp2 = new Empleado(3, "Martínez", "Vendedor", new Date(), 900.0f, 100.0f, jefe1, dep1);
		Empleado emp3 = new Empleado(4, "Fernández", "Programador", new Date(), 1800.0f, 0, null, dep2);
		Empleado emp4 = new Empleado(5, "Sánchez", "Técnico", new Date(), 1600.0f, 150.0f, null, dep2);
		Empleado emp5 = new Empleado(6, "Pérez", "Recursos Humanos", new Date(), 1100.0f, 50.0f, null, dep3);

		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.JUNE, 15); // 15 de junio de 2015
		Date fecha = calendar.getTime();
		Empleado emp6 = new Empleado(7, "Luisa", "Recursos Humanos", fecha, 1100.0f, 50.0f, null, dep3);

		odb.store(jefe1);
		odb.store(emp1);
		odb.store(emp2);
		odb.store(emp3);
		odb.store(emp4);
		odb.store(emp5);
		odb.store(emp6);



		System.out.println("✅ Datos insertados correctamente.");


		odb.close();


		//	        obtenerJugadores();
		//		obtenerDepartamento();
//		obtenerSalarioSuperiorA1000();
//		nombresDep10();
//		pedro();
		depVentas();
	}

	public static void obtenerJugadores() {
		ODB odb= ODBFactory.open(db);
		IQuery query= new CriteriaQuery(Empleado.class);

		Objects<Empleado> objeto= odb.getObjects(query);

		while(objeto.hasNext()) {
			Empleado e= objeto.next();
			System.out.println(e);
		}
		odb.close();
	}

	public static void obtenerDepartamento() {
		ODB odb= ODBFactory.open(db);
		IQuery query= new CriteriaQuery(Departamento.class);

		Objects<Departamento> objeto= odb.getObjects(query);

		while(objeto.hasNext()) {
			Departamento d= objeto.next();
			System.out.println(d);
		}

		odb.close();
	}

	public static void obtenerSalarioSuperiorA1000() {
		ODB odb= ODBFactory.open(db);

		ICriterion criterio= Where.gt("salario", 1000);

		CriteriaQuery query= new CriteriaQuery(Empleado.class, criterio);

		Objects<Empleado> empleado = odb.getObjects(query);

		while(empleado.hasNext()) {
			Empleado e= empleado.next();
			System.out.println(e);
		}

		odb.close();
	}
	
	public static void nombresDep10() {
		ODB odb= ODBFactory.open(db);

		ICriterion criterio= Where.equal("departamento.id_dep",10);

		CriteriaQuery query= new CriteriaQuery(Empleado.class, criterio);

		Objects<Empleado> empleado = odb.getObjects(query);

		while(empleado.hasNext()) {
			Empleado e= empleado.next();
			System.out.println(e);
		}

		odb.close();
	}
	
	public static void pedro() {
		ODB odb= ODBFactory.open(db);

		ICriterion criterio= Where.equal("jefe.apellido", "López");

		CriteriaQuery query= new CriteriaQuery(Empleado.class, criterio);

		Objects<Empleado> empleado = odb.getObjects(query);

		while(empleado.hasNext()) {
			Empleado e= empleado.next();
			System.out.println(e);
		}

		odb.close();
	}
	
	public static void depVentas() {
		ODB odb= ODBFactory.open(db);

		ICriterion criterio= Where.equal("departamento.nombre","Ventas");

		CriteriaQuery query= new CriteriaQuery(Empleado.class, criterio);

		Objects<Empleado> empleado = odb.getObjects(query);

		while(empleado.hasNext()) {
			Empleado e= empleado.next();
			System.out.println(e);
		}

		odb.close();
	}
}
