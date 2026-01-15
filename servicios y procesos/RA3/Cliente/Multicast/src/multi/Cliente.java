package multi;

import java.net.*;

public class Cliente {

    private static final String IP_GRUPO = "225.0.0.1";
    private static final int PUERTO = 49171;
    NetworkInterface netIf;
    private MulticastSocket socket;
    private InetAddress grupo;

    // 1️⃣ Crear socket y unirse al grupo
    public void start() throws Exception {
        System.out.println("(Cliente) Creando socket multicast...");
        socket = new MulticastSocket(PUERTO);
        grupo = InetAddress.getByName(IP_GRUPO);
        netIf = NetworkInterface.getByName(IP_GRUPO);
        socket.joinGroup(grupo);
        System.out.println("(Cliente) Unido al grupo multicast");
    }

    // 2️⃣ Recibir mensaje
    public String recibirMensaje() throws Exception {
        byte[] buffer = new byte[1024];
        DatagramPacket paquete =
            new DatagramPacket(buffer, buffer.length);

        socket.receive(paquete);

        String mensaje = new String(
            paquete.getData(),
            0,
            paquete.getLength()
        );

        return mensaje;
    }

    // 3️⃣ Salir del grupo y cerrar socket
    public void close() throws Exception {
        socket.close();
        System.out.println("(Cliente) Socket cerrado");
    }

    // MAIN
    public static void main(String[] args) {

        Cliente cliente = new Cliente();
        String mensaje = "";

        try {
            cliente.start();

            while (!mensaje.equals("*")) {
                mensaje = cliente.recibirMensaje();
                System.out.println("Recibido: " + mensaje);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cliente.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
