package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.Direccion;
import util.HibernateUtil;

public class DireccionDAOImpl implements DireccionDAO{

	SessionFactory factoria= HibernateUtil.getSessionFactory();
		
	
	@Override
	public void crear(Direccion d) {
		Session ses=factoria.openSession();
		Transaction tx=null;

		try {
			tx=ses.beginTransaction();
			ses.persist(d);			
			tx.commit();
		}catch(RuntimeException e){

			if (tx != null)
				tx.rollback();
			throw e; 

		}finally {
			ses.close();
		}
	}

	@Override
	public Direccion obtener(Long id) {
		Session ses=factoria.openSession();
		Transaction tx=null;
		Direccion dc=null;

		try {
			tx=ses.beginTransaction();
			dc=ses.find(Direccion.class, id);			
			tx.commit();
		}catch(RuntimeException e){

			if (tx != null)
				tx.rollback();
			throw e; 

		}finally {
			ses.close();
		}
		return dc;
	}

	@Override
	public void actualizar(Direccion d) {
		Session ses=factoria.openSession();
		Transaction tx=null;

		try {
			tx=ses.beginTransaction();
			ses.merge(d);			
			tx.commit();
		}catch(RuntimeException e){

			if (tx != null)
				tx.rollback();
			throw e; 

		}finally {
			ses.close();
		}
	}

	@Override
	public void eliminar(Long id) {
		Session ses=factoria.openSession();
		Transaction tx=null;
		Direccion dc=null;
		try {
			tx=ses.beginTransaction();
			dc=ses.find(Direccion.class,id);	
			ses.remove(dc);
			tx.commit();
		}catch(RuntimeException e){

			if (tx != null)
				tx.rollback();
			throw e; 

		}finally {
			ses.close();
		}
	}

}
