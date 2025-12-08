package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.Detalle_libro;
import util.HibernateUtil;

public class libroDAOimpl implements libroDAO{

	private SessionFactory factoria=null;

	@Override
	public void modelo() {
		factoria = HibernateUtil.getSessionFactory();
	}

	@Override
	public Detalle_libro consultarLibro(long id) {
		Session ses=factoria.openSession();
		Detalle_libro ld=null;
		Transaction tx=null;

		try {
			tx=ses.beginTransaction();
			ld=ses.find(Detalle_libro.class, id);
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
	public void insertarLibro(Detalle_libro ld) {
		Session ses=factoria.openSession();

		Transaction tx=null;

		try {
			tx=ses.beginTransaction();
			ses.persist(ld);
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
	public void actualizarLibro(Detalle_libro ld) {
		Session ses=factoria.openSession();
		Transaction tx=null;

		try {
			tx=ses.beginTransaction();
			ses.merge(ld);
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
	public void deletearLibro(long id) {
		Session ses=factoria.openSession();
		Detalle_libro ld=null;
		Transaction tx=null;

		try {
			tx=ses.beginTransaction();
			ld=ses.find(Detalle_libro.class, id);
			ses.remove(ld);
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