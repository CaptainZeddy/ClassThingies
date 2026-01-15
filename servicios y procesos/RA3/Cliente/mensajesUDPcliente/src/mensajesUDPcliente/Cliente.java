package mensajesUDPcliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	private static final int puerto=49171;
	private static final String ip="localhost";
	private DatagramSocket socket;
	private InetAddress direccionServidor;

	public void start() throws SocketException, UnknownHostException {
		System.out.println("Creando socket UDP.");
		socket = new DatagramSocket();
		direccionServidor= InetAddress.getByName(ip);
	}

	public void close() {
		socket.close();
		System.out.println("Socket cerrado.");
	}

	public void mandarMensaje(String mensaje) throws IOException {
		byte[] buffer= mensaje.getBytes();

		DatagramPacket paquete= new DatagramPacket(buffer, buffer.length, direccionServidor, puerto);
		socket.send(paquete);
		System.out.println("Mensaje enviado.");		
	}

	public void recibirMensaje() {
		byte[] buffer = new byte[1024];
		
		try {
			DatagramPacket paquete= new DatagramPacket(buffer, buffer.length);
			
			socket.setSoTimeout(4000);
			socket.receive(paquete);
			String mensaje = new String(
			            paquete.getData(),
			            0,
			            paquete.getLength()
			        );
			System.out.println(mensaje);
		}catch (SocketTimeoutException e) {
			System.out.println("Se ha agotado el tiempo de espera");		
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Se ha recibido el mensaje.");
		
	}

	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		Cliente cliente = new Cliente();
		String texto="";
		
		try {
			cliente.start();
			System.out.println("Escribe texto y para con un *:");
			
			texto=scan.nextLine();
			cliente.mandarMensaje(texto);
			cliente.recibirMensaje();

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			cliente.close();
		}
	}
}
