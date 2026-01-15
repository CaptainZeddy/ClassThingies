package multi;

import java.net.*;
import java.util.Scanner;

public class Server {

    private static final String IP_GRUPO = "225.0.0.1";
    private static final int PUERTO = 49171;

    private MulticastSocket socket;
    private InetAddress grupo;

    // 1️⃣ Crear socket y grupo
    public void start() throws Exception {
        System.out.println("(Servidor) Creando socket multicast...");
        socket = new MulticastSocket();
        grupo = InetAddress.getByName(IP_GRUPO);
    }

    // 2️⃣ Enviar mensaje al grupo
    public void enviarMensaje(String mensaje) throws Exception {
//        byte[] datos = mensaje.getBytes();
        
        DatagramPacket paquete =
            new DatagramPacket(mensaje.getBytes(), mensaje.length(), grupo, PUERTO);

        socket.send(paquete);
        System.out.println("(Servidor) Mensaje enviado al grupo");
    }

    // 3️⃣ Cerrar socket
    public void close() {
        socket.close();
        System.out.println("(Servidor) Socket cerrado");
    }

    // MAIN
    public static void main(String[] args) {

        Server servidor = new Server();
        Scanner sc = new Scanner(System.in);
        String mensaje = "";

        try {
            servidor.start();

            System.out.println("Escribe mensajes (* para salir):");

            while (!mensaje.equals("*")) {
                mensaje = sc.nextLine();
                servidor.enviarMensaje(mensaje);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            servidor.close();
        }
    }
}
