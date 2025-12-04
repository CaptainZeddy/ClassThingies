package modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_libro") 
	@SequenceGenerator(name = "seq_libro", sequenceName = "SEQ_LIBRO", allocationSize = 1)
	private long id;
	
	@Column
	private String titulo;
	
	@Column
	private String autor;
	
	@OneToOne(mappedBy = "libro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Detalle_libro librodetalle;
	
	public Libro() {
	}

	public Libro(String titulo, String autor) {
		this.titulo = titulo;
		this.autor = autor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	
	public Detalle_libro getLibrodetalle() {
		return librodetalle;
	}

	public void setLibrodetalle(Detalle_libro librodetalle) {
		this.librodetalle = librodetalle;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + "]";
	}
	
	
}
