package aplicacion;

//Paquete: com.catalogo.productos.main

import dao.ProductoDAOImpl;
import modelo.Producto;
import servicio.ProductoService;
import util.HibernateUtil;
import java.util.Scanner;

public class MenuProducto {

	private static ProductoService productoService;
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// Inicializar el Service y el DAO
		productoService = new ProductoService(new ProductoDAOImpl());
		productoService.iniciar();
		mostrarMenu();

		// Cerrar la SessionFactory al finalizar la aplicación
		HibernateUtil.shutdown();
	}

	public static void mostrarMenu() {
		int opcion = -1;

		while (opcion != 0) {
			System.out.println("\n--- Gestión de Productos ---");
			System.out.println("1. Crear Producto");
			System.out.println("2. Buscar Producto por ID");
			System.out.println("3. Modificar Producto");
			System.out.println("4. Eliminar Producto");
			System.out.println("0. Salir");
			System.out.print("Opción: ");

			try {
				opcion = sc.nextInt();
				sc.nextLine(); // Limpiar buffer

				switch (opcion) {
				case 1:
					crear();
					break;
				case 2:
					leer();
					break;
				case 3:
					actualizar();
					break;
				case 4:
					eliminar();
					break;
				case 0:
					System.out.println("Saliendo del sistema...");
					break;
				default:
					System.out.println("Opción no válida.");
				}
			} catch (Exception e) {
				System.out.println("Error de entrada. Intente de nuevo.");
				sc.nextLine(); // Limpiar la línea de error
				opcion = -1;
			}
		}
	}

	private static void crear() {
		Producto p = new Producto();
		System.out.print("Nombre: ");
		p.setNombre(sc.nextLine());
		System.out.print("Descripción: ");
		p.setDescripcion(sc.nextLine());
		System.out.print("Precio: ");
		p.setPrecio(sc.nextBigDecimal());
		sc.nextLine();

		try {
			productoService.registrarProducto(p);
			System.out.println("Producto creado con ID: " + p.getId());
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("Error al crear: " + e.getMessage());
		}
	}

	private static void leer() {
		System.out.print("ID del Producto a buscar: ");
		Long id = sc.nextLong();
		sc.nextLine();

		Producto p = productoService.obtenerProducto(id);

		if (p != null) {
			System.out.println(p.toString());
			System.out.println("Descripción: " + p.getDescripcion());
		} else {
			System.out.println("No existe el producto con ID " + id);
		}
	}

	private static void actualizar() {
		System.out.print("ID del Producto a modificar: ");
		Long id = sc.nextLong();
		sc.nextLine();

		Producto p = productoService.obtenerProducto(id);
		if (p == null) {
			System.out.println("No existe el producto.");
			return;
		}

		System.out.println("Producto actual: " + p.getNombre() + ". Ingrese nuevo nombre (o Enter para mantener):");
		String nuevoNombre = sc.nextLine();
		if (!nuevoNombre.isEmpty())
			p.setNombre(nuevoNombre);

		System.out.println("Precio actual: " + p.getPrecio() + ". Ingrese nuevo precio:");
		p.setPrecio(sc.nextBigDecimal());
		sc.nextLine();

		try {
			productoService.modificarProducto(p);
			System.out.println("Producto ID " + id + " actualizado.");
		} catch (Exception e) {
			System.out.println("Error al actualizar: " + e.getMessage());
		}
	}

	private static void eliminar() {
		System.out.print("ID del Producto a eliminar: ");
		Long id = sc.nextLong();
		sc.nextLine();

		try {
			productoService.eliminarProducto(id);
			System.out.println("Producto ID " + id + " eliminado.");
		} catch (Exception e) {
			System.out.println("Error al eliminar: " + e.getMessage());
		}
	}
}