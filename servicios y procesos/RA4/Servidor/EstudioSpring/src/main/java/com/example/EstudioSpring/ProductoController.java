package com.example.EstudioSpring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	private static List<Producto> productoSim= new ArrayList<Producto>();
	private static long contador=0;

	public ProductoController() {
		productoSim.add(new Producto(contador++, "Ratón Gaming", "Logitech", 45.50));
		productoSim.add(new Producto(contador++, "Monitor 24", "Samsung", 120.00));
		productoSim.add(new Producto(contador++, "Teclado Mecánico", "Corsair", 89.99));
	}

	@GetMapping
	public List<Producto> listar(@RequestParam(required = false) String marca ){
		if (marca==null) {
			return productoSim;
		}

		List<Producto> producto= new ArrayList<Producto>();

		for (Producto p : productoSim) {
			if(p.getMarca().equalsIgnoreCase(marca)){
				producto.add(p);
			}

		}

		return producto;
	}

	@PostMapping
	public Producto insert(@RequestBody Producto producto) {
		producto.setId(contador++);
		productoSim.add(producto);
		return producto;
	}

	@PutMapping("/{id}")
	public Producto update(@PathVariable Long id, @RequestBody Producto producto) {
		for(Producto p : productoSim) {
			if(p.getId()==id) {
				p.setPrecio(producto.getPrecio());
				return p;

			}
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		for(int i =0; i <productoSim.size();i++) {
			Producto p= productoSim.get(i);
			if(p.getId()==id) {
				productoSim.remove(i);
				return "Producto con id "+id+" borrado con exito.";
			}
		}
		return "No se ha encontrado el producto con id: "+id;

	}

}
