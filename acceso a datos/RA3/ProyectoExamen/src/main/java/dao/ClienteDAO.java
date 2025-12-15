package dao;

import modelo.Cliente;

public interface ClienteDAO {

	
	public void crear(Cliente c);

	public Cliente obtener(Long idCliente);

	public void actualizar(Cliente c);

	public void eliminar(Long idCliente);

	public Cliente obtenerClienteConInicializacion(Long id);

}
