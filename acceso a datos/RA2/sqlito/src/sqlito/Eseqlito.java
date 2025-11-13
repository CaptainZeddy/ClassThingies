package sqlito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;

public class Eseqlito {
	Scanner scan=new Scanner(System.in);
	private final static String url="jdbc:sqlite:musica.db";
	private final static String driver="org.sqlite.JDBC";

	private static Connection connection =null;
	static Statement st=null;
	static ResultSet rs=null;

	public static void conectar() {

		try {

			Class.forName(driver);
			connection=DriverManager.getConnection(url);
			if(connection!=null) {
				System.out.println("Conexion realizada correctamente");
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void desconectar() {
		try {
			connection.close();
			st.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al desconectar: "+e.getMessage());
		}
	}

	public static void crearTabla() {
		String sql="create table if not exists canciones("
				+ "id Integer primary key,"
				+ "titulo text,"
				+ "artista text,"
				+ "genero text,"
				+ "duracion_segundos integer,"
				+ "año_lanzamiento integer )";
		try(Statement st= connection.createStatement()){
			st.execute(sql);
			System.out.println("Tabla creada con Exito.");
		}catch (SQLException e) {
			System.out.println("Ha ocurrido un error al crear la tabla o ya existe: "+e.getMessage());
		}
	}	

	public static void añadirCancion(String titulo, String artista, String genero, int duracion, int año) {
		String sql="insert into canciones("
				+ "titulo,"
				+ "artista,"
				+ "genero,"
				+ "duracion_segundos,"
				+ "año_lanzamiento)"
				+ "values(?,?,?,?,?)";
		try {
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setString(1, titulo);
			ps.setString(2, artista);
			ps.setString(3, genero);
			ps.setInt(4, duracion);
			ps.setInt(5, año);
			ps.executeUpdate();

			ps=connection.prepareStatement("select* from canciones ORDER BY ID DESC LIMIT 1");
			rs=ps.executeQuery();

			while (rs.next()) {
				String resultado = "Insertada: "+rs.getString("titulo")+" "+rs.getString("artista")+" "+rs.getString("genero")+" "+rs.getInt("duracion_segundos")+" "+rs.getInt("año_lanzamiento");
				System.out.println(resultado);
			}

		}catch (SQLException e) {
			System.out.println("Ha ocurrido un error al insertar o visualizar la cancion: "+e.getMessage());
		}
	}


	public static void buscarGenero(String genero) {
		String sql="select titulo, artista, duracion_segundos from canciones where genero='"+genero+"'";
		try {
			Statement st=connection.createStatement();
			rs=st.executeQuery(sql);
	
			while (rs.next()) {
				String resultado = "Resultado: "+rs.getString("titulo")+" "+rs.getString("artista")+" "+rs.getInt("duracion_segundos");
				System.out.println(resultado);
			}

		}catch (SQLException e) {
			System.out.println("Ha ocurrido un error al visualizar la cancion: "+e.getMessage());
		}
	}

	public static void main (String[] args) throws SQLException {
		conectar();
//		crearTabla();
//		añadirCancion("Hunting soul", "HayaSi", "Speed/power metal", 300, 2025);
		buscarGenero("Speed/power metal");
		desconectar();
	}
}
