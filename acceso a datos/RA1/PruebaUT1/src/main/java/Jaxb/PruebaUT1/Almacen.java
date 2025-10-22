package Jaxb.PruebaUT1;

import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement(name="almacen")
public class Almacen {

	private List<Articulo> articulos;

	public Almacen() {}

	public Almacen(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	@XmlElementWrapper
	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
}