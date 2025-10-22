package practicaJAXB.practica;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws JAXBException {
    	File f=new File ("jaxbpractice.xml");
        JAXBContext context= JAXBContext.newInstance(Concesionario.class);
        Marshaller m= context.createMarshaller();
        
        Concesionario c1= new Concesionario();
        
        c1.setNombre("pacoautos");
        c1.setUbicacion("aqui");
        
        ArrayList<Vehiculo> vh = new ArrayList<Vehiculo>();
        
        Vehiculo v1= new Vehiculo();
        v1.setAño(52345);
        v1.setFabricante("tu mom");
        v1.setMarca("repooplt");
        v1.setModelo("vpooper");
        v1.setPrecio(591469.0);
        
        vh.add(v1);
        
        c1.setVehiculos(vh);
        
        m.setProperty(m.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(c1, f);
        
        Unmarshaller um= context.createUnmarshaller();
        Concesionario miConcesionario= (Concesionario) um.unmarshal(f);
        
        System.out.println("nombre "+miConcesionario.getNombre());
        System.out.println("ubicacion "+ miConcesionario.getUbicacion());
        
        ArrayList<Vehiculo> v2=miConcesionario.getVehiculos();
        
        for(Vehiculo v: v2) {
        	System.out.println("año: "+v.getAño()+ " Fabricante: "+v.getFabricante()+" Marca: "+v.getMarca()+" Modelo: "+v.getModelo()+" Precio: "+v.getPrecio());
        }
        
        
    }
}
