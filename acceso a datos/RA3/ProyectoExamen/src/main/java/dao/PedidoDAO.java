package dao;

import modelo.Pedido;

public interface PedidoDAO {
	

	public void crear(Pedido p);

	public Pedido obtener(Long id);

	public void actualizar(Pedido p);

	public void eliminar(Long id);
}
