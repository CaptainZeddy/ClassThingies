package pojos;

public class Departamento {

	 private int id_dep;
	 private String nombre;
	 private String localidad;
	
	 public Departamento(int id_dep, String nombre, String localidad) {
		this.id_dep = id_dep;
		this.nombre = nombre;
		this.localidad = localidad;
	}

	public int getId_dep() {
		return id_dep;
	}

	public void setId_dep(int id_dep) {
		this.id_dep = id_dep;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@Override
	public String toString() {
		return "Departamento [id_dep=" + id_dep + ", nombre=" + nombre + ", localidad=" + localidad + "]";
	}
	 
	
	

}
