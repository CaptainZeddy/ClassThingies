package repasojaxb.Repaso;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="Vehiculo")
public class Vehiculo {
	
	private double precio;
	private int año;
	private String marca;
	private String modelo;
	private String vendedor;
	
	public Vehiculo() {
		super();
	}

	@XmlElement(name="Precio")
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@XmlElement(name="Año")
	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}
	@XmlElement(name="Marca")
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	@XmlElement(name="Modelo")
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	@XmlElement(name="Vendedor")
	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	
	

}
