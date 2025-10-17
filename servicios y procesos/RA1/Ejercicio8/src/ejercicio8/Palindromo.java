package ejercicio8;

import java.lang.reflect.Array;

public class Palindromo {

	public static void main(String[] args) {
		
		if (args[0].isEmpty()) {
			System.out.println("El parametro esta vacío.");
			System.exit(0);
		}else {
		
			String reverse= new StringBuilder(args[0]).reverse().toString();
			
			if(reverse.equals(args[0])) {
				System.out.println(args[0]+" es palindromo");
				System.exit(1);
			}else {
				System.out.println(args[0]+" no es palindormo");
				System.exit(2);
			}
		}
	}

}
