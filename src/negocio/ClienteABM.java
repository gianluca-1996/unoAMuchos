package negocio;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;

import dao.ClienteDao;
import datos.Cliente;

public class ClienteABM {

ClienteDao dao = new ClienteDao();
	
	public Cliente traer(long idCliente)
	{
		Cliente c = dao.traer(idCliente);
		return c;
	}
	
	public Cliente traer(int dni)
	{
		Cliente c = dao.traer(dni);
		return c;
	}
	
	public int agregar(String apellido, String nombre, int dni, LocalDate fechaDeNacimiento)
	{
		//consultar si existe un cliente con el mismo dni, si existe arrojar la excepcion
		try
		{
			if(traer(dni) != null)
			{
				throw new Exception();
			}
		}catch(Exception e)
			{
				System.out.println("ERROR. Ya existe un cliente con el mismo DNI");
				return 0;
			}
		
		Cliente c = new Cliente(apellido, nombre, dni, fechaDeNacimiento);
		return dao.agregar(c);
	}
	
	public void modificar(Cliente c) throws Exception
	{
		//implementar antes de actualizar que no exista un cliente con el mismo documento a modificar y con el mismo id, lanzar la excepcion
		if(traer(c.getDni()) == null | traer(c.getIdCliente()) == null)
		{
			throw new Exception("ERROR. El cliente a modificar no existe");
		}
		
		else
			dao.actualizar(c);
	}
	
	public void eliminar(long idCliente) throws Exception
	{
		//en este caso es fisica en gral. no se aplicaria este caso de uso, si se hiciera habria que validar que el cliente no tenga dependencias
		Cliente c = dao.traer(idCliente);
		
		//implementar que si es null que arroje la excepcion
		if(c == null)
			throw new Exception("ERROR. El cliente a eliminar no existe");
		
		else
			dao.eliminar(c);
	}
	
	public List<Cliente> traer()
	{
		return dao.traer();
	}
	
	public Cliente traerClienteYprestamos(long idCliente) throws Exception
	{
		Cliente c = null;
		c = dao.traerClienteYPrestamos(idCliente);
		
		if(c.getPrestamos().isEmpty())
		{
			throw new Exception("El cliente no posee prestamos");
		}
			
		return c;
	}
	
}













