package mensajesUDPserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {

	private static final int puerto = 49171;
	private DatagramSocket socket;

	public void start() throws SocketException {
		System.out.println("Creando conexion");
		socket= new DatagramSocket(puerto);
	}

	public void close() {
		socket.close();
		System.out.println("Cerrando conexion");
	}

	public DatagramPacket recibirMensaje() {
		byte[] buffer = new byte[1024];
		DatagramPacket paquete= new DatagramPacket(buffer, buffer.length);

		try {
			socket.receive(paquete);

			String mensaje = new String(
					paquete.getData(),
					0,
					paquete.getLength()
					);

			System.out.println("Mensaje recibido: " + mensaje);

		}catch (IOException e) {
			e.printStackTrace();
		}
		return paquete;
	}

	public void mandarMensaje(DatagramPacket entrada) {
		String recibido = new String(
				entrada.getData(),
				0,
				entrada.getLength()
				);

		String respuesta = recibido + " ESTE MENSAJE HA SIDO RECIBIDO.";
		byte[] salida = respuesta.getBytes();

		DatagramPacket paquete = new DatagramPacket(
				salida,
				salida.length,
				entrada.getAddress(),
				entrada.getPort()
				);
		try {
			socket.send(paquete);

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Mensaje enviado.");
	}

	public static void main(String[] args) {
		Server server = new Server();

		try {
			server.start();

			DatagramPacket entrada= server.recibirMensaje();
			server.mandarMensaje(entrada);


		} catch (SocketException e) {
			e.printStackTrace();
		}finally {
			server.close();
		}

	}

}
