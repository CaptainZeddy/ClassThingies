package repasojaxb.Repaso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) throws JAXBException, IOException {

		JAXBContext contexto= JAXBContext.newInstance(Vehiculos.class);

		Marshaller m=contexto.createMarshaller();
		Unmarshaller u=contexto.createUnmarshaller();

		File f=new File ("SalidaJAXB.xml");

		Vehiculos misVehiculos=new Vehiculos();

		ArrayList<Vehiculo> vehiculos= new ArrayList();
		Vehiculo vehiculo= new Vehiculo();

		vehiculo.setAño(1998);
		vehiculo.setMarca("Mazda");
		vehiculo.setModelo("rx7");
		vehiculo.setPrecio(45000.00);
		vehiculo.setVendedor("Jujiro Matsuda");
		vehiculos.add(vehiculo);

		vehiculo = new Vehiculo();
		vehiculo.setAño(1996);
		vehiculo.setMarca("Toyota");
		vehiculo.setModelo("Supra");
		vehiculo.setPrecio(65000.00);
		vehiculo.setVendedor("Kiichiro Toyoda");
		vehiculos.add(vehiculo);

		misVehiculos.setVehiculos(vehiculos);

		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		//m.marshal(misVehiculos, System.out);

		FileWriter fw = new FileWriter("SalidaJAXB.xml");
		m.marshal(misVehiculos,fw);

		Vehiculos misVehiculo=(Vehiculos) u.unmarshal(f);

		ArrayList<Vehiculo> vehiculos2= misVehiculo.getVehiculos();
		
		for (Vehiculo v : vehiculos2) {
			System.out.println("Año: "+v.getAño());
			System.out.println("Marca: "+v.getMarca());
			System.out.println("Modelo: "+v.getModelo());
			System.out.println("Precio: "+v.getPrecio());
			System.out.println("Vededor: "+v.getVendedor());
			System.out.println();
		}

	}
}
