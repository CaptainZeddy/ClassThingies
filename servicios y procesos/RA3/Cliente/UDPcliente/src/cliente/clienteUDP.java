package cliente;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;



public class clienteUDP {
	public static DatagramSocket socketUDP;
	public static Scanner scan= new Scanner(System.in);
	static InetAddress hostServidor;
	static int puertoservidor = 49171;
	static byte[] mensaje;

	public static String mensaje() {
		String enviar="";
		String validar="";
		System.out.println("Escriba cosas y * para terminar: ");

		while(!validar.equals("*")) {

			validar=scan.nextLine();
			enviar+=validar+"\n";

		}
		return enviar;
	}

	public static void conectar() throws UnknownHostException {
		System.out.println("(Cliente): Estableciendo parámetros de conexión...");
		//se obtiene la dirección del servidor con getByName de InetAddress
		hostServidor = InetAddress.getByName("localhost");
		System.out.println("(Cliente): Creando socket…");
	}

	public static void desconectar() {
		System.out.println ("(Cliente): Cerrando socket…");
		//se cierra el socket
		socketUDP.close();
		System.out.println ("(Cliente): Socket cerrado.");
	}

	public static void crearSocket() throws SocketException {
		//se crea el socket UDP mediante un Datagramsocket
		socketUDP = new DatagramSocket();
		System.out.println ("(Cliente): Enviando datagrama..") ;
		mensaje = mensaje().getBytes();
	}
	
	public static void enviar() throws IOException {
		//se genera el datagrama mediante un Datagrampacket con el mensaje a enviar
		DatagramPacket peticion = new DatagramPacket(mensaje, mensaje.length,
				hostServidor, puertoservidor);

		//se envía el mensaje
		socketUDP.send(peticion) ;
		System.out.println (" (Cliente) : Recibiendo datagrama.") ;
	}
	
	public static void recibirYcerrar() throws IOException {
		//se crea el array de byte para recibir la respuesta, correctamente dimensionado
		byte[] buffer = new byte[64];
		DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, hostServidor,
				puertoservidor);

		socketUDP.setSoTimeout(4000);

		try {
			//se recibe la respuesta
			socketUDP.receive(respuesta);
			System.out.println ("(Cliente): Mensaje recibido:" + new String(buffer));

		}catch(SocketTimeoutException e) {
			System.out.println("Se ha agotdo el tiempo de espera. ");
		}finally {
			desconectar();
		}
	}
	
	public static void main (String[] args) {
		DatagramSocket socketUDP;
		try {

			conectar();
			
			crearSocket();
			
			enviar();

			recibirYcerrar();

		}catch (SocketException e) {
			e.printStackTrace() ;
		} catch (UnknownHostException e) {
			e.printStackTrace() ;
		}catch (IOException e) {
			e.printStackTrace() ;
		}
	}
}