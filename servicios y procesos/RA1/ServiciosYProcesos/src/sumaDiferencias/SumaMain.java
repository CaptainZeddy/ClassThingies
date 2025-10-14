package sumaDiferencias;

public class SumaMain {

	public static void main(String[] args) {

		int num1= Integer.parseInt(args[0]);
		int num2= Integer.parseInt(args[1]);
		int res=0;
		while(num1<=num2) {
			res+=num1;
			num1++;
			
		}
		System.out.println(res);
		System.exit(6);
	}

}
