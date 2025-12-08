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
public class Direccion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dir")
	@SequenceGenerator(name = "seq_dir", sequenceName = "SEQ_DIRECCION", allocationSize = 1)
	private long id;
	
	@Column
	private String calle;
	
	@Column
	private String ciudad;
	
	
	@OneToOne(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Cliente cliente;
	
	public Direccion() {
	}
	
	public Direccion(String calle, String ciudad) {
		this.calle = calle;
		this.ciudad = ciudad;
	}

	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Direccion [id=" + id + ", calle=" + calle + ", ciudad=" + ciudad + "]";
	}
	
	
}
