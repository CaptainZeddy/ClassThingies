package pojo;

import java.util.List;

public class Libro {
	private String titulo;
	private int año;
	private String editorial;
	private int paginas;
	private List<Autor> autores;
	
	public Libro(String titulo, int año, String editorial, int paginas, List<Autor> autores) {
		this.titulo = titulo;
		this.año = año;
		this.editorial = editorial;
		this.paginas = paginas;
		this.autores = autores;
	}

	public Libro() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", año=" + año + ", editorial=" + editorial + ", paginas=" + paginas + "]";
	}
	
	
}
