package Artefacto.Ejercicio1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Empleado")
public class Empleado {

	@Id // Esto es por ser PRIMARY KEY
	@Column(name="id")
	private  long id;
	
	@Column
	private  String nombre;
	
	@Column
	private  String departamento;
	
	@Column
	private double salario;
	
	public Empleado() {
	}

	public Empleado(long id, String nombre, String departamento, double salario) {
		this.id = id;
		this.nombre = nombre;
		this.departamento = departamento;
		this.salario = salario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", departamento=" + departamento + ", salario=" + salario
				+ "]";
	}
	
	
}
