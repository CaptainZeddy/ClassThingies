package cliente;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

public class Cliente {
	private static final String URL_API = "http://localhost:8080/api/productos";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcion = 0;

		System.out.println("--- GESTIÓN DE STOCK 'TECHSTORE' ---");

		while (opcion != 6) {
			System.out.println("\n1. Ver Catálogo (GET)");
			System.out.println("2. Nuevo Producto (POST)");
			System.out.println("3. Actualizar Stock/Precio (PUT)");
			System.out.println("4. Descatalogar (DELETE)");
			System.out.println("5. Buscar por Marca ");
			System.out.println("6. Salir");
			System.out.print("Elige una opción: ");

			try {
				opcion = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) { opcion = 0; }

			switch (opcion) {
			case 1:
				hacerGet(null);
				break;
			case 2:
				System.out.print("Nombre: ");
				String nombre = scanner.nextLine();
				System.out.print("Marca: ");
				String marca = scanner.nextLine();
				// Leemos como String y parseamos para evitar problemas con el scanner
				System.out.print("Precio (con punto): ");
				double precio = Double.parseDouble(scanner.nextLine());
				System.out.print("Stock: ");
				int stock = Integer.parseInt(scanner.nextLine());
				hacerPost(nombre, marca, precio, stock);
				break;
			case 3:
				System.out.print("ID del producto a modificar: ");
				long idPut = Long.parseLong(scanner.nextLine());
				System.out.print("Nuevo Precio: ");
				double nPrecio = Double.parseDouble(scanner.nextLine());
				hacerPut(idPut, nPrecio);
				break;
			case 4:
				System.out.print("ID del producto a borrar: ");
				long idDel = Long.parseLong(scanner.nextLine());
				hacerDelete(idDel);
				break;
			case 5:
				System.out.print("Introduce la marca a buscar: ");
				String marcaBusqueda = scanner.nextLine();
				hacerGet(marcaBusqueda);
				break;
			case 6:
				System.out.println("Cerrando sistema...");
				break;
			default:
				System.out.println("Opción no válida.");
			}
		}
		scanner.close();
	}

	private static void hacerDelete(long idDel) {
		try {
			String turboURL=URL_API;
			turboURL = turboURL +"/"+ idDel;
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
				    .uri(URI.create(turboURL))
				    .DELETE()
				    .build();
			
			HttpResponse<String> response =
				    client.send(request, BodyHandlers.ofString());
			
			System.out.println("Se han borrado cosas:\n"+response.body());
			
		}catch (Exception e) {
			System.out.println("Ha ocurrido un error: "+e.getMessage());
		}
	}

	private static void hacerPut(long idPut, double nPrecio) {
		try {
			String turboURL=URL_API;
			turboURL = turboURL + "/" + idPut;
			
			String json = "{"
					  + "\"precio\":\"" +nPrecio+"\""
					  + "}";

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
				    .uri(URI.create(turboURL))
				    .PUT(HttpRequest.BodyPublishers.ofString(json))
				    .header("Content-Type", "application/json")
				    .build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			System.out.println("Han pasao cosas: "+ response.body());
			
			
		}catch (Exception e) {
			System.out.println("Ha ocurrido un error: "+e.getMessage());
		}
	}

	private static void hacerPost(String nombre, String marca, double precio, int stock) {
		try {
			
			String json = "{"
					  + "\"nombre\":\"" + nombre+ "\","
					  + "\"marca\":\"" + marca+ "\","
					  + "\"precio\":" + precio
					  + "}";
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(URL_API))
					.POST(HttpRequest.BodyPublishers.ofString(json))
					.header("Content-Type", "application/json")
					.build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			System.out.println("Producto registrado: "+response.body());
			
		}catch (Exception e) {
			System.out.println("Ha ocurrido un error: "+e.getMessage());
		}
	}

	private static void hacerGet(String marca) {
		try {
			String turboURL = URL_API;
			if (marca!=null) {
				turboURL= turboURL+"?marca="+marca;
			}
			
			HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(turboURL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json= response.body();
            if (json.equals("[]")) {
            	System.out.println("No hay cosas disponibles...");
            }else {
            	System.out.println(json.replace("},{", "},\n{"));
            }
            
		}catch (Exception e) {
			System.out.println("Ha ocurrido un error: "+e.getMessage());
		}
	}
}
