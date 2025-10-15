package ejercicio2;

public class LeerNombre {

	public static void main(String[] args) {

		if (args[0].isEmpty()) {
			System.exit(0);
		}else {
			System.out.println(args[0]);
			System.exit(1);
		}
	}

}
