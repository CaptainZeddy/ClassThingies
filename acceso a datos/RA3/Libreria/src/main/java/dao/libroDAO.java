package dao;

import modelo.Detalle_libro;

public interface libroDAO {

	public void modelo();
	
	public Detalle_libro consultarLibro(long id);
	
	public void insertarLibro(Detalle_libro ld);
	
	public void actualizarLibro(Detalle_libro ld);
	
	public void deletearLibro(long id);
	
}
