package estaciones;

import java.util.Scanner;

public class Estaciones implements Runnable{
	private String[] meses= {"Diciembre","Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre"};
	Scanner scan= new Scanner(System.in);

	@Override
	public void run() {

		System.out.println("Introduce una estacion (en minuscula)");
		String mes=scan.nextLine();

		for(int i=0; i <3;i++) {

			if(mes.equals("invierno")) {
				System.out.println(meses[i+1]);
			}else if (mes.equals("primavera")) {
				System.out.println(meses[i+4]);
			}else if (mes.equals("verano")) {
				System.out.println(meses[i+7]);
			}else if (mes.equals("otoño")) {
				System.out.println(meses[i+9]);
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	

		}

	}

}
