package alumnoPersona;

import java.io.Serializable;

public class Persona implements Serializable{
	private String Nombre;
	private String Curso;
	private int nota;
	
	public Persona(String nombre, String curso, int nota) {
		super();
		Nombre = nombre;
		Curso = curso;
		this.nota = nota;
	} 
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getCurso() {
		return Curso;
	}
	public void setCurso(String curso) {
		Curso = curso;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	
	
	
}

class Alumno extends Persona implements Serializable{
	transient Fecha fecha;
	public Alumno(String nombre, String curso, int nota, Fecha fecha) {
		super(nombre, curso, nota);
		this.fecha=fecha;
	}
	@Override
	public String toString() {
		return "Alumno [fecha=" + fecha + ", getNombre()=" + getNombre() + ", getCurso()=" + getCurso() + ", getNota()="
				+ getNota() + "]";
	}

	
	
}

class Fecha implements Serializable{
	private int dia;
	private int mes;
	private int año;
	
	public Fecha(int dia, int mes, int año) {
		super();
		this.dia = dia;
		this.mes = mes;
		this.año = año;
	}
		public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAño() {
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	@Override
	public String toString() {
		return "Fecha [dia=" + dia + ", mes=" + mes + ", año=" + año + "]";
	}
	
	
	
}