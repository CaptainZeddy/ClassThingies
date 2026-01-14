package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Servidor {

	private static ServerSocket serverSocket;
	private static Socket socket;
	private static int puerto=49171;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	public Servidor(int puerto) throws IOException {
		serverSocket = new ServerSocket(puerto);
	}

	public void start() throws UnknownHostException, IOException {
		System.out.println("Iniciando conexion");
		socket = serverSocket.accept();
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
		Servidor servidor= new Servidor(puerto);

		try {
			

			servidor.start();
			int mensaje= servidor.dis.readInt();
			System.out.println("Numero recibido: "+mensaje);
			System.out.println("Numero mandado: "+mensaje*mensaje );
			servidor.dos.writeInt(mensaje*mensaje);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			servidor.close();
		}
	}

}
