package dao;

import modelo.Producto;

public interface ProductoDAO {

	public void modelo();
	
	void guardar(Producto p);

	public Producto buscarPorId(Long id) ;

	public void actualizar(Producto p) ;

	public void eliminar(Long id) ;

}
