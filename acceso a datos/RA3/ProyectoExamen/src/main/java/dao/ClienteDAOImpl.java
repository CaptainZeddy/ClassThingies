package dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import modelo.Cliente;
import util.HibernateUtil;

public class ClienteDAOImpl implements ClienteDAO{

	private SessionFactory factoria=HibernateUtil.getSessionFactory();	


	@Override
	public void crear(Cliente c) {
		Session ses=factoria.openSession();
		Transaction tx=null;

		try {
			tx=ses.beginTransaction();
			ses.persist(c);			
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
	public Cliente obtener(Long idCliente) {
		Session ses=factoria.openSession();
		Transaction tx=null;
		Cliente cl=null;

		try {
			tx=ses.beginTransaction();
			cl=ses.find(Cliente.class, idCliente );	
			//			if(cl!=null)cl.getPedidoLista().size();
			tx.commit();
		}catch(RuntimeException e){

			if (tx != null)
				tx.rollback();
			throw e; 

		}finally {
			ses.close();
		}
		return cl;
	}

	@Override
	public void actualizar(Cliente c) {
		Session ses=factoria.openSession();
		Transaction tx=null;

		try {
			tx=ses.beginTransaction();
			ses.merge(c);			
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
	public void eliminar(Long idCliente) {
		Session ses=factoria.openSession();
		Transaction tx=null;
		Cliente cl=null;
		try {
			tx=ses.beginTransaction();
			cl=ses.find(Cliente.class, idCliente);
			ses.remove(cl);
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
	public Cliente obtenerClienteConInicializacion(Long id) {
		Session ses=factoria.openSession();
		Transaction tx=null;
		Cliente cl=null;

		try {
			tx=ses.beginTransaction();
			cl=ses.find(Cliente.class, id);
			
			if(cl != null) {
				Hibernate.initialize(cl.getDireccion());
				Hibernate.initialize(cl.getPedidoLista());
			}
			
			tx.commit();
		}catch(RuntimeException e){

			if (tx != null)
				tx.rollback();
			throw e; 

		}finally {
			ses.close();
		}
		return cl;
	}


}
