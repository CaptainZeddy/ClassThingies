package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cli")
	@SequenceGenerator(name = "seq_cli",sequenceName = "SEQ_CLIENTE", allocationSize = 1)
	private long id;
	
	@Column
	private String nombre;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "DIRECCION_ID", unique = true, nullable = false)
	private Direccion direccion;

	@OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Pedido> pedido=new ArrayList<Pedido>();
	
	
	public Cliente(String nombre) {
		this.nombre = nombre;
	}

	public Cliente() {
	}


	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
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

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}
	
	
	
}
