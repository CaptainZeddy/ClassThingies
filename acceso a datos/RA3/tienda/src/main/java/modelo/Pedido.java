package modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ped")
	@SequenceGenerator(name = "seq_ped", sequenceName = "SEQ_PEDIDO", allocationSize = 1)
	private long id;
	
	@Column
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CLIENTE_ID", unique = true, nullable = false)
	private Cliente cliente;
	
	
	public Pedido(String descripcion) {
		this.descripcion = descripcion;
	}

	public Pedido() {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", descripcion=" + descripcion + ", cliente=" + cliente + "]";
	}

	
	
}
