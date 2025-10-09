package repasojaxb.Repaso;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Vehiculos")
public class Vehiculos {
	
	private ArrayList<Vehiculo> Vehiculos = new ArrayList();

	public Vehiculos() {
	}

	public ArrayList<Vehiculo> getVehiculos() {
		return Vehiculos;
	}

	public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
		Vehiculos = vehiculos;
	}

	



}
