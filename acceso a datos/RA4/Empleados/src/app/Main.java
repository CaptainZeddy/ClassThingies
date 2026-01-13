package app;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

import pojo.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ODB odb = ODBFactory.open("personal.db");
        java.sql.Date fecha = new java.sql.Date(System.currentTimeMillis());

        // A) Insertar un departamento
        Departamento dep1 = new Departamento(10, "Ventas", "Madrid");
        odb.store(dep1);

        // B) Insertar un empleado
        Empleado emp1 = new Empleado(1, "Gómez", "Vendedor",  fecha, 1200.50f, 200.0f, null, dep1);
        odb.store(emp1);
        
        Empleado emp2 = new Empleado(2, "Ramos", "Vendedor",  fecha, 1200.50f, 200.0f, emp1, dep1);
        odb.store(emp2);

        // C) Visualizar todos los departamentos
        Objects<Departamento> departamentos = odb.getObjects(Departamento.class);
        
        while (departamentos.hasNext()) {
            Departamento d = departamentos.next();
            System.out.println(d);
        }

        // D) Visualizar todos los empleados
        Objects<Empleado> empleados = odb.getObjects(Empleado.class);
        while (empleados.hasNext()) {
            Empleado e = empleados.next();
            //evaluar expresión? si verdadero:si no
            System.out.println(e.getApellido() + " - " + (e.getJefe() != null ? e.getJefe().getApellido() : "Sin jefe"));
            
        }

        odb.close();//cierro bbdd
    }
}