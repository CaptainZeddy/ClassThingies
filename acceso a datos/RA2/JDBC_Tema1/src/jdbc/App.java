package jdbc;

import java.sql.Connection;

public class App {
	private static String bd="xe";
	private static String login="biblio";
	private static String password="contraseña";
	
	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;
	
	static Connection connection =null;
	
	public static void main (String[] args) {
		
	}
}
