package Jaxb.PruebaUT1;


import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class GestionStockApp {

	// --- Rutas de Ficheros ---
	private static final String RUTA_CSV = "recursos/stock_catalogo.csv";
	private static final String RUTA_PRECIOS_BIN = "recursos/precios.dat";
	private static final String RUTA_STOCK_OBJ = "recursos/stock.dat";
	private static final String RUTA_XML = "recursos/catalogo.xml";
	private static final String RUTA_RESUMEN = "recursos/resumen.txt";
	private static final String RUTA_CATALOGO_CSV="recursos/catalogo.csv";

	public static void main(String[] args) throws IOException {
		// 1. Datos de prueba
		List<Articulo> stockInicial = new ArrayList<>();
		stockInicial.add(new Articulo("P001", "Monitor Curvo 27", 15, 299.99));
		stockInicial.add(new Articulo("P002", "Teclado Mecánico", 0, 75.50)); // Precio a modificar en Tarea 5
		stockInicial.add(new Articulo("P003", "Disco SSD 1TB", 30, 89.90));
		stockInicial.add(new Articulo("P004", "Webcam 4K", 5, 120.00));


		// Crear directorio de recursos si no existe
		new File("recursos").mkdirs();

		System.out.println("--- INICIANDO FLUJO DE PRUEBA COMPLETO ---\n");

		exportarA_CSV(stockInicial, RUTA_CATALOGO_CSV);
		System.out.println("[T.2] Artículos con stock 0: " + contarLineasNoVacias(RUTA_CATALOGO_CSV) + "\n"); // Tarea 2

		// --- II. Ficheros Binarios Primitivos y Aleatorio ---
		guardarID_Precio(stockInicial, RUTA_PRECIOS_BIN); // Tarea 3 (Ahora con RandomAccessFile)

		System.out.println("--- [T.4] VERIFICACIÓN BINARIO (ANTES DE MODIFICAR) ---");
		leerFicheroPreciosBinario(RUTA_PRECIOS_BIN); // Tarea 4 (Con RandomAccessFile)

		modificarPrecioPorPosicion(RUTA_PRECIOS_BIN, 2, 85.00); // Tarea 5
		System.out.println("\n[T.5] Precio del artículo 2 modificado a 85.00€ con RandomAccessFile.");

		System.out.println("--- [T.4] VERIFICACIÓN BINARIO (DESPUÉS DE MODIFICAR) ---");
		leerFicheroPreciosBinario(RUTA_PRECIOS_BIN); // Tarea 4 (Verificación pos-modificación)

		// --- III. Serialización y JAXB ---
		System.out.println("\n--- INICIANDO SERIALIZACIÓN (T.6) ---");
		// Primero, creamos el fichero
		guardarSerializado(stockInicial, RUTA_STOCK_OBJ); // Tarea 6
		// Luego, añadimos un nuevo artículo 
		List<Articulo> nuevoArticulo = new ArrayList<>();
		nuevoArticulo.add(new Articulo("P005", "Ratón Inalámbrico", 12, 25.00));
		guardarSerializado(nuevoArticulo, RUTA_STOCK_OBJ);

		System.out.println("--- [T.7] VERIFICACIÓN DE OBJETOS SERIALIZADOS ---");
		leerFicheroObjetos(RUTA_STOCK_OBJ); // Tarea 7

		convertirBinarioA_XML(RUTA_STOCK_OBJ, RUTA_XML); // Tarea 8

		try {
			generarResumen(RUTA_XML, RUTA_RESUMEN);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Tarea 9
		System.out.println("[T.9] Reporte final generado en: " + RUTA_RESUMEN);
		System.out.println("\n--- FIN DEL EXAMEN ---");
	}

	// =========================================================================
	// I. FICHEROS DE TEXTO
	// =========================================================================

	// Tarea 1: Exportación a Fichero de Texto (CSV)
	public static void exportarA_CSV(List<Articulo> lista, String ruta) {
		try (FileWriter fw= new FileWriter(ruta)){
			fw.write("CODIGO;NOMBRE;STOCK;PRECIO\n");

			for(Articulo a: lista) {
				fw.write(a.toString()+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Tarea 2: Recuperación de Texto y Documentación (Contar Stock 0)
	public static int contarLineasNoVacias(String rutaFichero) {
		int cuenta=0;
		try(BufferedReader br= new BufferedReader(new FileReader(rutaFichero))){
			String resultado="";

			while((resultado=br.readLine())!=null) {

				if(resultado.contains(";0;")) {
					cuenta++;
				}

			}

			//Este metodo esta creado para leer el archivo que se pasa por parametro. Se emplea BufferedReadaer con la ruta como parametro en un FileReader
			//Se asegura que se lea hasta el final del fichero y deje de hacerlo cuando la linea leida este vacia, y si en la lectura se detecta el dato
			//buscado, se suma al contador de stock vacio
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		return cuenta;

	}

	// =========================================================================
	// II. FICHEROS BINARIOS PRIMITIVOS Y ACCESO ALEATORIO (TODO RandomAccessFile)
	// =========================================================================

	// Tarea 3: Almacenamiento de Datos Primitivos
	public static void guardarID_Precio(List<Articulo> lista, String rutaFichero) {
		try (RandomAccessFile raf= new RandomAccessFile(rutaFichero, "rw")){
			raf.seek(0);
			for(Articulo a: lista) {
				StringBuffer sb=new StringBuffer(a.getCodigo());
				sb.setLength(10);
				raf.writeChars(sb.toString());
				raf.writeDouble(a.getPrecio());
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Tarea 4: Verificación de Fichero Binario
	public static void leerFicheroPreciosBinario(String rutaFichero) {
		try (RandomAccessFile raf = new RandomAccessFile(rutaFichero, "r")) {
			raf.seek(0);
			String resultado="";
			
			//En este metodo empezamos poniendo el puntero al inicio del fichero con la funcion seek() y detallamos la cantidad de registros con la division
			//de la longitud del archivo en bytes entre el tamaño de los registros
			
			long numRegistros = raf.length()/28; 

			//se crean dos bucles anidados, el exterior para saltar de registro (28 bytes) y el segundo para leer la cadena de caracteres del registro
			//(en este caso 10 chars es decir 20 bytes)
			
			for (int i = 0; i < numRegistros; i++) {
				String codigo ="";

				for (int j = 0; j < 10; j++) {
					codigo+=raf.readChar();
				}

				double precio = raf.readDouble();
				resultado+=codigo.trim()+" "+precio+"\n";
			}
			
			//se extraen los resultados y se suman en un string, cortando primero los espacios sobrantes del codigo.
			
			System.out.println(resultado);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Tarea 5: Modificación con Posicionamiento Aleatorio
	public static void modificarPrecioPorPosicion(String rutaFichero, int posicion, double nuevoPrecio) {
		try (RandomAccessFile raf= new RandomAccessFile(rutaFichero, "rw")){

			//Se busca primero la posicion a cambiar: la posicion buscada -1 porque empieza desde 0, por 28, que es el tamaño de los registros y finalmente +20
			//que es lo que ocupa la parte del String y no nos interesa modificar.
			
			raf.seek((posicion-1)*28+20);
			raf.writeDouble(nuevoPrecio);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	// =========================================================================
	// III. SERIALIZACIÓN DE OBJETOS Y CONVERSIÓN JAXB
	// =========================================================================

	// Tarea 6: Almacenamiento y Añadido de Objetos
	public static void guardarSerializado(List<Articulo> articulos, String ruta) {
		//		try{
		//			OutputStream os=new out
		//			ObjectOutputStream oos=new ObjectOutputStream(os)
		//		}catch (Exception e) {
		//			e.printStackTrace();
		//		}
		//
	}

	// Tarea 7: Verificación de Serialización
	public static void leerFicheroObjetos(String rutaFichero) {

	}

	// Método auxiliar para Tarea 8 (reutiliza lógica de Tarea 7)
	private static List<Articulo> leerTodosLosObjetos(String rutaFichero) {
		return null;

	}

	// Tarea 8: Conversión Objeto Serializado a XML
	public static void convertirBinarioA_XML(String rutaBinario, String rutaXML) {

	}

	// Tarea 9: Conversión XML a Reporte Final
	public static void generarResumen(String rutaXML, String rutaSalida) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Almacen.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Almacen almacen = (Almacen) unmarshaller.unmarshal(new File(rutaXML));

		List<Articulo> articulos = almacen.getArticulos();
		
		articulos.add(new Articulo("P001", "Monitor Curvo 27", 15, 299.99));
		articulos.add(new Articulo("P002", "Teclado Mecánico", 0, 75.50)); 
		articulos.add(new Articulo("P003", "Disco SSD 1TB", 30, 89.90));
		articulos.add(new Articulo("P004", "Webcam 4K", 5, 120.00));
		
		double sumaPrecios = 0.0;
		try (FileWriter fw= new FileWriter(rutaSalida)){
			for (Articulo articulo : articulos) {
				sumaPrecios += articulo.getPrecio();
			}
			double promedio = sumaPrecios / articulos.size();

			fw.write("\n--REPORTE DE ARTÍCULOS CON PRECIO SUPERIOR AL PROMEDIO--\nPrecio promedio del catálogo:"+ promedio+"\n");

			for (Articulo articulo : articulos) {
				if (articulo.getPrecio() > promedio) {
					fw.write(articulo.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
