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


@Table
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente") 
	@SequenceGenerator(name = "seq_cliente", sequenceName = "SEQ_CLIENTE", allocationSize = 1)
	private long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "DIRECCION_ID", unique = true, nullable = false)
	private Direccion direccion;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pedido> pedidoLista= new ArrayList<Pedido>();

	public Cliente() {
	}



	public Cliente(String nombre) {
		this.nombre = nombre;
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
		if (direccion != null) {
			direccion.setCliente(this);
		}
	}

	public List<Pedido> getPedidoLista() {
		return pedidoLista;
	}

	public void setPedidoLista(List<Pedido> pedidoLista) {
		this.pedidoLista = pedidoLista;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", pedidoLista=" + pedidoLista
				+ "]";
	}



	public void addPedido(Pedido p) {
		pedidoLista.add(p);
		p.setCliente(this);
	}



	public void removePedido(Pedido p) {
		pedidoLista.remove(p);
		p.setCliente(null);
	}




}
