package sumaEnteros;

public class Sumar {
	public static void main(String[] args) {
		
		int n1= Integer.parseInt(args[0]);
		int n2= Integer.parseInt(args[1]);
		int res=0;
		
		while(n1<=n2) {
			res=res+n1;
			n1++;
		}

		System.out.println(res);
	}
}
