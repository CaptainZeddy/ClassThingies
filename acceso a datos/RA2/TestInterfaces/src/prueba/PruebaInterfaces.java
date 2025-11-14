package prueba;

import java.sql.Statement;
import java.io.Console;
//import java.sql.Types;
//import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PruebaInterfaces {
	private static String login="admin";
	private static String password="password";

	private static String url="jdbc:mysql://bdpruebas.czcoso6c2w2i.us-east-1.rds.amazonaws.com:3306/juego_demo";

	private static Connection connection =null;
	static Statement st=null;
	static ResultSet rs=null;

	public static void conectar() {

		try {
			connection=DriverManager.getConnection(url,login,password);
			if(connection!=null) {
				System.out.println("Conexion realizada correctamente");
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void consultarJugadores(Connection conexion) {
		String sql = "SELECT id, nombre, nivel, puntos FROM jugadores";

		try (Statement stmt = conexion.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				int nivel = rs.getInt("nivel");
				int puntos = rs.getInt("puntos");

				System.out.println(
						"ID: " + id +
						", Nombre: " + nombre +
						", Nivel: " + nivel +
						", Puntos: " + puntos
						);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main (String[] args) throws SQLException {
		conectar();
		consultarJugadores(connection);
	}

}