package Jaxb.PruebaUT1;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
@XmlType(propOrder = {"stock", "codigo", "nombre","precio"})
public class Articulo implements Serializable {

    private String codigo;
    private String nombre;
    private int stock;
    private double precio;

    public Articulo() {}

    public Articulo(String codigo, String nombre, int stock, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    // --- Getters y Setters ---
    @XmlElement
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    @XmlElement
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @XmlAttribute
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    
    @XmlElement
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return codigo+";"+nombre+";"+stock+";"+precio;
    }
}