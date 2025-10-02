package Ejercicio4Jaxb.LecturaEjercicio4;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="Cancion")
@XmlType(propOrder={"titulo","duracion"})

public class Cancion {

	private String titulo;
	private int duracion;

	public Cancion() {

	}

	@XmlElement(name = "titulo")
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@XmlElement(name = "duracion")
	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}




}
