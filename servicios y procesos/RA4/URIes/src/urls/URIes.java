package urls;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class URIes {
	private static final String ruta="url.html";
	private static Scanner scan= new Scanner(System.in);

	public static void main(String[] args) {

		try {
			System.out.println("Introduzca una URL valida: ");
			String link= scan.nextLine();

			URL url= URI.create(link).toURL();

			HttpURLConnection conexion= (HttpURLConnection) url.openConnection();
			conexion.setRequestMethod("GET");

			conexion.setRequestProperty("User-Agent", "Mozilla/5.0");

			int codigoRespuesta= conexion.getResponseCode();
			System.out.println("Codigo de respuesta del server: "+codigoRespuesta);


			if(codigoRespuesta==HttpURLConnection.HTTP_OK) {

				File file= new File(ruta);
				
				if (file.exists()) file.delete();
				
				InputStream is=conexion.getInputStream();
				InputStreamReader lector= new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader buffer= new BufferedReader(lector);

				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(ruta), "utf-8"));
				
				String linea;

				while((linea= buffer.readLine())!=null) {
					writer.write(linea);
				}
				writer.close();

			}

		}catch (Exception e) {
			System.out.println("Han pasao cosas malas: "+ e.getMessage());
		}

	}

}
