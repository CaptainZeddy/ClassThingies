package examen;

import java.awt.Window.Type;
import java.sql.*;

public class GestorBD {



	// Sustituye estos con tus datos de Oracle
	private final String URL_CONEXION = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USUARIO = "C##MINITIENDA"; 
	private final String PASSWORD = "password";

	private static Connection conn=null;
	private static Statement st=null;
	private static ResultSet rs=null;

	private static int idLog=0;

	/**
	 * Criterio d: Establece la conexión con la base de datos Oracle.
	 * Criterio c: Usa el driver específico de Oracle.
	 */
	public void conectar() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn= DriverManager.getConnection(URL_CONEXION,USUARIO,PASSWORD);

			if (conn!=null)System.out.println("Conexión establecida con Oracle.");

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("ERROR: No se pudo conectar a la BD Oracle.");
		}
	}

	/**
	 * Criterio i: Cierra la conexión.
	 */
	public void desconectar() {

		try {
			if (conn!=null) conn.close();
			cerrar();
			System.out.println("\nDesconexión exitosa.");

		}catch (SQLException e){
			System.err.println("ERROR al cerrar conexión: " + e.getMessage());
		}
	}

	/**
	 * Tarea A - Criterio k: Consulta stock usando un Procedimiento Almacenado.
	 * Criterio g: Gestión del parámetro de salida.
	 */
	public void consultarStock(int productoId) {
		System.out.println("\n--- Tarea 1: Consultar Stock (ID: " + productoId + ") ---");

		String sql="{call CONSULTAR_STOCK(?,?)}";
		try {
			CallableStatement cs =conn.prepareCall(sql);

			cs.setInt(1, productoId);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.execute();
			int stockActual=cs.getInt(2);

			System.out.println("Stock actual del Producto " + productoId + ": " + stockActual + " unidades.");
		}catch (SQLException e) {
			System.err.println("ERROR al consultar stock: " + e.getMessage());
			//			e.printStackTrace();
		}
	}

	/**
	 * Tarea B - Criterio f, j: Procesa un pedido completo y su log de manera atómica.
	 */
	public void procesarPedido(int productoId, int cantidad) {

		System.out.println("\n--- Tarea 2: Procesar Pedido (Prod: " + productoId + ", Cant: " + cantidad + ") ---");
		try {
			System.out.println("    [Transacción Iniciada]");
			conn.setAutoCommit(false);
			PreparedStatement ps=null;

			String sqlUpdate="Update productos set stock=? where id = ?";
			ps= conn.prepareStatement(sqlUpdate);
			ps.setInt(1, cantidad);
			ps.setInt(2, productoId);
			ps.executeUpdate();

			String sqlSelect="select count(id)+1 from log_pedidos";
			ps=conn.prepareStatement(sqlSelect);
			rs=ps.executeQuery();
			int id=0;

			if(rs.next()) {
				id=rs.getInt(1);
			}

			String sqlInsert="insert into log_pedidos (id, PRODUCTO_ID, CANTIDAD_SOLICITADA, fecha) values ("+id+", ?, ?, SYSDATE)";
			ps=conn.prepareStatement(sqlInsert);
			ps.setInt(1, productoId);
			ps.setInt(2, cantidad);
			ps.executeUpdate();


			System.out.println("Pedido procesado y STOCK actualizado.");
			conn.commit();

		}catch (SQLException e) {
			try {
				conn.rollback();
				//				e.printStackTrace();
				System.err.println("ERROR procesando pedido. Detalle: " + "El producto no existe o ID incorrecto.");
				System.out.println("Cambios deshechos.");

			} catch (SQLException e1) {
				System.err.println("ERROR al deshacer cambios: " + e.getMessage());
			}
		}



	}

	/**
	 * Tarea D - Criterio h, g: Consulta 1
	 */
	public void listarPedidosDetallados() {

		System.out.println("\n--- Tarea 4: Consulta con JOIN (Pedidos Detallados) ---");

		String sql="select p.nombre, l.cantidad_solicitada from productos p "
				+ "join log_pedidos l on p.id=l.producto_id "
				+ "order by fecha desc";
		try {
			PreparedStatement ps= conn.prepareStatement(sql);
			rs=ps.executeQuery();

			while(rs.next()) {

				String nombre=rs.getString(1);
				int cantidad =rs.getInt(2);


				System.out.printf("    Producto: %-20s | Cantidad: %-5d\n", nombre, cantidad);

				System.out.println("    ---------------------------------------------------------");

				System.out.println("    ---------------------------------------------------------");
			}
		}catch (SQLException e){
			System.err.println("ERROR al listar pedidos detallados: " + e.getMessage());
			//			e.printStackTrace();
		}
	}

	/**
	 * Tarea E - Criterio h, g: Consulta 2
	 */
	public void productosAltaDemanda(int limite) {

		System.out.println("\n--- Tarea 5: Consulta con HAVING (Demanda > " + limite + ") ---");

		String sql="select p.nombre, sum(l.cantidad_solicitada) from productos p "
				+ "join log_pedidos l on p.id=l.producto_id "
				+ "group by p.nombre "
				+ "having sum(l.cantidad_solicitada) > ?";
		try {		
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, limite);
			rs=ps.executeQuery();

			while(rs.next()) {

				String nombre=rs.getString(1);
				int demanda=rs.getInt(2);

				System.out.println("    Productos con demanda total superior a " + limite + " unidades:");

				System.out.printf("    - Producto: %-20s | Demanda Total: %d\n", nombre, demanda);
			}
		}catch(SQLException e) {
			System.err.println("ERROR en consulta 2: " + e.getMessage());
		}
	}

	/**
	 * Criterio i: Método auxiliar para cerrar recursos de forma segura.
	 */
	private void cerrar() {
		try {

			if (st!=null) st.close();
			if (rs!=null) rs.close();
		}catch(SQLException e) {
			System.err.println("Error al cerrar recurso: " + e.getMessage());
		}
	}

	public Connection getConn() {
		return conn;
	}


}