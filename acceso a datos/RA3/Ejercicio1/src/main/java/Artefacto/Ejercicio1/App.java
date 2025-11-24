package Artefacto.Ejercicio1;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Modelo modelo= new Modelo();
        
        Empleado e1= new Empleado(1, "Juan", "I+D", 60.0);
        
        modelo.insertar(e1);
        
        Empleado e2 = modelo.buscar(1);
        System.out.println(e2);
    }
}
