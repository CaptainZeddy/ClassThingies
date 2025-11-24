package Artefacto.Ejercicio1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Artefacto.Ejercicio1.*;

public class Modelo {
	private SessionFactory factoria;
	
	public Modelo() {
	
		factoria = HibernateUtil.getSessionFactory();
	}
	
	public void insertar(Empleado empleado) {
		Session ses=factoria.openSession();
		
		Transaction tx=null;
		
		try {
			
			tx=ses.beginTransaction();
			ses.persist(empleado);
			
			tx.commit();
		}catch(RuntimeException e){
			
			if (tx != null)
				tx.rollback();
			throw e; 
			
		}finally {
			ses.close();
		}
	}
	
	public Empleado buscar(long id) {
		Session ses=factoria.openSession();
		Empleado e=null;
		Transaction tx=null;
		
		try {
			
			tx= ses.beginTransaction();
			e= ses.find(Empleado.class, id);
			
			tx.commit();
		}catch(RuntimeException ex){
			
			if (tx != null)
				tx.rollback();
			throw ex; 
			
		}finally {
			ses.close();
		}
		return e;
	}
}
