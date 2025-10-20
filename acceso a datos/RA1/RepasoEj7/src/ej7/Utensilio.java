package ej7;

public class Utensilio {
	
	private String nombre;
	private int cantidad;
	
	
	public Utensilio(String nombre, int cantidad) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Utensilio [" +nombre +": " + cantidad + "]";
	}
	
	
}
