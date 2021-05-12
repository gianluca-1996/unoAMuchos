package dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cliente;
import datos.Cuota;
import datos.Prestamo;

public class PrestamoDao {

	private static Session session;
	private Transaction tx;
	
	private void iniciaOperacion() throws HibernateException
	{
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}
	
	private void manejaExcepcion(HibernateException he) throws HibernateException
	{
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}
	
	public Prestamo traer(long idPrestamo)	throws HibernateException
	{
		Prestamo obj = null;
		
		try
		{
			iniciaOperacion();
			String hql = "from Prestamo p inner join fetch p.cliente c where p.idPrestamo =" + idPrestamo;
			obj = (Prestamo) session.createQuery(hql).uniqueResult();
		}
		
		finally
		{
			session.close();
		}
		
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public List<Prestamo> traer(Cliente c)
	{
		List<Prestamo> lista = null;
		try
		{
			iniciaOperacion();
			String hql = "from Prestamo p inner join fetch p.cliente c where c.idCliente =" + c.getIdCliente(); 
			lista = session.createQuery(hql).list();
		}
		
		finally
		{
			session.close();
		}
		
		return lista;
	}
	
	public long agregar(Prestamo obj) throws HibernateException
	{
		long id;
		try
		{
			iniciaOperacion();
			id = (long) session.save(obj);
			tx.commit();
		}catch(HibernateException he)
		{
			manejaExcepcion(he);
			throw he;
		}
		
		finally
		{
			session.close();
		}
		
		return id;
	}
	
	public long agregar(Prestamo obj, List<Cuota> listaCuotas) throws HibernateException
	{
		CuotaDao daoCuota = new CuotaDao();
		long id = 1;

		try
		{
			//iniciaOperacion();
			//id = (long) session.save(obj);
			daoCuota.agregar(listaCuotas, obj);
			//tx.commit();
		}catch(HibernateException he)
		{
			manejaExcepcion(he);
			throw he;
		}
		
		finally
		{
			//session.close();
		}
		
		return id;
	}
	
	public void modificar(Prestamo p) throws HibernateException
	{
		try
		{
			iniciaOperacion();
			session.update(p);
			tx.commit();
		}catch(HibernateException he)
		{
			manejaExcepcion(he);
			throw he;
		}
		
		finally
		{
			session.close();
		}
	}
}















