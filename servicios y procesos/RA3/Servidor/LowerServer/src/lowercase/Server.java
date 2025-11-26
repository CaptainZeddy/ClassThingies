package lowercase;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serversocket;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	// por defecto se asigna la ip de la maquina y un puerto
	public Server(int puerto) throws IOException {
		serversocket = new ServerSocket(puerto);
	}
	
	public void start() throws IOException{
		System.out.println("(Servidor) Esperando conexiones...");
		socket=serversocket.accept();
		is= socket.getInputStream();
		os=socket.getOutputStream();
		dos = new DataOutputStream(os);
		dis = new DataInputStream(is);
		System.out.println("(Servidor) Conexión establecida.");
	}
	
	public void stop() throws IOException{
		System.out.println("(Servidor) Cerrando conexiones...");
		is.close();
		os.close();
		dis.close();
		dos.close();
		socket.close();
		serversocket.close();
		System.out.println("(Servidor) Conexiones cerradas...");
	}
	
	public static String toLowerCase(String upper) {
		String lower=upper.toLowerCase();
		return lower;
	}
	
	public static void main(String[] args) {
		try {
			Server servidor= new Server(49171);
			servidor.start();
			System.out.println("Texto enviado: "+servidor.dis.readUTF());
			String resultado=servidor.dis.readUTF();//Leo el mensaje de entrada (input) del cliente y lo muestro
			servidor.dos.writeUTF(toLowerCase(resultado));//escribo mensaje 200 (output)
			servidor.stop();
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}