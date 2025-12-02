package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class serverUDP{
	
	public static String toUpper(String cadena) {
		String resultado="";
		resultado+=cadena.toUpperCase();		
		return resultado;
	}
	
	public static void main (String [] args){
				
		DatagramSocket socket;
		try {
			System.out.println(" (Servidor) : Creando socket..");
			
			//datagramsocket asociado al puerto
			socket = new DatagramSocket (49171) ;
			System.out.println("(Servidor): Recibiendo datagrama…");
			
			//array de bytes para almacenar el mensaje y el datagrampacket pasándole el array
			byte[] bufferLectura = new byte [100];
			DatagramPacket datagramaEntrada = new DatagramPacket(bufferLectura, bufferLectura.length);
			
			//el socket recibe el datagrama, el servidor queda a la espera
			socket.receive(datagramaEntrada);
			System.out.println("(Servidor):Mensaje recibido: " + new String(bufferLectura));
			System.out.println("(Servidor):Enviando datagrama…");
			byte [] mensajeEnviado = new String(toUpper(new String(bufferLectura))).getBytes();
			
			DatagramPacket datagramaSalida = new DatagramPacket(mensajeEnviado,mensajeEnviado.length,
					datagramaEntrada.getAddress(),datagramaEntrada.getPort());
			
			//envía el datagrama de respuesta cuando se ha terminado la comunicación.
			socket.send (datagramaSalida);
			System.out.println("(Servidor): Cerrando socket ...") ;
			
			//se cierra el socket
			socket.close() ;
			System.out.println ("(Servidor): Socket cerrado.");
		} catch (SocketException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}