package test;

import negocio.ClienteABM;
import datos.Cliente;
import datos.Prestamo;

public class TestTraerClienteYPrestamos {

	public static void main(String[] args) {

		long idCliente = 1;
		ClienteABM cliAbm = new ClienteABM();
		Cliente c = null;
		
		//IMPLEMENTAR SI ESTE CLIENTE NO TIENE PRESTAMOS OTORGADOS IMPRIMA EL MENSAJE
		try
		{	
		c = cliAbm.traerClienteYprestamos(idCliente);		
		System.out.println("\n---> Traer Cliente y Prestamos idCliente = " + idCliente);
		System.out.println("\n" + c);
		for(Prestamo p : c.getPrestamos())
			System.out.println("\n" + p);
		
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		
	}

}
