package servicio;

//En el servicio se realizan validaciones de negocio no se mezclan con la interfaz.
//Si se quiere hacer varias operaciones DAO en una misma transacción, se hace aquí un método y la app sólo llama al método.

import dao.ProductoDAO;
import modelo.Producto;
import java.math.BigDecimal;

public class ProductoService {

 private final ProductoDAO productoDAO;

 public ProductoService(ProductoDAO productoDAO) {
     this.productoDAO = productoDAO;
 }

 public void registrarProducto(Producto p) {
     // Lógica de negocio: Validar que el precio no sea negativo
     if (p.getPrecio().compareTo(BigDecimal.ZERO) < 0) {
         throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
     }
     productoDAO.guardar(p);
 }

 public Producto obtenerProducto(Long id) {
     // Aquí no hay lógica de negocio compleja, solo delegamos
     return productoDAO.buscarPorId(id);
 }

 public void modificarProducto(Producto p) {
     // Lógica de negocio: Asegurar que el ID exista antes de actualizar
     if (productoDAO.buscarPorId(p.getId()) == null) {
          throw new IllegalArgumentException("El producto a modificar no existe.");
     }
     productoDAO.actualizar(p);
 }

 public void eliminarProducto(Long id) {
     productoDAO.eliminar(id);
 }
}