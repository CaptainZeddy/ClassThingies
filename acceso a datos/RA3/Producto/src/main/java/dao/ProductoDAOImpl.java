package dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.Producto;
import util.HibernateUtil;

public class ProductoDAOImpl implements ProductoDAO{

	private SessionFactory factoria=null;

	@Override
	public void modelo() {
		factoria = HibernateUtil.getSessionFactory();
	}

	@Override
	public void guardar(Producto p) {
		Session ses=factoria.openSession();

		Transaction tx=null;

		try {
			tx=ses.getTransaction();


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
	public Producto buscarPorId(Long id) {
		Session ses=factoria.openSession();
		Producto p=null;
		Transaction tx=null;

		try {
			tx=ses.getTransaction();
			p=ses.find(Producto.class, id);
			tx.commit();
		}catch(RuntimeException e){

			if (tx != null)
				tx.rollback();
			throw e; 

		}finally {
			ses.close();
		}
		return null;
	}

	@Override
	public void actualizar(Producto p) {
		Session ses=factoria.openSession();
		Producto pro=p;
		Transaction tx=null;

		try {
			tx=ses.getTransaction();
			

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
		Producto p=null;
		Transaction tx=null;

		try {
			tx=ses.getTransaction();
			p=ses.find(Producto.class, id);
			
			ses.remove(p);

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
