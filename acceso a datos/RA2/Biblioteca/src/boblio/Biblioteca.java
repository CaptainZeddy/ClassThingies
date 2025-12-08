package boblio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Biblioteca {

	private static String bd="xe";
	private static String user="biblioteca";
	private static String pass="password";

	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;

	private static Connection connection;
	static Statement st=null;
	static ResultSet rs=null;

	private static void conexion() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection=DriverManager.getConnection(url,user,pass);

			if(connection!=null) {
				System.out.println("Se ha realizado la conexion.");
			}
		}catch (SQLException | ClassNotFoundException e) {
			System.out.println("Se ha prodicido un error: "+e.getMessage());
		}
	}

	private static void desconectar() {
	    try {
	        if (rs != null) rs.close();
	        if (st != null) st.close();
	        if (connection != null) connection.close();

	        System.out.println("Se ha cerrado la conexión.");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private static void select() {
		String sql="select ID_AUTOR, NOMBRE_AUTOR, NACIONALIDAD from autores";
		try {
			st=connection.createStatement();
			rs=st.executeQuery(sql);

			while(rs.next()) {
				int id=rs.getInt("ID_autor");
				String nombre=rs.getString("NOMBRE_AUTOR");
				String nacionalidad=rs.getString("NACIONALIDAD");

				System.out.println(id+" "+nombre+" "+nacionalidad);
			}
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al visualizar autores: "+e.getMessage());
		}
	}


	private static void insertarLector(int id, String nombre, String apellido, String email) {
		String sql="insert into lectores (ID_LECTOR, nombre, apellido, email) values(?,?,?,?)";

		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setString(3, apellido);
			ps.setString(4, email);
			ps.executeUpdate();

			System.out.println("Lector insertado.");

		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al insertar: "+e.getMessage());
		}
	}

	public static void actualizarEmail(int idLector, String nuevoEmail) {
		try {

			st=connection.createStatement();
			int filas=st.executeUpdate("update lectores set email ='"+nuevoEmail+"' where id_lector ="+idLector);
			System.out.println("Filas actualizadas: "+filas);

		}catch (SQLException e) {
			System.out.println("Ha ocurrido un erro al actualizar: "+e.getMessage());
		}
	}

	public static void actualizarNombre(int idLector, String nuevoNombre) {
		String sql="update lectores set nombre= ? where id_lector=?";

		try {
			PreparedStatement ps=connection.prepareStatement(sql);

			ps.setString(1, nuevoNombre); 
			ps.setInt(2, idLector);

			int filas=ps.executeUpdate();
			System.out.println("Filas actualizadas: "+filas);

		}catch (SQLException e) {
			System.out.println("Ha ocurrido un erro al actualizar: "+e.getMessage());
		}
	}

	public static void eliminarLector(int idLector) {

		try {
			st=connection.createStatement();
			int filas=st.executeUpdate("delete from lectores where id_lector="+idLector);
			System.out.println("Filas afectadas: "+filas);

		} catch (SQLException e) {
			System.out.println("Ha ocuirrido un error al borrar: "+e.getMessage());
		}
	}

	public static void buscarLibrosPorAnio(int anio) {
		String sql="select isbn, titulo from libros where anio_publicacion = ?";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, anio);
			ResultSet rs= ps.executeQuery();

			while (rs.next()) {
				int isbn=rs.getInt("isbn");
				String titulo=rs.getString("titulo");
				System.out.println(isbn+" "+titulo);
			}

		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al buscar el libro: "+e.getMessage());
		}
	}

	public static void buscarLectoresConMultas() {
		String sql="select ID_LECTOR, NOMBRE, MULTAS_PENDIENTES from lectores where MULTAS_PENDIENTES >0";

		try {
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			while(rs.next()) {
				int id=rs.getInt("ID_LECTOR");
				String nombre=rs.getString("NOMBRE");
				double multas=rs.getDouble("MULTAS_PENDIENTES");

				System.out.println(id+" "+nombre+" "+multas);
			}

		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error en la consulta: "+e.getMessage());
		}
	}
	
	public static void procesarDevolucion(int idPrestamo) {
		
	}
	

	public static void main (String[] args) throws SQLException {

		conexion();
		//		select();
		//		insertarLector(105, "Dokja", "Kim", "esteEmail@ejemplo.com");
		//		actualizarEmail(105, "otroEmail@example.com");
		//		eliminarLector(105);
		//		buscarLibrosPorAnio(1981);
		//				buscarLectoresConMultas();
		desconectar();
	}
}
