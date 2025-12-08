package modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table
public class Detalle_libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_detalle") 
	@SequenceGenerator(name = "seq_detalle", sequenceName = "SEQ_DETALLE_LIBRO", allocationSize = 1)
	private long id;
	
	@Column
	private String sinopsis_larga;
	
	@Column
	private String editorial;
		
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "LIBRO_ID", unique = true, nullable = false)
	private Libro libro;

	public Detalle_libro() {
	}
	
	public Detalle_libro(String sinopsis, String editorial, Libro libro) {
		this.sinopsis_larga = sinopsis;
		this.editorial = editorial;
		this.libro = libro;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSinopsis() {
		return sinopsis_larga;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis_larga = sinopsis;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	@Override
	public String toString() {
		return "LibroDetalle [id=" + id + ", sinopsis=" + sinopsis_larga + ", editorial=" + editorial + ", libro=" + libro
				+ "]";
	}
	
	
}
