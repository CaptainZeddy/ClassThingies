package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import modelo.Pedido;
import util.HibernateUtil;

public class PedidoDAOImpl implements PedidoDAO {

SessionFactory factoria= HibernateUtil.getSessionFactory();
	
		
	@Override
	public void crear(Pedido p) {
		Session ses=factoria.openSession();
		Transaction tx=null;

		try {
			tx=ses.beginTransaction();
			ses.persist(p);			
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
	public Pedido obtener(Long id) {
		Session ses=factoria.openSession();
		Transaction tx=null;
		Pedido pd=null;

		try {
			tx=ses.beginTransaction();
			pd=ses.find(Pedido.class, id);			
			tx.commit();
		}catch(RuntimeException e){

			if (tx != null)
				tx.rollback();
			throw e; 

		}finally {
			ses.close();
		}
		return pd;
	}

	@Override
	public void actualizar(Pedido d) {
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
		Pedido pd=null;
		try {
			tx=ses.beginTransaction();
			pd=ses.find(Pedido.class,id);	
			ses.remove(pd);
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
