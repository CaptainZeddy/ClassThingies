package uppercase;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class upper {

	private String serverIP;
	private int serverPort;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	public upper(String serverIP, int serverPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
	}

	public void start() throws UnknownHostException, IOException {
		System.out.println("(Cliente) Estableciendo conexión...");
		socket = new Socket(serverIP, serverPort);//Creo un socket con la ip local y puerto
		os =  socket.getOutputStream();
		is =  socket.getInputStream();
		dos = new DataOutputStream(os);
		dis = new DataInputStream(is);
		System.out.println("(Cliente) Conexión establecida.");
	}

	public void stop() throws IOException {
		System.out.println("(Cliente) Cerrando conexiones...");
		is.close();
		os.close();
		dis.close();
		dos.close();
		socket.close();
		System.out.println("(Cliente) Conexiones cerradas...");
	}

	public static void main(String[] args) {
		upper cliente = new upper("192.168.1.184", 49171);//Pongo la IP de mi ordenador en el que lo ejecuto
		try {
			cliente.start();
			cliente.dos.writeUTF("TEXTO EN MAYUSCULAS");//Mando 100 al servidor (output)
			System.out.println("Mensaje del servidor: " + cliente.dis.readUTF());//Leo entrada de datos del servidor (intput) y lo muestro
			cliente.stop();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}