package dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cliente;
import datos.Cuota;
import datos.Prestamo;

public class CuotaDao {

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
	
	public void agregar(Cuota cuota)
	{
		try
		{
			iniciaOperacion();
			session.save(cuota);
			tx.commit();
		}	catch(HibernateException he) {
				manejaExcepcion(he);
				throw he;
			}
			
			finally {
				session.close();
			}
	}
	
	public void agregar(List<Cuota> cuotas, Prestamo prest)
	{	
		try
		{
			iniciaOperacion();
			session.save(prest);
			
			for(int i = 0; i < cuotas.size(); i++)	
			{
				session.save(cuotas.get(i));				
			}
			
			tx.commit();
		}	catch(HibernateException he) {
				manejaExcepcion(he);
				throw he;
			}
			
			finally {
				session.close();
			}
	}
	
	public Cuota traer(long idCuota) throws HibernateException
	{
		Cuota objeto = null;
		try
		{
			iniciaOperacion();
			objeto = (Cuota) session.get(Cuota.class, idCuota);
		}	finally
			{
				session.close();
			}
		
		return objeto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cuota> traer() throws HibernateException
	{
		List<Cuota> lista = null;
		
		try
		{
			iniciaOperacion();
			lista = session.createQuery("from Cuota").list();
		}
		
		finally
		{
			session.close();
		}
		
		return lista;
	}
}
