package practicaJAXB.practica;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement(name="vehiculo")
@XmlType(propOrder={"precio", "año","marca", "modelo","fabricante"})
public class Vehiculo {
	private double precio;
	private int año;
	private String marca;
	private String modelo;
	private String fabricante;
	
	public Vehiculo() {
		super();
	}

	@XmlAttribute
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	@XmlElement
	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}
	@XmlElement
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	@XmlElement
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	@XmlElement
	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	
	
	
}
