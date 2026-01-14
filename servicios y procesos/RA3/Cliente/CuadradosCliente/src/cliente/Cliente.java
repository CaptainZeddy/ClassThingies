package cliente;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	private static Socket socket;
	private static final String clienteIp="localhost";
	private static final int puerto=49171;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public Cliente(String ip, int puerto) {
	}


	public void start() throws UnknownHostException, IOException {
		System.out.println("Iniciando conexion");
		socket = new Socket(clienteIp, puerto);
		is = socket.getInputStream();
		os = socket.getOutputStream();
		dis= new DataInputStream(is);
		dos= new DataOutputStream(os);
		System.out.println("Conexion establecida.");
		
	}
	
	public void close() throws IOException {
		System.out.println("Cerrando conexion.");
		socket.close();
		is.close();
		os.close();
		dis.close();
		dos.close();
		System.out.println("Conexion terminada.");
	}

	

	public static void main(String[] args) throws IOException {
		Cliente cliente= new Cliente(clienteIp, puerto);
		int numero=300;
		int respuesta=0;
		try {
			cliente.start();
			System.out.println("Numero mandado: "+numero);
			cliente.dos.writeInt(numero);
			respuesta=cliente.dis.readInt();			
			System.out.println("Numero devuelto: "+respuesta);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			cliente.close();
		}
		
		
	}

}
