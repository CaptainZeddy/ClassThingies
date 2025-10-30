package jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
	private static String bd="xe";
	private static String login="biblio";
	private static String password="contraseña";

	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;

	private static Connection connection =null;
	static Statement st=null;
	static ResultSet rs=null;

	public static void conectar() {
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection=DriverManager.getConnection(url,login,password);
			if(connection!=null) {
				System.out.println("Conexion realizada correctamente");
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void update(int id, String email) throws SQLException {
		

		try {
		st=connection.createStatement();
		int filas=st.executeUpdate( "UPDATE lectores SET email='" + email + "' WHERE id_lector=" + id);
		}finally {
			if (st !=null) st.close();
		}

	}
	
	public static void insert(int id, String nombre, String apellido, String email) throws SQLException {
		PreparedStatement ps=connection.prepareStatement("insert into lectores (id_lector, nombre, apellido, email) values (?,?,?,?)");
		
		ps.setInt(1, id);
		ps.setString(2, nombre);
		ps.setString(3,apellido);
		ps.setString(4, email);
		ps.executeUpdate();
		ps=connection.prepareStatement("select* from lectores");
		rs=ps.executeQuery();
		
		ps.close();
	}


	public static void buscarLibro(int anio) throws SQLException {
		st=connection.createStatement();
		rs=st.executeQuery("select titulo from libros where anio_publicacion="+anio);
		
		while (rs.next()) {
			String resultado = rs.getString("titulo");
			System.out.println(resultado);
			}
		
		
	}


	public static void main (String[] args) throws SQLException {
		conectar();
//		update(2001,"elmaninmaninoman");
//		insert(5145,"Dokja","kim","manincete");
		buscarLibro(1813);
	}
}
