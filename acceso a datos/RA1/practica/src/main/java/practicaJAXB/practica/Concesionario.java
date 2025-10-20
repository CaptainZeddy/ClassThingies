package practicaJAXB.practica;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement(name="concesionario")
@XmlType(propOrder = {"nombre","vehiculos","ubicacion"})
public class Concesionario {
	private String nombre;
	private ArrayList<Vehiculo> vehiculos= new ArrayList<Vehiculo>();
	private String ubicacion;
	
	public Concesionario() {
		super();
	}

	@XmlAttribute
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElementWrapper
	@XmlElement
	public ArrayList<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	@XmlElement
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	
}
