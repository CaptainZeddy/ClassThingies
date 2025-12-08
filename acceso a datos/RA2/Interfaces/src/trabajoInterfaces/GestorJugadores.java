package trabajoInterfaces;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class GestorJugadores extends JFrame {
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtPuntuacion;
    private JTextArea txtAreaResultados;

    private final String URL = "jdbc:mysql://testdatabase2.czcoso6c2w2i.us-east-1.rds.amazonaws.com:3306/juego_demo";
    private final String USER = "admin";
    private final String PASSWORD = "password";

    public GestorJugadores() {
        setTitle("Gestor de Jugadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        getContentPane().setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(30, 30, 70, 20);
        getContentPane().add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 30, 120, 20);
        getContentPane().add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 70, 70, 20);
        getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 70, 120, 20);
        getContentPane().add(txtNombre);

        JLabel lblPuntuacion = new JLabel("Puntuación:");
        lblPuntuacion.setBounds(30, 110, 70, 20);
        getContentPane().add(lblPuntuacion);

        txtPuntuacion = new JTextField();
        txtPuntuacion.setBounds(100, 110, 120, 20);
        getContentPane().add(txtPuntuacion);

        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.setBounds(250, 30, 100, 25);
        getContentPane().add(btnInsertar);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(250, 70, 100, 25);
        getContentPane().add(btnConsultar);

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(250, 110, 100, 25);
        getContentPane().add(btnBorrar);

        txtAreaResultados = new JTextArea();
        txtAreaResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaResultados);
        scrollPane.setBounds(30, 160, 400, 170);
        getContentPane().add(scrollPane);

        // --- Acciones de los botones ---

        btnInsertar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertarJugador();
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarJugadores();
            }
        });

        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrarJugador();
            }
        });
    }

    private void insertarJugador() {
        String nombre = txtNombre.getText();
        String puntuacionStr = txtPuntuacion.getText();

        if (nombre.isEmpty() || puntuacionStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            int puntuacion = Integer.parseInt(puntuacionStr);
            String query = "INSERT INTO jugadores (nombre, puntos) VALUES ('" + nombre + "', " + puntuacion + ")";
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Jugador insertado correctamente.");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar jugador: " + ex.getMessage());
        }
    }

    private void consultarJugadores() {
        txtAreaResultados.setText("");
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM jugadores")) {

            while (rs.next()) {
                txtAreaResultados.append("ID: " + rs.getInt("id") +
                        " | Nombre: " + rs.getString("nombre") +
                        " | Puntos: " + rs.getInt("puntos") + "\n");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al consultar: " + ex.getMessage());
        }
    }

    private void borrarJugador() {
        String idStr = txtId.getText();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduce el ID del jugador a borrar.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            int id = Integer.parseInt(idStr);
            int filas = stmt.executeUpdate("DELETE FROM jugadores WHERE id = " + id);
            if (filas > 0) {
                JOptionPane.showMessageDialog(this, "Jugador eliminado.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un jugador con ese ID.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al borrar jugador: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            GestorJugadores frame = new GestorJugadores();
            frame.setVisible(true);
        });
    }
}
