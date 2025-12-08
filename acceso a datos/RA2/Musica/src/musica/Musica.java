package musica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Musica {
	private final static String url="jdbc:sqlite:musica3.db";
	private final static String driver="org.sqlite.JDBC";

	private static Connection connection=null;
	private static Statement st=null;
	private static ResultSet rs =null;



	public static void conectar() throws ClassNotFoundException, SQLException {

		Class.forName(driver);		
		connection= DriverManager.getConnection(url);

		if (connection!=null) {
			System.out.println("Se ha establecido la conexión.");
		}
	}

	public static void desconectar() throws SQLException {
		if (connection!=null) connection.close();
		if (st!=null) st.close();
		if (rs!=null) rs.close();
	}

	public static void crearTabla() {
		String sql = "create table if not exists canciones"
				+ "(id integer primary key autoincrement,"
				+ " titulo text not null,"
				+ " artista text not null,"
				+ " genero text not null,"
				+ " duracion_segundos integer not null,"
				+ " año_lanzamiento integer not null,"
				+ "id_discografica integer not null) ";

		try {
			st = connection.createStatement();
			st.execute(sql);
			System.out.println("Se ha creado la tabla");
		} catch (SQLException e) {
			System.out.println("Error en la creacion de tabla");
		}

	}

	public static void crearTabla2() {
		String sql = "create table if not exists discografica"
				+ "(id_discografica integer primary key autoincrement,"
				+ " nombre text not null,"
				+ " pais_sede text not null)";

		try {
			st = connection.createStatement();
			st.execute(sql);
			System.out.println("Se ha creado la tabla discografica");
		} catch (SQLException e) {
			System.out.println("Error en la creacion de tabla");
		}

	}

	public static void alterarCanciones() throws SQLException {
		String sql="alter table canciones add column id_discografica";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.executeUpdate();
		System.out.println("Se ha alterado la tabla canciones.");
	}

	public static void  añadirCancion(String titulo, String artista, String genero, int duracion, int año, int idDisc) throws SQLException {
		String sql="insert into canciones (titulo, artista, genero, duracion_segundos, año_lanzamiento, id_discografica) values (?,?,?,?,?,?)"; 

		PreparedStatement ps= connection.prepareStatement(sql);
		ps.setString(1, titulo);
		ps.setString(2, artista);
		ps.setString(3, genero);
		ps.setInt(4, duracion);
		ps.setInt(5, año);
		ps.setInt(6, idDisc);
		ps.executeUpdate();

		System.out.println("Se ha insertado con exito.");

		ps=connection.prepareStatement("select* from canciones ORDER BY ID DESC LIMIT 1");
		rs=ps.executeQuery();

		while (rs.next()) {
			String resultado = "Insertada: "+rs.getString("titulo")+" "+rs.getString("artista")+" "+rs.getString("genero")+" "+rs.getInt("duracion_segundos")+" "+rs.getInt("año_lanzamiento")+" "+rs.getInt("id_discografica");
			System.out.println(resultado);
		}
	}

	public static void  añadirDiscografica(int idDisc, String nombre, String sede) throws SQLException {
		String sql="insert into discografica (id_discografica, nombre, pais_sede) values (?,?,?)"; 

		PreparedStatement ps= connection.prepareStatement(sql);
		ps.setInt(1, idDisc);
		ps.setString(2, nombre);
		ps.setString(3, sede);


		ps.executeUpdate();

		System.out.println("Se ha insertado discografica con exito.");
	}



	public static void consultarCancionesPorGenero(String genero) throws SQLException {
		String sql="select titulo, artista, duracion_segundos from canciones where genero=?";

		PreparedStatement ps= connection.prepareStatement(sql);
		ps.setString(1, genero);
		rs=ps.executeQuery();

		while(rs.next()) {
			String titulo =rs.getString("titulo");
			String artista =rs.getString("artista");
			int duracion =rs.getInt("duracion_segundos");

			System.out.println(titulo+" "+artista+" "+duracion);
		}
	}

	public static void actualizarDuracionCancion(int idCancion, int nuevaDuracion) throws SQLException {
		String sql="update canciones set duracion_segundos=? where id=?";

		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setInt(1, nuevaDuracion);
		ps.setInt(2, idCancion);
		int filas=ps.executeUpdate();

		System.out.println("update realizado, filas afectadas= "+filas);
	}

	public static void selectGeneral() throws SQLException {
		String sql="select * from canciones";

		PreparedStatement ps=connection.prepareStatement(sql);
		rs=ps.executeQuery();

		while (rs.next()) {
			String resultado = "Insertada: "+rs.getString("titulo")+" "+rs.getString("artista")+" "+rs.getString("genero")+" "+rs.getInt("duracion_segundos")+" "+rs.getInt("año_lanzamiento");
			System.out.println(resultado);
		}
	}

	public static void eliminarCancionesAntiguas(int añoLimite) throws SQLException {
		String sql="Delete from canciones where año_lanzamiento<?";

		PreparedStatement ps=connection.prepareStatement(sql);
		int filas=ps.executeUpdate();
		System.out.println("Se han borrado "+filas+" canciones.");

	}

	public static void obtenerCancionesYDiscografica() throws SQLException {
		String sql="Select c.titulo, c.artista , d.nombre from canciones c"
				+ " join discografica d on c.id_discografica=d.id_discografica";

		PreparedStatement ps= connection.prepareStatement(sql);

		rs= ps.executeQuery();

		while(rs.next()) {
			String titulo=rs.getString(1);
			String artista=rs.getString(2);
			String nombreDisc=rs.getString(3);

			System.out.println(titulo+" "+artista+" "+nombreDisc);
		}
	}

	public static void  artistasConMuchasCanciones(int limite) throws SQLException {
		String sql="Select artista, count(id) as total from canciones "
				+ "group by artista "
				+ "having id > ?";

		PreparedStatement ps= connection.prepareStatement(sql);
		ps.setInt(1, limite);
		rs=ps.executeQuery();

		while(rs.next()) {
			String artista=rs.getString(1);
			int conteo=rs.getInt(2);

			System.out.println("Artista: "+artista+" Numero canciones: "+conteo);
		}
	}


	public static void main (String[] args) {
		try {
			conectar();
		} catch (ClassNotFoundException e) {
			System.out.println("Ha ocurrido un error al conectar: "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al conectar: "+e.getMessage());
		}

		//		crearTabla();

		//		try {
		//			añadirCancion("Cancer2", "Otro", "Clasica", 180, 1990, 2);
		//		} catch (SQLException e) {
		//			System.out.println("Ha ocurrido un erro al insertar la cancion: "+e.getMessage());
		//		}

		//		try {
		//			añadirDiscografica(2, "cancer", "Francia");
		//		} catch (SQLException e) {
		//			System.out.println("Ha ocurrido un error al añadir la discografica: "+e.getMessage());
		//		}

		//		try {
		//			consultarCancionesPorGenero("deathMetal");
		//		} catch (SQLException e) {
		//			System.out.println("Ha ocurrido un error al buscar por genero: "+e.getMessage());
		//		}

		//		try {
		//			actualizarDuracionCancion(2, 210);
		//		} catch (SQLException e) {
		//			System.out.println("Ha ocurrido un error al actualizar la duracion: "+e.getMessage());
		//		}

		//		try {
		//			selectGeneral();
		//		} catch (SQLException e) {
		//			System.out.println("Ha ocurrido un erro al visualizar todo: "+e.getMessage());
		//		}
		//
		//		try {
		//			eliminarCancionesAntiguas(2023);
		//		} catch (SQLException e) {
		//			System.out.println("Se ha producido un error al borrar: "+e.getMessage());
		//		}

		//		crearTabla2();

		//		try {
		//			alterarCanciones();
		//		} catch (SQLException e) {
		//			System.out.println("Ha ocurrido un error al alterar la tabla: "+e.getMessage());
		//		}

		//		try {
		//			obtenerCancionesYDiscografica();
		//		} catch (SQLException e) {
		//			System.out.println("Ha ocurrido un erro al seleccionar en el join: "+e.getMessage());
		//		}

		try {
			artistasConMuchasCanciones(3);
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al buscar en grupo: "+e.getMessage());
		}

		try {
			desconectar();
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al desconectar: "+e.getMessage());
		}

	}
}
